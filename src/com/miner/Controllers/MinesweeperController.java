package com.miner.Controllers;

import com.miner.Constants.BoardPropertiesEnum;
import com.miner.Helpers.BoardProvider;
import com.miner.Helpers.DimensionXY;
import com.miner.Helpers.MineImagesProvider;
import com.miner.Models.BoardModel;
import com.miner.Models.MinesweeperModel;
import com.miner.Views.MinesweeperView;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseButton;

public class MinesweeperController {
    private final MinesweeperView minesweeperView;
    private MinesweeperModel minesweeperModel;
    private final BoardProvider boardProvider;

    public MinesweeperController(MinesweeperModel minesweeperModel, MinesweeperView minesweeperView) {
        this.minesweeperModel =minesweeperModel;
        this.minesweeperView =minesweeperView;
        // цепляем обработчики событий
        this.minesweeperView.setHandlers(this);
        // Делаем всех провайдеров
        boardProvider = new BoardProvider();
    }

    public void onClick(double MouseX, double MouseY, MouseButton button) {
        DimensionXY cellSize = MineImagesProvider.getImageDimension();
        DimensionXY xy=new DimensionXY((int)MouseX/cellSize.getX(),(int)MouseY/cellSize.getY());
        if (button== MouseButton.SECONDARY) {
            boardProvider.changeMarkAsMine(minesweeperModel.getBoard(), xy);
        } else {
            if (boardProvider.isGameOverAfterOpenCell(minesweeperModel.getBoard(), xy)) gameOver();
        }
        minesweeperView.paintBoard(minesweeperModel.getBoard());
    }

    public void newGame() {
        minesweeperModel.setBoard(new BoardModel(BoardPropertiesEnum.width.get(),BoardPropertiesEnum.height.get()));
        boardProvider.placeMines(minesweeperModel.getBoard(), BoardPropertiesEnum.numberOfMines.get());
        minesweeperView.paintBoard(minesweeperModel.getBoard());
    }

    private void gameOver() {
        boardProvider.openAllCells(minesweeperModel.getBoard());
        minesweeperView.paintBoard(minesweeperModel.getBoard());
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Игра закончена");
        alert.setHeaderText("Вы проиграли!");
//        alert.setContentText("Ещё разок?");
        alert.showAndWait();
    }
}
