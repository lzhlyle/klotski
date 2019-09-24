package com.lzhlyle.klotski.slover;

import com.lzhlyle.klotski.quide.Quide;
import com.lzhlyle.klotski.vo.Duration;
import com.lzhlyle.klotski.vo.Snapshot;

public class Slover {
    private Duration duration;
    private Quide quide;
    private int minSteps;

    private int currentMinSteps;

    public Slover(Snapshot firstSnapshot) {
        this.duration = null;
        this.quide = new Quide(firstSnapshot);
        this.minSteps = 0;
        this.currentMinSteps = 0;
    }

    public void explore() {
        // TODO lzh explore the solution
        this.duration = new Duration();
    }

    public Duration getDuration() {
        return duration;
    }

    public int getMinSteps() {
        return minSteps;
    }

    public Quide getQuide() {
        return quide;
    }
}
