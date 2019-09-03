package com.lzhlyle.klotski.board;

public interface IBoardFactory {
    Board createBoard() throws CloneNotSupportedException;
}
