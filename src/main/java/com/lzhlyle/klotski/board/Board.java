package com.lzhlyle.klotski.board;

import com.lzhlyle.klotski.vo.Position;

import java.util.List;
import java.util.Objects;

public class Board {
    private List<Cell> cellList;

    public Board(List<Cell> cellList) {
        this.cellList = cellList;
    }

    public List<Cell> getCellList() {
        return cellList;
    }

    public Cell locateCell(int x, int y) {
        return this.locateCell(new Position(x, y));
    }

    public Cell locateCell(Position position) {
        if (this.cellList == null || this.cellList.isEmpty()) {
            return null;
        }

        return this.cellList.stream()
                .filter(c -> Objects.equals(c.getPosition(), position))
                .findFirst().orElse(null);
    }


}
