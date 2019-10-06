package com.miner;

import javafx.scene.input.MouseButton;
import org.jetbrains.annotations.NotNull;

class Actions {
    private final Board board;
    Actions(Board b) {
        this.board=b;
    }

    private void setMine(DimensionXY xy) {
        board.changeMarkAsMine(xy);
    }
    void onClick(DimensionXY xy, MouseButton button) {
        if (button== MouseButton.SECONDARY) {
            setMine(xy);
        } else {
            if (board.clickCell(xy)) board.gameOver();
        }
        board.paintBoard();
    }

    void newGameClick(@NotNull Board board) {
        board.newGame();
    }
}
