package com.lzhlyle.klotski.block;

import com.lzhlyle.klotski.move.IMovable;
import com.lzhlyle.klotski.move.MoveDirection;

public abstract class Block implements IMovable {
    private int height;
    private int width;

    public Block(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public boolean move(MoveDirection direction) {
        return false;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
