package com.lzhlyle.klotski.board;

import com.lzhlyle.klotski.block.Block;

public class BlockPlace {
    /**
     * 最左下角的格子(距离原点最近)，西南方格子
     */
    private Cell southwestCell;
    private Block block;

    public BlockPlace(Cell southwestCell, Block block) {
        this.southwestCell = southwestCell;
        this.southwestCell.occupy();
        this.block = block;
    }

    public Cell getSouthwestCell() {
        return southwestCell;
    }

    public Block getBlock() {
        return block;
    }
}
