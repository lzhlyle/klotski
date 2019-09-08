package com.lzhlyle.klotski.board;

import com.lzhlyle.klotski.vo.Location;

import java.util.ArrayList;
import java.util.List;

public class Cell implements Cloneable {
    private Location location;
    private boolean isOccupied;

    public Cell() {
        this.location = null;
        this.isOccupied = false;
    }

    public Cell(int x, int y) {
        this();
        this.setPosition(x, y);
    }

    public Location getLocation() {
        return location;
    }

    public void setPosition(int x, int y) {
        this.location = new Location(x, y);
    }

    public boolean isOccupied() {
        return this.isOccupied;
    }

    public void occupy() {
        this.isOccupied = true;
    }

    public void free() {
        this.isOccupied = false;
    }

    public static List<Cell> generateCells(int count) throws CloneNotSupportedException {
        Cell prototype = new Cell();
        List<Cell> cellList = new ArrayList<>();
        cellList.add(prototype);
        for (int i = 0; i < count - 1; i++) {
            cellList.add(prototype.clone());
        }
        return cellList;
    }

    @Override
    protected Cell clone() throws CloneNotSupportedException {
        return (Cell) super.clone();
    }
}
