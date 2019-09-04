package com.lzhlyle.klotski.board;

import com.lzhlyle.klotski.vo.Position;

import java.util.ArrayList;
import java.util.List;

public class Cell implements Cloneable {
    private Position position;
    private boolean isEmpty;

    public Cell() {
        this.position = null;
        this.isEmpty = true;
    }

    public Cell(Position position) {
        this();
        this.position = position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isEmpty() {
        return isEmpty;
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
