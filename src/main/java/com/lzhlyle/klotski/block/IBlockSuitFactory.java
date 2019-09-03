package com.lzhlyle.klotski.block;

import java.util.List;

public interface IBlockSuitFactory {
    List<Block> createBlockSuit() throws CloneNotSupportedException;
}
