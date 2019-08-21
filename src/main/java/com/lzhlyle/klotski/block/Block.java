package com.lzhlyle.klotski.block;

import com.lzhlyle.klotski.move.Movable;
import com.lzhlyle.klotski.move.MoveDirection;

public abstract class Block implements Movable {

    public boolean move(MoveDirection direction, boolean toEnd) {
        return false;
    }
}
