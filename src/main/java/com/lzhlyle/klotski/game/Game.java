package com.lzhlyle.klotski.game;

import com.lzhlyle.klotski.block.*;
import com.lzhlyle.klotski.board.Board;
import com.lzhlyle.klotski.opening.Opening;
import com.lzhlyle.klotski.pedometer.Pedometer;
import com.lzhlyle.klotski.rule.MoveRule;
import com.lzhlyle.klotski.rule.WinRule;
import com.lzhlyle.klotski.vo.Duration;

import java.util.List;

public class Game {
    private Opening opening;
    private MoveRule moveRule;
    private WinRule winRule;
    private Duration duration;
    private GameStatus status;

    private Board board;
    private List<Block> cubeBlockList;

    private Pedometer pedometer;

    public Game() {
        this.status = GameStatus.READY;
    }

    public boolean start() {
        return false;
    }
}
