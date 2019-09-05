package com.lzhlyle.klotski;

import com.lzhlyle.klotski.block.HorizontalBlock;
import com.lzhlyle.klotski.game.Game;
import com.lzhlyle.klotski.opening.Opening;
import com.lzhlyle.klotski.vo.Snapshot;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Snapshot snapshot = new Snapshot("4,5|C,C1,C2,C3,V,1H1,1V3,1V,3S1,3V3,3");
        Opening opening = new Opening(snapshot);
        Game game = new Game(opening);
        game.start();

        // pick up H only
        HorizontalBlock h = game.pickUpOnly(HorizontalBlock.class);
    }
}
