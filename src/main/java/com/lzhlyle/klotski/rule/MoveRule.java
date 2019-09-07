package com.lzhlyle.klotski.rule;

import com.lzhlyle.klotski.block.Block;
import com.lzhlyle.klotski.board.BlockPlace;
import com.lzhlyle.klotski.move.MoveDirectionEnum;

import java.util.List;

public class MoveRule extends Rule {
    @Override
    public boolean check(List<BlockPlace> before, Block mover, MoveDirectionEnum direction) {
        // TODO lzh calculate after cells
        return false;
    }
}
