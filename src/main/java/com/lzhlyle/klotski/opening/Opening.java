package com.lzhlyle.klotski.opening;

import com.lzhlyle.klotski.vo.Snapshot;

public class Opening {
    private Snapshot snapshot;

    public Opening(Snapshot snapshot) {
        this.snapshot = snapshot;
    }

    public Snapshot getSnapshot() {
        return snapshot;
    }
}
