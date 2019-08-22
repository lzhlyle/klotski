package com.lzhlyle.klotski.pedometer;

import com.lzhlyle.klotski.regulation.Regulation;

public class Pedometer {
    private Regulation recordRegulation;

    public Pedometer(Regulation recordRegulation) {
        this.recordRegulation = recordRegulation;
    }

    public boolean record() {
        return false;
    }
}
