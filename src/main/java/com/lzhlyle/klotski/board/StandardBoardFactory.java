package com.lzhlyle.klotski.board;

public class StandardBoardFactory implements IBoardFactory {
    @Override
    public Board createBoard() throws CloneNotSupportedException {
        return new Board(Cell.generateCells(20));

    }
}
