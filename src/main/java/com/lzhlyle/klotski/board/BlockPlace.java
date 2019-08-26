package com.lzhlyle.klotski.board;

import com.lzhlyle.klotski.block.Block;

public class BlockPlace {
    private Cell cell;
    private Block block;

    public BlockPlace(Cell cell, Block block) {
        this.cell = cell;
        this.block = block;
    }

    public Cell getCell() {
        return cell;
    }

    public Block getBlock() {
        return block;
    }
}
