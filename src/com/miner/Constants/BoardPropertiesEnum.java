package com.miner.Constants;

public enum BoardPropertiesEnum {
    width(16), height(16),
    numberOfMines(30);

    private final int val;
    BoardPropertiesEnum(int v) {
        this.val=v;
    }
    public int get() {
        return val;
    }
}
