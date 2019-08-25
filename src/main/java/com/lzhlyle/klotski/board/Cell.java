package com.lzhlyle.klotski.board;

import com.lzhlyle.klotski.vo.Position;

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
