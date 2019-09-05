package com.lzhlyle.klotski.game;

import com.lzhlyle.klotski.block.Block;
import com.lzhlyle.klotski.block.IBlockSuitFactory;
import com.lzhlyle.klotski.board.Board;
import com.lzhlyle.klotski.board.IBoardFactory;
import com.lzhlyle.klotski.opening.Opening;
import com.lzhlyle.klotski.pedometer.Pedometer;
import com.lzhlyle.klotski.rule.MoveRule;
import com.lzhlyle.klotski.rule.StepRule;
import com.lzhlyle.klotski.rule.WinRule;
import com.lzhlyle.klotski.vo.Duration;
import com.lzhlyle.klotski.vo.Snapshot;

import java.util.List;

public class Game {
    private Opening opening;
    private MoveRule moveRule;
    private WinRule winRule;
    private Duration duration;
    private GameStatusEnum status;

    private Board board;
    private List<Block> cubeBlockList;

    private Pedometer pedometer;

    private Snapshot currentSnapshot;

    public Game(Opening opening, IBoardFactory boardFactory, IBlockSuitFactory blockSuitFactory)
            throws CloneNotSupportedException {
        this.opening = opening;
        this.moveRule = new MoveRule();
        this.winRule = new WinRule();
        this.duration = null;
        this.status = GameStatusEnum.READY;

        this.board = boardFactory.createBoard();
        this.cubeBlockList = blockSuitFactory.createBlockSuit();

        this.pedometer = new Pedometer(new StepRule());

        this.currentSnapshot = opening.getSnapshot();
    }

    public boolean start() {
        this.duration = new Duration();
        return false;
    }
}
