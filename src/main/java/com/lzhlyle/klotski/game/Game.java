package com.lzhlyle.klotski.game;

import com.lzhlyle.klotski.block.*;
import com.lzhlyle.klotski.board.BlockPlace;
import com.lzhlyle.klotski.board.Board;
import com.lzhlyle.klotski.board.Cell;
import com.lzhlyle.klotski.board.StandardBoardFactory;
import com.lzhlyle.klotski.move.MoveDirectionEnum;
import com.lzhlyle.klotski.opening.Opening;
import com.lzhlyle.klotski.pedometer.Pedometer;
import com.lzhlyle.klotski.rule.MoveRule;
import com.lzhlyle.klotski.rule.StepRule;
import com.lzhlyle.klotski.rule.WinRule;
import com.lzhlyle.klotski.vo.Duration;
import com.lzhlyle.klotski.vo.Position;
import com.lzhlyle.klotski.vo.Snapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Game {
    private Opening opening;
    private MoveRule moveRule;
    private WinRule winRule;
    private Duration duration;
    private GameStatusEnum status;

    private Board board;
    private List<BlockPlace> blockPlaceList;

    private Pedometer pedometer;

    private Snapshot currentSnapshot;

    public Game(Opening opening) throws CloneNotSupportedException {
        this.opening = opening;
        this.moveRule = new MoveRule();
        this.winRule = new WinRule();
        this.duration = null;
        this.status = GameStatusEnum.READY;

        this.board = this.initBoard(opening.getSnapshot());
        this.blockPlaceList = this.placeBlocks(opening.getSnapshot(), this.board);

        this.pedometer = new Pedometer(new StepRule());

        this.currentSnapshot = opening.getSnapshot();
    }

    private Board initBoard(Snapshot snapshot) throws CloneNotSupportedException {
        // should re-construct board from snapshot
        // standard board instead
        Board board = new StandardBoardFactory().createBoard();

        // set cells' position
        int cellIndex = 0;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 5; y++) {
                board.getCellList().get(cellIndex).setPosition(x, y);
                cellIndex++;
            }
        }

        return board;
    }

    private List<BlockPlace> placeBlocks(Snapshot snapshot, Board board) {
        // should re-construct board from snapshot
        // 横刀立马 instead
        List<BlockPlace> blockPlaces = new ArrayList<>();
        blockPlaces.add(this.placeBlock(board, new CubeBlock(), 0, 0));
        blockPlaces.add(this.placeBlock(board, new CubeBlock(), 1, 0));
        blockPlaces.add(this.placeBlock(board, new CubeBlock(), 2, 0));
        blockPlaces.add(this.placeBlock(board, new CubeBlock(), 3, 0));
        blockPlaces.add(this.placeBlock(board, new VerticalBlock(), 0, 1));
        blockPlaces.add(this.placeBlock(board, new HorizontalBlock(), 1, 1));
        blockPlaces.add(this.placeBlock(board, new VerticalBlock(), 3, 1));
        blockPlaces.add(this.placeBlock(board, new VerticalBlock(), 0, 3));
        blockPlaces.add(this.placeBlock(board, new SquareBlock(), 1, 3));
        blockPlaces.add(this.placeBlock(board, new VerticalBlock(), 3, 3));
        return blockPlaces;
    }

    private BlockPlace placeBlock(Board board, Block block, int x, int y) {
        Cell southwestCell = board.locateCell(x, y);

        for (int i = 1; i < block.getWidth(); i++) {
            int cX = southwestCell.getPosition().getX() + i;
            int cY = southwestCell.getPosition().getY();
            Cell locateCell = board.locateCell(cX, cY);
            if (locateCell.isOccupied()) {
                throw new RuntimeException("the cell(" + cX + "," + cY + ") is occupied.");
            }
        }

        for (int i = 1; i < block.getHeight(); i++) {
            int cX = southwestCell.getPosition().getX();
            int cY = southwestCell.getPosition().getY() + i;
            Cell locateCell = board.locateCell(cX, cY);
            if (locateCell.isOccupied()) {
                throw new RuntimeException("the cell(" + cX + "," + cY + ") is occupied.");
            }
        }

        return new BlockPlace(southwestCell, block);
    }

    public void start() {
        this.duration = new Duration();
        this.status = GameStatusEnum.PLAYING;
    }

    public <T extends Block> T pickUpOnly(Class blockClass) {
        List<Block> blocks = this.blockPlaceList.stream()
                .filter(bp -> Objects.equals(bp.getBlock().getClass(), blockClass))
                .map(BlockPlace::getBlock)
                .collect(Collectors.toList());
        if (blocks.size() != 1) {
            throw new RuntimeException("can not find only " + blockClass.getSimpleName());
        }
        return (T) blocks.get(0);
    }

    public <T extends Block> T pickUp(Class blockClass, Position southwestCellPosition) {
        List<Block> blocks = this.blockPlaceList.stream()
                .filter(bp -> Objects.equals(bp.getBlock().getClass(), blockClass)
                        && Objects.equals(bp.getSouthwestCell().getPosition(), southwestCellPosition))
                .map(BlockPlace::getBlock)
                .collect(Collectors.toList());
        if (blocks.size() != 1) {
            throw new RuntimeException("can not find " + blockClass.getSimpleName() + " in " + southwestCellPosition.toString());
        }
        return (T) blocks.get(0);
    }

    public void move(Block mover, MoveDirectionEnum direction) {
        boolean isAllow = this.moveRule.check(this.blockPlaceList, mover, direction);
        if (isAllow) {
            mover.move(direction);
        }
    }

}
