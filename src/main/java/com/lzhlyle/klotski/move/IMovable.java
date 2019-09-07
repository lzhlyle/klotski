package com.lzhlyle.klotski.move;

@FunctionalInterface
public interface IMovable {

    /**
     * Move
     *
     * @param direction which direction dose the first move move relative to the current position
     * @return movable or not
     */
    void move(MoveDirectionEnum direction);
}
