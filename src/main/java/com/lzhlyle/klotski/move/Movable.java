package com.lzhlyle.klotski.move;

public interface Movable {

    /**
     * Move
     *
     * @param direction which direction dose the first step move relative to the current position
     * @param toEnd     whether move to the end or not
     * @return movable or not
     */
    boolean move(MoveDirection direction, boolean toEnd);
}
