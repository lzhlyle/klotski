package com.lzhlyle.klotski.board;

import java.util.List;

public class Board {
    private List<Cell> cellList;

    public Board(List<Cell> cellList) {
        this.cellList = cellList;
    }

    public List<Cell> getCellList() {
        return cellList;
    }
}
