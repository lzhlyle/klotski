package com.lzhlyle.klotski.quide;

import com.lzhlyle.klotski.vo.Snapshot;

import java.util.ArrayList;
import java.util.List;

public class Quide {
    private List<Snapshot> snapshotList;

    public Quide(Snapshot firstSnapshot) {
        this.snapshotList = new ArrayList<>();
        this.snapshotList.add(firstSnapshot);
    }

    public void take() {

    }
}
