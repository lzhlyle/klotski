package com.lzhlyle.klotski.game;

import com.lzhlyle.klotski.block.CubeBlock;
import com.lzhlyle.klotski.block.HorizontalBlock;
import com.lzhlyle.klotski.opening.Opening;
import com.lzhlyle.klotski.vo.Location;
import com.lzhlyle.klotski.vo.Snapshot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class GameTest {

    private Game game;

    @Before
    public void setup() throws CloneNotSupportedException {
        Snapshot snapshot = new Snapshot("4,5|C,C1,C2,C3,V,1H1,1V3,1V,3S1,3V3,3");
        Opening opening = new Opening(snapshot);
        Game game = new Game(opening);
        game.start();
        this.game = game;
    }

    @Ignore("test private property game.board")
    @Test
    public void initBoardTest() {
//        List<Cell> cells = this.game.board.getCellList();
//        int cellIndex = 0;
//        for (int x = 0; x < 4; x++) {
//            for (int y = 0; y < 5; y++) {
//                Assert.assertEquals(new Location(x, y), cells.get(cellIndex).getLocation());
//                cellIndex++;
//            }
//        }
    }

    @Test
    public void pickUpOnly_h_shouldNotNull() {
        HorizontalBlock h = this.game.pickUpOnly(HorizontalBlock.class);

        Assert.assertNotNull(h);
    }

    @Test
    public void pickUpOnly_c_shouldThrowException() {
        try {
            CubeBlock c = this.game.pickUpOnly(CubeBlock.class);
            Assert.fail("should throw exception");
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
            Assert.assertTrue(ex.getMessage().contains("can not find"));
        }
    }

    @Test
    public void pickUp_c00_shouldNotNull() {
        CubeBlock c = this.game.pickUp(CubeBlock.class, new Location(0, 0));

        Assert.assertNotNull(c);
    }

    @Test
    public void pickUpOnly_c11_shouldThrowException() {
        try {
            CubeBlock c = this.game.pickUp(CubeBlock.class, new Location(1, 1));
            Assert.fail("should throw exception");
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
            Assert.assertTrue(ex.getMessage().contains("can not find"));
        }
    }

    @Test
    public void move() {
        // DOING lzh Game move() test
    }
}