package com.lzhlyle.klotski.rule;

import com.lzhlyle.klotski.block.SquareBlock;
import com.lzhlyle.klotski.board.BlockPlace;

import java.util.List;

public class WinRule extends Rule {
    public boolean check(List<BlockPlace> blockPlaceList) {
        // is the square block at (1, 0)
        return blockPlaceList.stream().anyMatch(bp -> (bp.getBlock() instanceof SquareBlock)
                && bp.getSouthwestCell().getLocation().equals(1, 0));
    }
}
