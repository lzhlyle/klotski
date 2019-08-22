package com.lzhlyle.klotski.move;

public interface IMovable {

    /**
     * Move
     *
     * @param direction which direction dose the first step move relative to the current position
     * @return movable or not
     */
    boolean move(MoveDirection direction);
}
