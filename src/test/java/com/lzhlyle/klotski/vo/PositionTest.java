package com.lzhlyle.klotski.vo;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class PositionTest {

    @Test
    public void equals1() {
        Random rd = new Random();
        int x = rd.nextInt();
        int y = rd.nextInt();
        Position pos1 = new Position(x, y);
        Position pos2 = new Position(x, y);

        assertEquals(pos1, pos2);
    }

    @Test
    public void hashCode1() {
        Random rd = new Random();
        int x = rd.nextInt();
        int y = rd.nextInt();
        Position pos1 = new Position(x, y);
        Position pos2 = new Position(x, y);

        assertEquals(pos1.hashCode(), pos2.hashCode());
    }

    @Test
    public void toString1() {
        Random rd = new Random();
        int x = rd.nextInt();
        int y = rd.nextInt();
        Position pos1 = new Position(x, y);
        Position pos2 = new Position(x, y);

        assertEquals(pos1.toString(), pos2.toString());

        System.out.println(pos1.toString());
    }
}