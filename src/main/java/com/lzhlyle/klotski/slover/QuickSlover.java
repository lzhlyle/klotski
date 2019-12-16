package com.lzhlyle.klotski.slover;

import java.util.Arrays;

public class QuickSlover {
    // 总是按 SHVVVVCCCC 顺序
    // 横刀立马布局
    // S: 0b0110_0110_0000_0000_0000 : 二进制_第一行占中间两格子_以此类推
    private int[] standard;

    private QuickSlover() {
        standard = new int[10];
        standard[0] = 0b0110_0110_0000_0000_0000; // S
        standard[1] = 0b0000_0000_0000_0110_0000; // H
        standard[2] = 0b1000_1000_0000_0000_0000; // V
        standard[3] = 0b0001_0001_0000_0000_0000; // V
        standard[4] = 0b0000_0000_1000_1000_0000; // V
        standard[5] = 0b0000_0000_0001_0001_0000; // V
        standard[6] = 0b0000_0000_0000_0000_1000; // C
        standard[7] = 0b0000_0000_0000_0000_0100; // C
        standard[8] = 0b0000_0000_0000_0000_0010; // C
        standard[9] = 0b0000_0000_0000_0000_0001; // C
    }

    public int minSteps(int[] snapshot) {
        // target: snapshot[0] = 0b0000_0000_0000_0110_0110;
        return -1;
    }

    /**
     * 检测棋盘是否有效
     *
     * @param snapshot
     * @return
     */
    private boolean validate(int[] snapshot) {
        if (snapshot.length != 10) return false; // 少子

        // TODO lzh 每个block判断是否符合形状

        int occupying = 0b0; // 已占位的
        for (int block : snapshot) {
            if (block > ((1 << 20) - 1) || block < 1) return false; // 越界
            if ((occupying & block) != 0) return false; // 重叠
            occupying |= block; // 占位累计
        }
        return true;
    }

    public static void main(String[] args) {
        QuickSlover slover = new QuickSlover();
        System.out.println(Arrays.toString(slover.standard));
        System.out.println(slover.validate(slover.standard));
    }
}
