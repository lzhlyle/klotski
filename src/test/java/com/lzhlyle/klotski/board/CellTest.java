package com.lzhlyle.klotski.board;

import org.junit.Assert;
import org.junit.Test;

public class CellTest {

    @Test
    public void clone_override_objectShouldNotEquals() throws CloneNotSupportedException {
        Cell prototype = new Cell();
        Cell cloned = prototype.clone();
        Assert.assertNotEquals(prototype, cloned);
    }

    @Test
    public void clone_override_propertiesShouldEquals() throws CloneNotSupportedException {
        Cell prototype = new Cell();
        Cell cloned = prototype.clone();
        Assert.assertEquals(prototype.getLocation(), cloned.getLocation());
    }
}