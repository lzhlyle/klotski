package com.lzhlyle.klotski.rule;

import com.lzhlyle.klotski.block.*;
import com.lzhlyle.klotski.board.BlockPlace;
import com.lzhlyle.klotski.board.Board;
import com.lzhlyle.klotski.board.Cell;
import com.lzhlyle.klotski.game.Game;
import com.lzhlyle.klotski.move.MoveDirectionEnum;

import java.util.ArrayList;
import java.util.List;

public class MoveRule extends Rule {
    public boolean check(Game game, Block mover, MoveDirectionEnum direction) {
        List<Cell> borderCells = this.getBorderCells(game, mover, direction);

        for (Cell borderCell : borderCells) {
            // from-cell's location
            int toX = borderCell.getLocation().getX();
            int toY = borderCell.getLocation().getY();

            // to-cell's location
            if (direction == MoveDirectionEnum.UP) {
                toY = toY + 1;
            } else if (direction == MoveDirectionEnum.DOWN) {
                toY = toY - 1;
            } else if (direction == MoveDirectionEnum.LEFT) {
                toX = toX - 1;
            } else if (direction == MoveDirectionEnum.RIGHT) {
                toX = toX + 1;
            }

            Cell toCell = game.getBoard().locateCell(toX, toY);
            if (toCell == null) {
                // out of the board
                return false;
            }
            if (toCell.isOccupied()) {
                // the cell is occupied
                return false;
            }
        } // end iterator

        return true;
    }

    private List<Cell> getBorderCells(Game game, Block mover, MoveDirectionEnum direction) {
        BlockPlace blockPlace = game.locateBlock(mover);
        Cell southwestCell = blockPlace.getSouthwestCell();
        Board board = game.getBoard();

        List<Cell> borderCells = new ArrayList<>();

        if (mover instanceof CubeBlock) {
            borderCells.add(southwestCell);
        } else if (mover instanceof HorizontalBlock) {
            if (direction == MoveDirectionEnum.UP || direction == MoveDirectionEnum.DOWN) {
                borderCells.add(southwestCell);
                borderCells.add(board.locateRight(southwestCell));
            } else if (direction == MoveDirectionEnum.LEFT) {
                borderCells.add(southwestCell);
            } else if (direction == MoveDirectionEnum.RIGHT) {
                borderCells.add(board.locateRight(southwestCell));
            }
        } else if (mover instanceof VerticalBlock) {
            if (direction == MoveDirectionEnum.LEFT || direction == MoveDirectionEnum.RIGHT) {
                borderCells.add(southwestCell);
                borderCells.add(board.locateUp(southwestCell));
            } else if (direction == MoveDirectionEnum.UP) {
                borderCells.add(board.locateUp(southwestCell));
            } else if (direction == MoveDirectionEnum.DOWN) {
                borderCells.add(southwestCell);
            }
        } else if (mover instanceof SquareBlock) {
            if (direction == MoveDirectionEnum.UP) {
                Cell northwestCell = board.locateUp(southwestCell);
                borderCells.add(northwestCell);
                borderCells.add(board.locateRight(northwestCell));
            } else if (direction == MoveDirectionEnum.DOWN) {
                borderCells.add(southwestCell);
                borderCells.add(board.locateRight(southwestCell));
            } else if (direction == MoveDirectionEnum.LEFT) {
                borderCells.add(southwestCell);
                borderCells.add(board.locateUp(southwestCell));
            } else if (direction == MoveDirectionEnum.RIGHT) {
                Cell southeastCell = board.locateRight(southwestCell);
                borderCells.add(southeastCell);
                borderCells.add(board.locateUp(southeastCell));
            }
        }
        return borderCells;
    }
}
