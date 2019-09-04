package com.lzhlyle.klotski.block;

import java.util.ArrayList;
import java.util.List;

public class StandardBlockSuitFactory implements IBlockSuitFactory {
    @Override
    public List<Block> createBlockSuit() throws CloneNotSupportedException {
        List<Block> blockSuit = new ArrayList<>();

        blockSuit.add(new SquareBlock());
        blockSuit.add(new HorizontalBlock());
        blockSuit.addAll(Block.generate(4, VerticalBlock::new));
        blockSuit.addAll(Block.generate(4, CubeBlock::new));

        return blockSuit;
    }
}
