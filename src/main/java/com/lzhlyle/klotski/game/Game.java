package com.lzhlyle.klotski.game;

import com.lzhlyle.klotski.block.*;
import com.lzhlyle.klotski.board.BlockPlace;
import com.lzhlyle.klotski.board.Board;
import com.lzhlyle.klotski.board.Cell;
import com.lzhlyle.klotski.board.StandardBoardFactory;
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
        this.blockPlaceList = this.initBlockPlace(opening.getSnapshot());

        this.pedometer = new Pedometer(new StepRule());

        this.currentSnapshot = opening.getSnapshot();
    }

    private Board initBoard(Snapshot snapshot) throws CloneNotSupportedException {
        // should re-construct board from snapshot
        // standard board instead
        return new StandardBoardFactory().createBoard();
    }

    private List<BlockPlace> initBlockPlace(Snapshot snapshot) {
        // should re-construct board from snapshot
        // 横刀立马 instead
        List<BlockPlace> blockPlaces = new ArrayList<>();
        blockPlaces.add(new BlockPlace(new Cell(new Position(1, 3)), new SquareBlock()));
        blockPlaces.add(new BlockPlace(new Cell(new Position(1, 1)), new HorizontalBlock()));
        blockPlaces.add(new BlockPlace(new Cell(new Position(0, 1)), new VerticalBlock()));
        blockPlaces.add(new BlockPlace(new Cell(new Position(3, 1)), new VerticalBlock()));
        blockPlaces.add(new BlockPlace(new Cell(new Position(0, 3)), new VerticalBlock()));
        blockPlaces.add(new BlockPlace(new Cell(new Position(3, 3)), new VerticalBlock()));
        blockPlaces.add(new BlockPlace(new Cell(new Position(0, 0)), new CubeBlock()));
        blockPlaces.add(new BlockPlace(new Cell(new Position(1, 0)), new CubeBlock()));
        blockPlaces.add(new BlockPlace(new Cell(new Position(2, 0)), new CubeBlock()));
        blockPlaces.add(new BlockPlace(new Cell(new Position(3, 0)), new CubeBlock()));
        return blockPlaces;
    }

    public void start() {
        this.duration = new Duration();
        this.status = GameStatusEnum.PLAYING;
    }

    public List<BlockPlace> getBlockPlaceList() {
        return blockPlaceList;
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

}
