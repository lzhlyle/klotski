package com.lzhlyle.klotski.board;

import com.lzhlyle.klotski.game.Game;
import com.lzhlyle.klotski.opening.Opening;
import com.lzhlyle.klotski.vo.Location;
import com.lzhlyle.klotski.vo.Snapshot;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {
    private Board board;

    @Before
    public void setup() throws CloneNotSupportedException {
        Snapshot snapshot = new Snapshot("4,5|C,C1,C2,C3,V,1H1,1V3,1V,3S1,3V3,3");
        Opening opening = new Opening(snapshot);
        Game game = new Game(opening);
        this.board = game.getBoard();
    }

    @After
    public void tear() {
        this.board = null;
    }

    @Test
    public void locateCell_withXY() {
        Cell cell = this.board.locateCell(1, 2);

        Assert.assertNotNull(cell);
        Assert.assertEquals(new Location(1, 2), cell.getLocation());
    }

    @Test
    public void locateCell_withLocation() {
        Cell cell = this.board.locateCell(new Location(1, 2));

        Assert.assertNotNull(cell);
        Assert.assertEquals(new Location(1, 2), cell.getLocation());
    }

    @Test
    public void locateLeft() {
        Cell source = this.board.locateCell(new Location(1, 2));

        Cell cell = this.board.locateLeft(source);

        Assert.assertEquals(new Location(0, 2), cell.getLocation());
    }

    @Test
    public void locateRight() {
        Cell source = this.board.locateCell(new Location(1, 2));

        Cell cell = this.board.locateRight(source);

        Assert.assertEquals(new Location(2, 2), cell.getLocation());
    }

    @Test
    public void locateUp() {
        Cell source = this.board.locateCell(new Location(1, 2));

        Cell cell = this.board.locateUp(source);

        Assert.assertEquals(new Location(1, 3), cell.getLocation());
    }

    @Test
    public void locateDown() {
        Cell source = this.board.locateCell(new Location(1, 2));

        Cell cell = this.board.locateDown(source);

        Assert.assertEquals(new Location(1, 1), cell.getLocation());
    }
}