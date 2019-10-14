package com.miner.Models;

import com.miner.Constants.BoardPropertiesEnum;

public class MinesweeperModel {
    private BoardModel board;

    public MinesweeperModel() {
        board = new BoardModel(BoardPropertiesEnum.width.get(), BoardPropertiesEnum.height.get());
    }

    public BoardModel getBoard() {
        return board;
    }

    public void setBoard(BoardModel board) {
        this.board = board;
    }
}
