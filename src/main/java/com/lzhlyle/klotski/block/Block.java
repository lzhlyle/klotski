package com.lzhlyle.klotski.block;

import com.lzhlyle.klotski.move.MoveDirectionEnum;
import com.lzhlyle.klotski.move.Movement;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public abstract class Block implements Cloneable {
    private int height;
    private int width;

    public Block(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public void move(Movement movement, MoveDirectionEnum direction) {
        movement.move(this, direction);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public static <B extends Block> List<B> generate(int count, Supplier<B> constr)
            throws CloneNotSupportedException {
        B prototype = constr.get();
        List<B> blockList = new ArrayList<>();
        blockList.add(prototype);
        for (int i = 0; i < count - 1; i++) {
            blockList.add((B) prototype.clone());
        }
        return blockList;
    }

    @Override
    protected Block clone() throws CloneNotSupportedException {
        return (Block) super.clone();
    }
}
