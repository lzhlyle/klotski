package com.lzhlyle.klotski.board;

import com.lzhlyle.klotski.vo.Location;

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
        return this.locateCell(new Location(x, y));
    }

    public Cell locateCell(Location location) {
        if (this.cellList == null || this.cellList.isEmpty()) {
            return null;
        }

        return this.cellList.stream()
                .filter(c -> Objects.equals(c.getLocation(), location))
                .findFirst().orElse(null);
    }

    public Cell locateLeft(Cell source) {
        return this.locateCell(source.getLocation().getX() - 1, source.getLocation().getY());
    }

    public Cell locateRight(Cell source) {
        return this.locateCell(source.getLocation().getX() + 1, source.getLocation().getY());
    }

    public Cell locateUp(Cell source) {
        return this.locateCell(source.getLocation().getX(), source.getLocation().getY() + 1);
    }

    public Cell locateDown(Cell source) {
        return this.locateCell(source.getLocation().getX(), source.getLocation().getY() - 1);
    }
}
