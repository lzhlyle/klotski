package com.lzhlyle.klotski.solver;

import java.util.*;

public class QuickSolver {
    // 总是按 SHVVVVCCCC 顺序
    // 横刀立马布局
    // S: 0b0110_0110_0000_0000_0000 : 二进制_第一行占中间两格子_以此类推
    // target: snapshot[0] = 0b0000_0000_0000_0110_0110;
    private int[] standard;
    private int maxSize;
    private int divideNum;

    private QuickSolver() {
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
        Set<BitSet> visited = new HashSet<>(Collections.singleton(this.compress(opening)));

        Map<int[], int[]> quide = new HashMap<>();
        quide.put(opening, null);

        return this.bfs(0, queue, target, visited, quide);
    }

    private int bfs(int step, Queue<int[]> queue, int target, Set<BitSet> visited, Map<int[], int[]> quide) {
        // terminator
        if (queue.isEmpty()) return -1; // cannot reach

        // process
        step++;
        System.out.println(step + ": " + queue.size() + ": " + visited.size()); // 21: 1260183: 2942392

        // heuristic search
        Queue<int[]> allNextQueue = new PriorityQueue<>(Comparator.comparingInt((int[] b) -> Math.abs(target - b[0])));
        while (!queue.isEmpty()) {
            int[] curr = queue.remove();

            // look up next queue
            List<int[]> possibilities = this.getPossibilites(curr);
            for (int[] possibility : possibilities) {
                // pruning: visited
                if (visited.contains(this.compress(possibility))) continue;
                if (possibility[0] == target) {
                    this.print(possibility);
                    System.out.println("=======================");
                    this.output(quide, curr);
                    return step; // win
                }

                allNextQueue.add(possibility);
                visited.add(this.compress(possibility));
                quide.put(possibility, curr);
            }
        }

        Queue<int[]> nextQueue = new LinkedList<>();
        int size = Math.min(allNextQueue.size(), 20000); // magic: 18222
//        if (allNextQueue.size() > 1000) System.out.println("size: " + size + ", all: " + allNextQueue.size());
        for (int i = 0; i < size; i++) {
            nextQueue.add(allNextQueue.remove());
        }

        // drill down
        return this.bfs(step, nextQueue, target, visited, quide);
    }

    private void output(Map<int[], int[]> quide, int[] curr) {
        int count = 0;
        while (quide.get(curr) != null) {
            this.print(curr);
            if (!this.isMovingSameBlock(curr, quide.get(curr), quide.get(quide.get(curr)))) count++;
            System.out.println(count);
            System.out.println();
            curr = quide.get(curr);
        }
        System.out.println(count);
        this.print(curr);
    }

    private boolean isMovingSameBlock(int[] curr, int[] prev, int[] prevPrev) {
        if (prevPrev == null) return true;
        int lastMoving = 0, lastLastMoving = 0;
        for (int i = 0; i < 10; i++) {
            if (curr[i] != prev[i]) lastMoving = i;
            if (prev[i] != prevPrev[i]) lastLastMoving = i;
        }
        return lastMoving == lastLastMoving;
    }

    private void print(int[] curr) {
        int occupying = 0b0;
        for (int b : curr) {
            StringBuilder builder = new StringBuilder(Integer.toBinaryString(b));
            int length = builder.length();
            for (int i = 0; i < 20 - length; i++) builder.insert(0, "0");
            String str = builder.toString();
            System.out.print(str.substring(0, 4) + "_");
            System.out.print(str.substring(4, 8) + "_");
            System.out.print(str.substring(8, 12) + "_");
            System.out.print(str.substring(12, 16) + "_");
            System.out.print(str.substring(16, 20));
            System.out.println();

            occupying |= b;
        }

        StringBuilder builder = new StringBuilder(Integer.toBinaryString(occupying));
        int length = builder.length();
        for (int i = 0; i < 20 - length; i++) builder.insert(0, "0");
        char[] chars = builder.toString().toCharArray();
        // each lines
        for (int i = 0; i < 5; i++) {
            int j = i * 4;
            System.out.print(chars[j++]);
            System.out.print(chars[j++]);
            System.out.print(chars[j++]);
            System.out.print(chars[j]);
            System.out.println();
        }

        System.out.println();
    }

    private List<int[]> getPossibilites(int[] blocks) {
        List<int[]> result = new ArrayList<>();
        // pruning
        // move the blocks near by the empty cells..
//        int empty = 0b0;
//        for (int block : blocks) empty |= block;

        // move
        for (int i = 0; i < blocks.length; i++) {
            int original = blocks[i];

            // up
            blocks[i] = original << 4;
            if (validate(blocks)) {
                result.add(blocks.clone());
                if (i != 0) {
                    blocks[i] = original << 8;
                    if (validate(blocks)) result.add(blocks.clone());
                }
            }

            // down
            blocks[i] = original >> 4;
            if (validate(blocks)) {
                result.add(blocks.clone());
                if (i != 0) {
                    blocks[i] = original >> 8;
                    if (validate(blocks)) result.add(blocks.clone());
                }
            }


            // 若到边(最高/低位的1)，则不可再左右移动

            // left
            if (!this.isLeftBorder(original)) {
                blocks[i] = original << 1;
                if (validate(blocks)) {
                    result.add(blocks.clone());
                    if (i != 0 && !this.isLeftBorder(original << 1)) {
                        blocks[i] = original << 2;
                        if (validate(blocks)) result.add(blocks.clone());
                    }
                }
            }

            // right
            if (!this.isRightBorder(original)) {
                blocks[i] = original >> 1;
                if (validate(blocks)) {
                    result.add(blocks.clone());
                    if (i != 0 && !this.isRightBorder(original >> 1)) {
                        blocks[i] = original >> 2;
                        if (validate(blocks)) result.add(blocks.clone());
                    }
                }
            }

            // reverse
            blocks[i] = original;
        }
        return result;
    }

    private boolean isLeftBorder(int block) {
        int[] leftBorders = new int[]{3, 7, 11, 15, 19};
        for (int leftBorder : leftBorders) {
            if (((block >> leftBorder) & 1) == 1) return true;
        }
        return false;
    }

    private boolean isRightBorder(int block) {
        int[] rightBorders = new int[]{0, 4, 8, 12, 16};
        for (int rightBorder : rightBorders) {
            if (((block >> rightBorder) & 1) == 1) return true;
        }
        return false;
    }

    private BitSet compress(int[] blocks) {
        BitSet bitSet = new BitSet(200);
        // each block
        for (int i = 0; i < blocks.length; i++) {
            // each bit in a block
            for (int j = 0; j < 20; j++) {
                bitSet.set((i * 20) + j, ((blocks[i] >> j) & 1) == 1);
            }
        }
        return bitSet;
    }

    // 检测棋盘有效性
    private boolean validate(int[] blocks) {
        if (blocks.length != 10) return false; // 少子

        // common
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

    public static void main(String[] args) {
        QuickSolver solver = new QuickSolver();
//        int x = solver.standard[0];
//        System.out.println(x & -x);

//        int min = Integer.MAX_VALUE;
//        for (int i = 816; i < 12000; i++) {
//            solver.maxSize = 816;
//            for (int j = 2; j < 5; j++) {
//                solver.divideNum = 5;
        int res = solver.minSteps(solver.standard);
//                System.out.println("maxSize = " + solver.maxSize + ", divideNum = " + solver.divideNum + ", res = " + res);
//                min = Math.min(min, res);
//                System.out.println("min = " + min);
//            }
//        }
        System.out.println(res);

//        System.out.println(Arrays.toString(solver.standard));
//        System.out.println(solver.validate(solver.standard));
//
//        System.out.println(solver.compress(solver.standard));
//
//        System.out.println(solver.compress(solver.standard).equals(solver.compress(solver.standard)));
//
//        System.out.println(solver.getPossibilites(solver.standard));
    }
}
