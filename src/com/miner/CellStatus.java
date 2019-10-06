package com.miner;

public enum CellStatus {
    EMPTY_CELL(0),
    MIN_LEGAL_BOMB_COUNT(1),
    MAX_LEGAL_BOMB_COUNT(8),
    MINE_CELL(9),
    COVERED_CELL(10),
    MARKED_MINE(11),
    WRONG_MINE(12);

    private final int val;
    CellStatus(int val) {
        this.val=val;
    }

    public int get() {
        return val;
    }
}
