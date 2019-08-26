package com.lzhlyle.klotski.vo;

import com.lzhlyle.klotski.board.BlockPlace;
import com.lzhlyle.klotski.board.Board;
import com.lzhlyle.klotski.board.Cell;

import java.util.List;
import java.util.Objects;

public class Snapshot {
    private String compressResult;

    public Snapshot(String compressResult) {
        this.compressResult = compressResult;
    }

    public Snapshot(Board board, List<BlockPlace> blockPlaceList) {
        // compressing
        String boardCompress = this.compressBoard(board);
        String blockPlaceListCompress = this.compressBlockPlaceList(blockPlaceList);

        this.compressResult = boardCompress + "|" + blockPlaceListCompress;
    }

    private String compressBlockPlaceList(List<BlockPlace> blockPlaceList) {
        // TODO lzh compress block place list
        return "";
    }

    private String compressBoard(Board board) {
        int width = 0;
        int height = 0;
        // support square only
        List<Cell> cellsInBoard = board.getCellList();
        for (Cell cell : cellsInBoard) {
            width = Math.max(width, cell.getPosition().getX());
            height = Math.max(height, cell.getPosition().getY());
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
