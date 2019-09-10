package com.lzhlyle.klotski.game;

import com.lzhlyle.klotski.block.CubeBlock;
import com.lzhlyle.klotski.block.HorizontalBlock;
import com.lzhlyle.klotski.block.VerticalBlock;
import com.lzhlyle.klotski.move.MoveDirectionEnum;
import com.lzhlyle.klotski.opening.Opening;
import com.lzhlyle.klotski.vo.Snapshot;
import org.junit.*;

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

    @After
    public void tear() {
        this.game = null;
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
        HorizontalBlock h = this.game.selectOnly(HorizontalBlock.class);

        Assert.assertNotNull(h);
    }

    @Test
    public void pickUpOnly_c_shouldThrowException() {
        try {
            CubeBlock c = this.game.selectOnly(CubeBlock.class);
            Assert.fail("should throw exception");
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
            Assert.assertTrue(ex.getMessage().contains("can not find"));
        }
    }

    @Test
    public void pickUp_c00_shouldNotNull() {
        CubeBlock c = this.game.select(CubeBlock.class, 0, 0);

        Assert.assertNotNull(c);
    }

    @Test
    public void pickUpOnly_c11_shouldThrowException() {
        try {
            CubeBlock c = this.game.select(CubeBlock.class, 1, 1);
            Assert.fail("should throw exception");
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
            Assert.assertTrue(ex.getMessage().contains("can not find"));
        }
    }

    @Test
    public void move_hUp_shouldBeMoved() {
        HorizontalBlock h = this.game.selectOnly(HorizontalBlock.class);

        this.game.move(h, MoveDirectionEnum.UP);

        String currentSnapshotResult = this.game.getCurrentSnapshot().getCompressResult();
        Assert.assertEquals("4,5|C,C1,C2,C3,V,1V3,1H1,2V,3S1,3V3,3", currentSnapshotResult);
    }

    @Test
    public void move_hUp_c10Up_shouldBeMoved() {
        HorizontalBlock h = this.game.selectOnly(HorizontalBlock.class);
        this.game.move(h, MoveDirectionEnum.UP);

        CubeBlock c = this.game.select(CubeBlock.class, 1, 0);
        this.game.move(c, MoveDirectionEnum.UP);

        String currentSnapshotResult = this.game.getCurrentSnapshot().getCompressResult();
        Assert.assertEquals("4,5|C,C2,C3,V,1C1,1V3,1H1,2V,3S1,3V3,3", currentSnapshotResult);
    }

    @Test
    public void move_hUp_c10Up_c00Right_shouldBeMoved() {
        HorizontalBlock h = this.game.selectOnly(HorizontalBlock.class);
        this.game.move(h, MoveDirectionEnum.UP);

        CubeBlock c10 = this.game.select(CubeBlock.class, 1, 0);
        this.game.move(c10, MoveDirectionEnum.UP);

        CubeBlock c00 = this.game.select(CubeBlock.class, 0, 0);
        this.game.move(c00, MoveDirectionEnum.RIGHT);

        String currentSnapshotResult = this.game.getCurrentSnapshot().getCompressResult();
        Assert.assertEquals("4,5|C1,C2,C3,V,1C1,1V3,1H1,2V,3S1,3V3,3", currentSnapshotResult);
    }

    @Test
    public void move_hUp_c10Up_v01Down_c00Right_shouldBeMoved() {
        HorizontalBlock h = this.game.selectOnly(HorizontalBlock.class);
        this.game.move(h, MoveDirectionEnum.UP);

        CubeBlock c10 = this.game.select(CubeBlock.class, 1, 0);
        this.game.move(c10, MoveDirectionEnum.UP);

        CubeBlock c00 = this.game.select(CubeBlock.class, 0, 0);
        this.game.move(c00, MoveDirectionEnum.RIGHT);

        VerticalBlock v01 = this.game.select(VerticalBlock.class, 0, 1);
        this.game.move(v01, MoveDirectionEnum.DOWN);

        String currentSnapshotResult = this.game.getCurrentSnapshot().getCompressResult();
        Assert.assertEquals("4,5|V,C1,C2,C3,C1,1V3,1H1,2V,3S1,3V3,3", currentSnapshotResult);
    }
}