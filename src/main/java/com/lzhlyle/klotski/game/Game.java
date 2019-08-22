package com.lzhlyle.klotski.game;

import com.lzhlyle.klotski.block.CubeBlock;
import com.lzhlyle.klotski.block.HorizontalBlock;
import com.lzhlyle.klotski.block.SquareBlock;
import com.lzhlyle.klotski.block.VerticalBlock;
import com.lzhlyle.klotski.board.Board;
import com.lzhlyle.klotski.pedometer.Pedometer;
import com.lzhlyle.klotski.quide.Quide;
import com.lzhlyle.klotski.regulation.Regulation;

import java.util.List;

public class Game {
    private Board board;

    private List<CubeBlock> cubeBlockList;
    private List<HorizontalBlock> horizontalBlockList;
    private List<VerticalBlock> verticalBlockList;
    private SquareBlock squareBlock;

    private Pedometer pedometer;

    private Regulation moveRegulation;

    private Quide quide;

    private GameStatus status;

    public Game() {
        this.status = GameStatus.READY;
    }

    public boolean start() {
        return false;
    }
}
