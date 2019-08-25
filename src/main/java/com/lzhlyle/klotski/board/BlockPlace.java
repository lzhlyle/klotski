package com.lzhlyle.klotski.board;

import com.lzhlyle.klotski.block.Block;

import java.util.Objects;

public class BlockPlace {
    private Cell cell;
    private Block block;

    public BlockPlace(Cell cell, Block block) {
        this.cell = cell;
        this.block = block;
    }
}
