package com.blackshoe.esthete.common.constant;

public enum Rule {
    INTRODUCE_LIMIT(20),
    BIOGRAPHY_LIMIT(50);

    private final int limit;

    Rule(int limit) {
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }
}