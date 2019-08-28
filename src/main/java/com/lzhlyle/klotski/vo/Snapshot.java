package com.lzhlyle.klotski.vo;

import com.lzhlyle.klotski.block.*;
import com.lzhlyle.klotski.board.BlockPlace;
import com.lzhlyle.klotski.board.Board;
import com.lzhlyle.klotski.board.Cell;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Snapshot {
    private String compressResult;

    public Snapshot(String compressResult) {
        this.compressResult = compressResult;
    }

    public Snapshot(Board board, List<BlockPlace> blockPlaceList) {
        // compressing
        this.compressResult = this.compressBoard(board) + "|" + this.compressBlockPlaceList(blockPlaceList);
    }

    private String compressBlockPlaceList(List<BlockPlace> blockPlaceList) {
        StringBuilder builder = new StringBuilder();
        blockPlaceList.stream()
                // sort by y asc, x asc
                .sorted(
                        Comparator.comparing(BlockPlace::getSouthwestCell,
                                Comparator.comparing(Cell::getPosition,
                                        Comparator.comparingInt(Position::getX))))
                .sorted(
                        Comparator.comparing(BlockPlace::getSouthwestCell,
                                Comparator.comparing(Cell::getPosition,
                                        Comparator.comparingInt(Position::getY))))
                .forEach(blockPlace -> builder.append(this.getBlockStr(blockPlace.getBlock()))
                        .append(this.getPositionStr(blockPlace.getSouthwestCell().getPosition())));
        return builder.toString();
    }

    private String getPositionStr(Position position) {
        return (position.getX() == 0 ? "" : position.getX())
                + "," +
                (position.getY() == 0 ? "" : position.getY());
    }

    private String getBlockStr(Block block) {
        if (block instanceof SquareBlock) {
            return "S";
        } else if (block instanceof HorizontalBlock) {
            return "H";
        } else if (block instanceof VerticalBlock) {
            return "V";
        } else if (block instanceof CubeBlock) {
            return "C";
        } else {
            // Un-know
            return "U";
        }
    }

    private String compressBoard(Board board) {
        int width = 0;
        int height = 0;
        // support square only
        List<Cell> cellsInBoard = board.getCellList();
        for (Cell cell : cellsInBoard) {
            width = Math.max(width, cell.getPosition().getX() + 1);
            height = Math.max(height, cell.getPosition().getY() + 1);
        }
        return width + "," + height;
    }

    public String getCompressResult() {
        return compressResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Snapshot snapshot = (Snapshot) o;
        return compressResult.equals(snapshot.compressResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(compressResult);
    }

    @Override
    public String toString() {
        return "Snapshot{" +
                "compressResult='" + compressResult + '\'' +
                '}';
    }
}
