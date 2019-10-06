package com.miner;

public enum boardProperties {
    width(16), height(16),
    numberOfMines(30);
    private final int val;
    boardProperties(int v) {
        this.val=v;
    }
    public int get() {
        return val;
    }
}
