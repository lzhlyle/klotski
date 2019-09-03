package com.lzhlyle.klotski.board;

import com.lzhlyle.klotski.vo.Position;

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

    @Override
    public Cell clone() throws CloneNotSupportedException {
        return (Cell) super.clone();
    }
}
