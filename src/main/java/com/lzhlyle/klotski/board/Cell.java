package com.lzhlyle.klotski.board;

public class Cell {
    private Position position;
    private boolean isEmpty;

    public Cell(Position position) {
        this.position = position;
        this.isEmpty = true;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isEmpty() {
        return isEmpty;
    }
}
