package com.lzhlyle.klotski.vo;

import com.lzhlyle.klotski.block.CubeBlock;
import com.lzhlyle.klotski.block.HorizontalBlock;
import com.lzhlyle.klotski.block.SquareBlock;
import com.lzhlyle.klotski.block.VerticalBlock;
import com.lzhlyle.klotski.board.BlockPlace;
import com.lzhlyle.klotski.board.Board;
import com.lzhlyle.klotski.board.Cell;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SnapshotTest {

    @Test
    public void compressResultShouldEquals() {
        Snapshot snapshot1 = getHengDaoLiMaSnapshot();
        Snapshot snapshot2 = getHengDaoLiMaSnapshot();

        Assert.assertEquals(snapshot1.getCompressResult(), snapshot2.getCompressResult());
        System.out.println(snapshot1.getCompressResult());
    }

    private Snapshot getHengDaoLiMaSnapshot() {
        List<Cell> cells = new ArrayList<>();
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 5; y++) {
                cells.add(new Cell(new Position(x, y)));
            }
        }
        Board board = new Board(cells);

        List<BlockPlace> blockPlaces = new ArrayList<>();
        blockPlaces.add(new BlockPlace(new Cell(new Position(1, 3)), new SquareBlock()));
        blockPlaces.add(new BlockPlace(new Cell(new Position(1, 1)), new HorizontalBlock()));
        blockPlaces.add(new BlockPlace(new Cell(new Position(0, 1)), new VerticalBlock()));
        blockPlaces.add(new BlockPlace(new Cell(new Position(3, 1)), new VerticalBlock()));
        blockPlaces.add(new BlockPlace(new Cell(new Position(0, 3)), new VerticalBlock()));
        blockPlaces.add(new BlockPlace(new Cell(new Position(3, 3)), new VerticalBlock()));
        blockPlaces.add(new BlockPlace(new Cell(new Position(0, 0)), new CubeBlock()));
        blockPlaces.add(new BlockPlace(new Cell(new Position(1, 0)), new CubeBlock()));
        blockPlaces.add(new BlockPlace(new Cell(new Position(2, 0)), new CubeBlock()));
        blockPlaces.add(new BlockPlace(new Cell(new Position(3, 0)), new CubeBlock()));

        return new Snapshot(board, blockPlaces);
    }

    @Test
    public void equals1() {
        Snapshot snapshot1 = getHengDaoLiMaSnapshot();
        Snapshot snapshot2 = getHengDaoLiMaSnapshot();

        Assert.assertEquals(snapshot1, snapshot2);
    }

    @Test
    public void hashCode1() {
        Snapshot snapshot1 = getHengDaoLiMaSnapshot();
        Snapshot snapshot2 = getHengDaoLiMaSnapshot();

        Assert.assertEquals(snapshot1.hashCode(), snapshot2.hashCode());
    }

    @Test
    public void toString1() {
        Snapshot snapshot1 = getHengDaoLiMaSnapshot();
        Snapshot snapshot2 = getHengDaoLiMaSnapshot();

        Assert.assertEquals(snapshot1.toString(), snapshot2.toString());
        System.out.println(snapshot1.toString());
    }
}