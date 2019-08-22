package com.lzhlyle.klotski.regulation;

import java.util.ArrayList;
import java.util.List;

public class Regulation {
    private List<Rule> ruleList;

    public Regulation() {
        this.ruleList = new ArrayList<Rule>();
    }

    public void addRule(Rule rule) {
        this.ruleList.add(rule);
    }
}
