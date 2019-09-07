package com.lzhlyle.klotski.pedometer;

import com.lzhlyle.klotski.rule.StepRule;

public class Pedometer {
    private StepRule stepRule;

    public Pedometer(StepRule stepRule) {
        this.stepRule = stepRule;
    }

    public boolean record() {
        return false;
    }
}
