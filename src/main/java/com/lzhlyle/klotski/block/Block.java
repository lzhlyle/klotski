package com.lzhlyle.klotski.block;

import com.lzhlyle.klotski.move.IMovable;
import com.lzhlyle.klotski.move.MoveDirection;

public abstract class Block implements IMovable, Cloneable {
    private int height;
    private int width;

    public Block(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public boolean move(MoveDirection direction) {
        // TODO lzh block.move()
        return false;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    protected Block clone() throws CloneNotSupportedException {
        return (Block) super.clone();
    }
}
