package com.lzhlyle.klotski.solver;

import com.lzhlyle.klotski.vo.Duration;

import java.util.*;

public class QuickSolverIV {
    // 总是按 SHVVVVCCCC 顺序
    // 横刀立马布局
    // S: 0b0110_0110_0000_0000_0000 : 二进制_第一行占中间两格子_以此类推
    // target: snapshot[0] = 0b0000_0000_0000_0110_0110;
    private int[] standard;

    private QuickSolverIV() {
        standard = new int[10];
        standard[0] = 0b0110_0110_0000_0000_0000; // S
        standard[1] = 0b0000_0000_0110_0000_0000; // H
        standard[2] = 0b1000_1000_0000_0000_0000; // V
        standard[3] = 0b0001_0001_0000_0000_0000; // V
        standard[4] = 0b0000_0000_1000_1000_0000; // V
        standard[5] = 0b0000_0000_0001_0001_0000; // V
        standard[6] = 0b0000_0000_0000_0000_1000; // C
        standard[7] = 0b0000_0000_0000_0100_0000; // C
        standard[8] = 0b0000_0000_0000_0010_0000; // C
        standard[9] = 0b0000_0000_0000_0000_0001; // C
    }

    public int minSteps(int[] opening) {
        int target = 0b0000_0000_0000_0110_0110;
        Queue<int[]> queue = new LinkedList<>(Collections.singleton(opening));
        Set<Long> visited = new HashSet<>(Collections.singleton(this.compress(opening)));
        Map<int[], int[]> paths = new HashMap<>();
        paths.put(opening, null);
        return this.bfs(0, queue, target, visited, paths);
    }

    private int bfs(int step, Queue<int[]> queue, int target, Set<Long> visited, Map<int[], int[]> paths) {
        // terminator
        if (queue.isEmpty()) return -1; // cannot reach
        // process
        step++;
        // heuristic search
        Queue<int[]> allNextQueue = new PriorityQueue<>(Comparator.comparingInt((int[] b) -> Math.abs(target - b[0])));
        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            // look up next queue
            List<int[]> possibilities = this.getPossibilities(curr);
            for (int[] possibility : possibilities) {
                // pruning: visited
                if (visited.contains(this.compress(possibility))) continue;
                if (possibility[0] == target) {
                    duration.stop();
                    paths.put(possibility, curr);
                    return step; // win
                }
                allNextQueue.add(possibility);
                visited.add(this.compress(possibility));
//                visited.add(this.compress(this.getMirror(possibility)));
                paths.put(possibility, curr);
            }
        }
        Queue<int[]> nextQueue = new LinkedList<>();
        int size = Math.min(allNextQueue.size(), 29000);
        for (int i = 0; i < size; i++) nextQueue.add(allNextQueue.remove());
        // drill down
        return this.bfs(step, nextQueue, target, visited, paths);
    }

    // O(n^2)
    private List<int[]> getPossibilities(int[] blocks) {
        List<int[]> result = new ArrayList<>();
        // move
        for (int i = 0; i < blocks.length; i++) {
            int original = blocks[i];
            // up
            blocks[i] = original << 4; // up
            if (validate(blocks)) {
                result.add(blocks.clone());
                if (i != 0) {
                    blocks[i] = original << 8; // up 2 cells
                    if (validate(blocks)) result.add(blocks.clone());
                }
                if (i > 5 && !this.isLeftBorder(original)) {
                    blocks[i] = original << 5; // up left
                    if (validate(blocks)) result.add(blocks.clone());
                }
                if (i > 5 && !this.isRightBorder(original)) {
                    blocks[i] = original << 3; // up right
                    if (validate(blocks)) result.add(blocks.clone());
                }
            }
            // down
            blocks[i] = original >> 4; // down
            if (validate(blocks)) {
                result.add(blocks.clone());
                if (i != 0) {
                    blocks[i] = original >> 8; // down 2 cells
                    if (validate(blocks)) result.add(blocks.clone());
                }
                if (i > 5 && !this.isLeftBorder(original)) {
                    blocks[i] = original >> 3; // down left
                    if (validate(blocks)) result.add(blocks.clone());
                }
                if (i > 5 && !this.isRightBorder(original)) {
                    blocks[i] = original >> 5; // down right
                    if (validate(blocks)) result.add(blocks.clone());
                }
            }
            // 若到边(最高/低位的1)，则不可再左右移动
            // left
            if (!this.isLeftBorder(original)) {
                blocks[i] = original << 1; // left
                if (validate(blocks)) {
                    result.add(blocks.clone());

                    if (i != 0 && !this.isLeftBorder(original << 1)) {
                        blocks[i] = original << 2; // left 2 cells
                        if (validate(blocks)) result.add(blocks.clone());
                    }
                    if (i > 5) {
                        blocks[i] = original << 5; // left up
                        if (validate(blocks)) result.add(blocks.clone());

                        blocks[i] = original >> 3; // left down
                        if (validate(blocks)) result.add(blocks.clone());
                    }
                }
            }
            // right
            if (!this.isRightBorder(original)) {
                blocks[i] = original >> 1; // right
                if (validate(blocks)) {
                    result.add(blocks.clone());

                    if (i != 0 && !this.isRightBorder(original >> 1)) {
                        blocks[i] = original >> 2; // right 2 cells
                        if (validate(blocks)) result.add(blocks.clone());
                    }
                    if (i > 5) {
                        blocks[i] = original << 3; // right up
                        if (validate(blocks)) result.add(blocks.clone());

                        blocks[i] = original >> 5; // right down
                        if (validate(blocks)) result.add(blocks.clone());
                    }
                }
            }
            // reverse
            blocks[i] = original;
        }
        return result;
    }

    // O(5)
    private boolean isLeftBorder(int block) {
        int[] leftBorders = new int[]{3, 7, 11, 15, 19};
        for (int leftBorder : leftBorders) if (((block >> leftBorder) & 1) == 1) return true;
        return false;
    }

    // O(5)
    private boolean isRightBorder(int block) {
        int[] rightBorders = new int[]{0, 4, 8, 12, 16};
        for (int rightBorder : rightBorders) if (((block >> rightBorder) & 1) == 1) return true;
        return false;
    }

    // O(20n)
    private long compress(int[] blocks) {
        // max block: 1100_1100_0000_0000_0000 = (1<<19) + (1<<18) + (1<<15) + (1<<14)
        // 最大的最低位为19:10011,10个block共 5*10=50 bit即可
        long res = 0b0; // 64 bit
        // each block
        for (int i = 0; i < blocks.length; i++) {
            // 找最低位的1的位置
            long pos = 0; // 64 bit
            for (int j = 1; j < 20; j++) {
                if (((blocks[i] >> j) << j) != blocks[i]) {
                    pos = j - 1;
                    break;
                }
            }
            res |= (pos << (i * 5));
        }
        return res;
    }

    // 检测棋盘有效性
    // O(n)
    private boolean validate(int[] blocks) {
        if (blocks.length != 10) return false; // 少子
        int occupying = 0b0; // 已占位的
        for (int block : blocks) {
            if (block > ((1 << 20) - 1)) return false; // 越上界
            if ((occupying & block) != 0) return false; // 重叠
            occupying |= block; // 占位累计
        }
        // 消除最低位的17个1后，应该还有1——越下界
        for (int i = 0; i < 17; i++) occupying &= occupying - 1;
        return occupying != 0;
    }

    // O(n)
    private int[] getMirror(int[] blocks) {
        int len = blocks.length;
        int[] mirror = new int[len];
        for (int i = 0; i < len; i++) {
            if (this.isLeftBorder(blocks[i])) mirror[i] = blocks[i] >> (i < 2 ? 2 : 3);
            else if (this.isRightBorder(blocks[i])) mirror[i] = blocks[i] << (i < 2 ? 2 : 3);
            else if (i > 1) {
                if (this.isLeftBorder(blocks[i] << 1)) mirror[i] = blocks[i] >> 1;
                if (this.isRightBorder(blocks[i] >> 1)) mirror[i] = blocks[i] << 1;
            } else mirror[i] = blocks[i];
        }
        return mirror;
    }

    private static Duration duration;

    public static void main(String[] args) {
        QuickSolverIV solver = new QuickSolverIV();
        duration = new Duration();
        int res = solver.minSteps(solver.standard);
        System.out.println(res);
        System.out.println("millis: " + duration.getDurationMillis());
    }
}
