package com.lzhlyle.klotski.move;

import com.lzhlyle.klotski.block.Block;

@FunctionalInterface
public interface Movement {

    /**
     * How to move
     *
     * @param block
     * @param direction which direction dose the first move move relative to the current position
     * @return movable or not
     */
    void move(Block block, MoveDirectionEnum direction);
}
