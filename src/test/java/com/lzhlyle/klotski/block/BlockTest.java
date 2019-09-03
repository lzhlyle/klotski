package com.lzhlyle.klotski.block;

import org.junit.Assert;
import org.junit.Test;

public class BlockTest {

    @Test
    public void clone_override_objectShouldNotEquals() throws CloneNotSupportedException {
        Block prototype = new SquareBlock();
        Block cloned = prototype.clone();
        Assert.assertNotEquals(prototype, cloned);
    }

    @Test
    public void clone_override_objectClassShouldEquals() throws CloneNotSupportedException {
        Block prototype = new VerticalBlock();
        Block cloned = prototype.clone();
        Assert.assertEquals(prototype.getClass(), cloned.getClass());
    }

    @Test
    public void clone_override_propertiesShouldEquals() throws CloneNotSupportedException {
        Block prototype = new HorizontalBlock();
        Block cloned = prototype.clone();
        Assert.assertEquals(prototype.getHeight(), cloned.getHeight());
        Assert.assertEquals(prototype.getWidth(), cloned.getWidth());
    }
}