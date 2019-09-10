package com.lzhlyle.klotski.rule;

import com.lzhlyle.klotski.block.HorizontalBlock;
import com.lzhlyle.klotski.block.SquareBlock;
import com.lzhlyle.klotski.block.VerticalBlock;
import com.lzhlyle.klotski.game.Game;
import com.lzhlyle.klotski.move.MoveDirectionEnum;
import com.lzhlyle.klotski.opening.Opening;
import com.lzhlyle.klotski.vo.Snapshot;
import org.junit.Assert;
import org.junit.Test;

public class MoveRuleTest {

    @Test
    public void check_hUp_shouldTrue() throws CloneNotSupportedException {
        Snapshot snapshot = new Snapshot("4,5|C,C1,C2,C3,V,1H1,1V3,1V,3S1,3V3,3");
        Opening opening = new Opening(snapshot);
        Game game = new Game(opening);
        HorizontalBlock h = game.selectOnly(HorizontalBlock.class);

        boolean canMove = new MoveRule().check(game, h, MoveDirectionEnum.UP);

        Assert.assertTrue(canMove);
    }

    @Test
    public void check_hDown_shouldFalse() throws CloneNotSupportedException {
        Snapshot snapshot = new Snapshot("4,5|C,C1,C2,C3,V,1H1,1V3,1V,3S1,3V3,3");
        Opening opening = new Opening(snapshot);
        Game game = new Game(opening);
        HorizontalBlock h = game.selectOnly(HorizontalBlock.class);

        boolean canMove = new MoveRule().check(game, h, MoveDirectionEnum.DOWN);

        Assert.assertFalse(canMove);
    }

    @Test
    public void check_vLeft_shouldFalse() throws CloneNotSupportedException {
        Snapshot snapshot = new Snapshot("4,5|C,C1,C2,C3,V,1H1,1V3,1V,3S1,3V3,3");
        Opening opening = new Opening(snapshot);
        Game game = new Game(opening);
        VerticalBlock v = game.select(VerticalBlock.class, 0, 1);

        boolean canMove = new MoveRule().check(game, v, MoveDirectionEnum.LEFT);

        Assert.assertFalse(canMove);
    }

    @Test
    public void check_sRight_shouldFalse() throws CloneNotSupportedException {
        Snapshot snapshot = new Snapshot("4,5|C,C1,C2,C3,V,1H1,1V3,1V,3S1,3V3,3");
        Opening opening = new Opening(snapshot);
        Game game = new Game(opening);
        SquareBlock s = game.selectOnly(SquareBlock.class);

        boolean canMove = new MoveRule().check(game, s, MoveDirectionEnum.RIGHT);

        Assert.assertFalse(canMove);
    }
}