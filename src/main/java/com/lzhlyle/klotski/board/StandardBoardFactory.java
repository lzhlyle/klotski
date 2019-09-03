package com.lzhlyle.klotski.board;

import java.util.ArrayList;
import java.util.List;

public class StandardBoardFactory implements IBoardFactory {
    @Override
    public Board createBoard() throws CloneNotSupportedException {
        // create cell list
        Cell prototype = new Cell();
        List<Cell> cellList = new ArrayList<>();
        cellList.add(prototype);
        for (int i = 0; i < 19; i++) {
            cellList.add(prototype.clone());
        }

        return new Board(cellList);
    }
}
