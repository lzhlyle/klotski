package com.lzhlyle.klotski.block;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

public class StandardBlockSuitFactoryTest {

    @Test
    public void createBlockSuit_any_should10Blocks() throws CloneNotSupportedException {
        StandardBlockSuitFactory blockSuitFactory = new StandardBlockSuitFactory();

        List<Block> blockSuit = blockSuitFactory.createBlockSuit();

        Assert.assertEquals(10, blockSuit.size());
        Assert.assertTrue(this.containsIn(blockSuit, SquareBlock.class, 1));
        Assert.assertTrue(this.containsIn(blockSuit, HorizontalBlock.class, 1));
        Assert.assertTrue(this.containsIn(blockSuit, VerticalBlock.class, 4));
        Assert.assertTrue(this.containsIn(blockSuit, CubeBlock.class, 4));
    }

    private boolean containsIn(List<Block> blockSuit, Class c, int count) {
        return blockSuit.stream().filter(b -> Objects.equals(b.getClass(), c)).count() == count;
    }

    @Test
    public void createBlockSuit_any_shouldDiffBlockObjs() throws CloneNotSupportedException {
        StandardBlockSuitFactory blockSuitFactory = new StandardBlockSuitFactory();

        List<Block> blockSuit = blockSuitFactory.createBlockSuit();

        for (int i = 0; i < blockSuit.size(); i++) {
            for (int j = 0; j < blockSuit.size(); j++) {
                if (i != j) {
                    Assert.assertNotEquals(blockSuit.get(i), blockSuit.get(j));
                }
            }
        }
    }
}