package com.lzhlyle.klotski.board;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class StandardBoardFactoryTest {

    @Test
    public void createBoard_any_should20Cells() throws CloneNotSupportedException {
        StandardBoardFactory boardFactory = new StandardBoardFactory();

        Board board = boardFactory.createBoard();
        List<Cell> cells = board.getCellList();

        Assert.assertEquals(20, cells.size());
    }

    @Test
    public void createBoard_any_shouldDiffCellObjs() throws CloneNotSupportedException {
        StandardBoardFactory boardFactory = new StandardBoardFactory();

        Board board = boardFactory.createBoard();
        List<Cell> cells = board.getCellList();

        for (int i = 0; i < cells.size(); i++) {
            for (int j = 0; j < cells.size(); j++) {
                if (i != j) {
                    Assert.assertNotEquals(cells.get(i), cells.get(j));
                }
            }
        }
    }
}