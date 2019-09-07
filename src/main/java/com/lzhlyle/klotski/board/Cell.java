package com.lzhlyle.klotski.board;

import com.lzhlyle.klotski.vo.Position;

import java.util.ArrayList;
import java.util.List;

public class Cell implements Cloneable {
    private Position position;
    private boolean isOccupied;

    public Cell() {
        this.position = null;
        this.isOccupied = false;
    }

    public Cell(int x, int y) {
        this();
        this.setPosition(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        this.position = new Position(x, y);
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
