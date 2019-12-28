package com.lzhlyle.klotski.solver;

import java.util.*;

public class QuickSolver4Blog {
    // 开局棋盘
    private int[] standard;

    // 确定开局，以横刀立马为例
    private QuickSolver4Blog() {
        standard = new int[10];
        standard[0] = 0b0110_0110_0000_0000_0000; // Square 大方块曹操
        standard[1] = 0b0000_0000_0110_0000_0000; // Horizon 横放关羽
        standard[2] = 0b1000_1000_0000_0000_0000; // Vertical 竖放武将
        standard[3] = 0b0001_0001_0000_0000_0000; // Vertical 竖放武将
        standard[4] = 0b0000_0000_1000_1000_0000; // Vertical 竖放武将
        standard[5] = 0b0000_0000_0001_0001_0000; // Vertical 竖放武将
        standard[6] = 0b0000_0000_0000_0000_1000; // Cube 小兵
        standard[7] = 0b0000_0000_0000_0100_0000; // Cube 小兵
        standard[8] = 0b0000_0000_0000_0010_0000; // Cube 小兵
        standard[9] = 0b0000_0000_0000_0000_0001; // Cube 小兵
    }

    // 传入开局棋盘
    // 返回最小步数
    public int minSteps(int[] opening) {
        // 要找的目标
        int target = 0b0000_0000_0000_0110_0110;

        // 初始层的所有可能，存入队列
        Queue<int[]> queue = new LinkedList<>(Collections.singleton(opening));

        // 分身走过路口的记录
        Set<Long> visited = new HashSet<>(Collections.singleton(this.compress(opening)));

        // 路径记录
        Map<int[], int[]> paths = new HashMap<>();
        paths.put(opening, null);

        return this.bfs(0, queue, target, visited, paths); // 传入跟踪者
    }

    // 广度优先搜索
    // 支持传入跟踪者
    private int bfs(int step, Queue<int[]> queue, int target, Set<Long> visited, Map<int[], int[]> paths) {
        // terminator 递归终止条件
        if (queue.isEmpty()) return -1; // 所有路口都已经无路可走，找不到

        // process 当前这批分身要做的事
        step++; // 走一批，计数一次
        Queue<int[]> nextQueue = new LinkedList<>(); // 下一批可走的所有分岔
        // 这批每个分身都有平等机会 同步向前
        while (!queue.isEmpty()) {
            int[] current = queue.remove(); // 逐个分身走
            // 看看这个路口有几条分岔
            List<int[]> possibilities = this.getPossibilities(current);
            for (int[] possibility : possibilities) {
                // pruning: visited 看看哪些分岔其他分身走过了
                if (visited.contains(this.compress(possibility))) continue; // 若走过，则跳过不再考虑

                // can be possible
                paths.put(possibility, current); // 跟踪者执行记录

                // 下一条路就是终点，则返回经过了多少批分身（即多少个路口）
                if (possibility[0] == target) {
                    output(paths, possibility); // 解开注释时，记得确认运行次数
                    return step; // win
                }

                // 若不是终点，则入选下一轮分岔路队列
                nextQueue.add(possibility);

                // 记录这个路口已经有影分身占位准备要往前走了，其他分身不必再来
                visited.add(this.compress(possibility));
                // 同时，镜像路口也算走过，不必再走
                visited.add(this.compress(this.getMirror(possibility)));
            }
        }

        // drill down 所有分身接着向前走
        return this.bfs(step, nextQueue, target, visited, paths); // 传入跟踪者
    }

    // 输出路径
    private void output(Map<int[], int[]> paths, int[] curr) {
        // 先利用栈的先进后出，倒序存行进步骤
        Stack<int[]> stack = new Stack<>();
        while (paths.get(curr) != null) {
            stack.push(curr);
            curr = paths.get(curr);
        }

        System.out.println("========================");
        System.out.println("开局");
        printLayout(curr);

        int count = 0;
        while (!stack.isEmpty()) {
            System.out.println("第 " + (++count) + " 步");
            int[] b = stack.pop();
            printLayout(b);

            // 输出镜像棋局
//            System.out.println("mirror:");
//            printLayout(this.getMirror(b));
        }
    }

    // 输出棋局
    private void printLayout(int[] curr) {
        // 每个棋子，每个1都以字符表示
        char[] symbol = new char[]{'S', 'H', 'V', 'V', 'V', 'V', 'C', 'C', 'C', 'C'};
        String[] bStrArr = new String[10];
        // 逐棋子补0后转String
        for (int i = 0; i < 10; i++) {
            StringBuilder builder = new StringBuilder(Integer.toBinaryString(curr[i]));
            int length = builder.length();
            for (int j = 0; j < 20 - length; j++) builder.insert(0, "0");
            bStrArr[i] = builder.toString().replace('1', symbol[i]);
        }

        // 将棋子存入五行四列的20个格子中
        char[] inline = new char[20];
        for (int i = 0; i < 20; i++) {
            for (String bStr : bStrArr) {
                if (bStr.charAt(i) != '0') { inline[i] = bStr.charAt(i); break; }
            }
        }

        // 逐行输出
        for (int i = 0; i < 5; i++) {
            int j = i * 4;
            System.out.print(inline[j++]);
            System.out.print(inline[j++]);
            System.out.print(inline[j++]);
            System.out.print(inline[j]);
            System.out.println();
        }
        System.out.println();
    }

    // 返回棋局的镜像棋局
    private int[] getMirror(int[] blocks) {
        int len = blocks.length;
        int[] mirror = new int[len];
        // 逐个棋子找镜像位置，放入镜像棋局
        for (int i = 0; i < len; i++) {
            // 最左边的，右移2（曹操/关羽——横向已占2格的）或3格（非曹操关羽——横向只占1格的）
            // 1100 -> 0011, 1000 -> 0001
            if (this.isLeftBorder(blocks[i])) mirror[i] = blocks[i] >> (i < 2 ? 2 : 3);
                // 最右边，左移2或3格
                // 0011 -> 1100, 0001 -> 1000
            else if (this.isRightBorder(blocks[i])) mirror[i] = blocks[i] << (i < 2 ? 2 : 3);
                // 非曹操/关羽时（曹/关在中间时就是镜像位），可能需左/右移1格
            else if (i > 1) {
                // 左移1格到边的话，那么镜像位为右移1格
                // 0100 -> 0010
                if (this.isLeftBorder(blocks[i] << 1)) mirror[i] = blocks[i] >> 1;
                // 右移1格到边的话，那么镜像位为左移1格
                // 0010 -> 0100
                if (this.isRightBorder(blocks[i] >> 1)) mirror[i] = blocks[i] << 1;
            }
            // 当前与镜像同位置，直接放入
            // 0110 -> 0110
            else mirror[i] = blocks[i];
        }
        return mirror;
    }

    // 查找当前棋局的、所有可能的、移动后的棋局
    private List<int[]> getPossibilities(int[] blocks) {
        List<int[]> possibilities = new ArrayList<>();
        // move 逐个棋子试着移动，移动有效则计入可能的移动
        for (int i = 0; i < blocks.length; i++) {
            // 记录棋子原来位置，最后需还原
            int original = blocks[i];

            // up 试着向上移动
            blocks[i] = original << 4; // up
            if (validate(blocks)) {
                possibilities.add(blocks.clone());

                // 非曹操才可能连续移动
                if (i != 0) {
                    blocks[i] = original << 8; // up 2 cells
                    if (validate(blocks)) possibilities.add(blocks.clone());
                }

                // 小兵才可能拐点移动
                if (i > 5 && !isLeftBorder(original)) {
                    blocks[i] = original << 5; // up left
                    if (validate(blocks)) possibilities.add(blocks.clone());
                }
                if (i > 5 && !isRightBorder(original)) {
                    blocks[i] = original << 3; // up right
                    if (validate(blocks)) possibilities.add(blocks.clone());
                }
            }

            // down 试着向下移动
            blocks[i] = original >> 4; // down
            if (validate(blocks)) {
                possibilities.add(blocks.clone());
                if (i != 0) {
                    blocks[i] = original >> 8; // down 2 cells
                    if (validate(blocks)) possibilities.add(blocks.clone());
                }
                if (i > 5 && !isLeftBorder(original)) {
                    blocks[i] = original >> 3; // down left
                    if (validate(blocks)) possibilities.add(blocks.clone());
                }
                if (i > 5 && !isRightBorder(original)) {
                    blocks[i] = original >> 5; // down right
                    if (validate(blocks)) possibilities.add(blocks.clone());
                }
            }

            // 若到最左边，则不可再左移动
            // left 试着向左移动
            if (!isLeftBorder(original)) {
                blocks[i] = original << 1; // left
                if (validate(blocks)) {
                    possibilities.add(blocks.clone());

                    if (i != 0 && !isLeftBorder(original << 1)) {
                        blocks[i] = original << 2; // left 2 cells
                        if (validate(blocks)) possibilities.add(blocks.clone());
                    }
                    if (i > 5) {
                        blocks[i] = original << 5; // left up
                        if (validate(blocks)) possibilities.add(blocks.clone());

                        blocks[i] = original >> 3; // left down
                        if (validate(blocks)) possibilities.add(blocks.clone());
                    }
                }
            }

            // 若到最右边，则不可再右移动
            // right 试着向右移动
            if (!isRightBorder(original)) {
                blocks[i] = original >> 1; // right
                if (validate(blocks)) {
                    possibilities.add(blocks.clone());

                    if (i != 0 && !isRightBorder(original >> 1)) {
                        blocks[i] = original >> 2; // right 2 cells
                        if (validate(blocks)) possibilities.add(blocks.clone());
                    }
                    if (i > 5) {
                        blocks[i] = original << 3; // right up
                        if (validate(blocks)) possibilities.add(blocks.clone());

                        blocks[i] = original >> 5; // right down
                        if (validate(blocks)) possibilities.add(blocks.clone());
                    }
                }
            }

            // reverse 还原这颗棋子的位置
            blocks[i] = original;
        }

        return possibilities;
    }

    // 是否靠右边
    private boolean isRightBorder(int block) {
        // 右边界数组，二进制对低位为0算起，0表示最下一行的右边界
        // 依此类推，4, 8, 12, 16 表示其他各行的右边界
        int[] rightBorders = new int[]{0, 4, 8, 12, 16};
        for (int rightBorder : rightBorders) if (((block >> rightBorder) & 1) == 1) return true;
        return false;
    }

    // 是否靠左边
    private boolean isLeftBorder(int block) {
        // 左边界数组，二进制对低位为0算起，3表示最下一行的左边界
        // 依此类推，7, 11, 15, 19 表示其他各行的左边界
        int[] leftBorders = new int[]{3, 7, 11, 15, 19};
        for (int leftBorder : leftBorders) if (((block >> leftBorder) & 1) == 1) return true;
        return false;
    }

    // 校验棋盘有效性
    private boolean validate(int[] blocks) {
        if (blocks.length != 10) return false; // 缺少棋子
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

    // 压缩棋局
    private Long compress(int[] blocks) {
        int[] temp = new int[10];
        temp[0] = blocks[0]; // 曹操这形状棋子就一个，无需排序
        temp[1] = blocks[1]; // 关羽这形状棋子就一个，无需排序

        // 对同vertical形状的四虎将排序
        int[] vArr = new int[]{blocks[2], blocks[3], blocks[4], blocks[5]};
        Arrays.sort(vArr);
        temp[2] = vArr[0];
        temp[3] = vArr[1];
        temp[4] = vArr[2];
        temp[5] = vArr[3];

        // 对同cube形状的小兵排序
        int[] cArr = new int[]{blocks[6], blocks[7], blocks[8], blocks[9]};
        Arrays.sort(cArr);
        temp[6] = cArr[0];
        temp[7] = cArr[1];
        temp[8] = cArr[2];
        temp[9] = cArr[3];

        // eg: 1100_1100_0000_0000_0000 = (1<<19) | (1<<18) | (1<<15) | (1<<14)
        // 最大的最低位为19:10011,10个block共 5*10=50 bit即可
        long res = 0b0; // 64 bit
        // 遍历棋子
        for (int i = 0; i < temp.length; i++) {
            // 找最低位的1的位置
            long lowest = 0; // 64 bit
            for (int n = 0; n < 20; n++) {
                if (((temp[i] >> n) & 1) == 1) {
                    lowest = n;
                    break;
                }
            }
            // 左移后并入，左移5是跨过上一个棋子已经并入的5个位
            res |= (lowest << (i * 5));
        }
        return res;
    }

    public static void main(String[] args) {
        QuickSolver4Blog solver = new QuickSolver4Blog();
        long sum = 0;
        int times = 1; // 输出路劲时可改为1
        for (int i = 0; i < times; i++) {
            Date begin = new Date();
            int res = solver.minSteps(solver.standard);
            Date end = new Date();
            sum += end.getTime() - begin.getTime();
        }
        System.out.println("average millis: " + sum / times);
    }
}
