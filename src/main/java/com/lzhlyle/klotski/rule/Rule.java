package com.lzhlyle.klotski.rule;

import com.lzhlyle.klotski.block.Block;
import com.lzhlyle.klotski.board.BlockPlace;
import com.lzhlyle.klotski.move.MoveDirectionEnum;

import java.util.List;

public abstract class Rule {

    public abstract boolean check(List<BlockPlace> before, Block mover, MoveDirectionEnum direction);
}
