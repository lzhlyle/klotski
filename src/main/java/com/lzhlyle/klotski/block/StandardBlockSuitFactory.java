package com.lzhlyle.klotski.block;

import java.util.ArrayList;
import java.util.List;

public class StandardBlockSuitFactory implements IBlockSuitFactory {
    @Override
    public List<Block> createBlockSuit() throws CloneNotSupportedException {
        List<Block> blockSuit = new ArrayList<>();

        blockSuit.add(new SquareBlock());
        blockSuit.add(new HorizontalBlock());

        VerticalBlock vBlock = new VerticalBlock();
        blockSuit.add(vBlock);
        for (int i = 0; i < 3; i++) {
            blockSuit.add(vBlock.clone());
        }

        CubeBlock cBlock = new CubeBlock();
        blockSuit.add(cBlock);
        for (int i = 0; i < 3; i++) {
            blockSuit.add(cBlock.clone());
        }

        return blockSuit;
    }
}
