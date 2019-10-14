package com.miner.Views;

import com.miner.Controllers.MinesweeperController;
import com.miner.Helpers.DimensionXY;
import com.miner.Helpers.MineImagesProvider;
import com.miner.Models.BoardModel;
import com.miner.Models.CellModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MinesweeperView {
    private Scene scene;
    private javafx.scene.canvas.Canvas boardCanvas;
    private GraphicsContext graphicsContext;

    public MinesweeperView(Stage stage) throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getResource("..\\Main.fxml")));
        stage.setScene(scene);
        stage.setTitle("Miner");
        stage.setResizable(false);
        stage.show();
        boardCanvas = (javafx.scene.canvas.Canvas) scene.lookup("#gameBoard"); // ищем поле.
        graphicsContext = boardCanvas.getGraphicsContext2D();
    }

    public void setHandlers(MinesweeperController minesweeperController) {
        // цепляем обработчики событий
        scene.lookup("#newGameButton").setOnMouseClicked(e -> minesweeperController.newGame());
        boardCanvas.setOnMouseClicked(e -> minesweeperController.onClick(e.getX(),e.getY(),e.getButton()));
    }

    public void paintBoard(BoardModel cells) {
        double maxX = cells.getCellArrayDimension().getX();
        double maxY = cells.getCellArrayDimension().getY();
        DimensionXY cellSize = MineImagesProvider.getImageDimension();

        for(int i=0; i<maxX; i++)
            for (int j=0;j<maxY;j++) {
                Image img = null;
                CellModel currentCell = cells.getCell(i,j);
                if (currentCell.markAsMine) img = MineImagesProvider.getMARKED_MINEImage();
                else if (!currentCell.opened) img = MineImagesProvider.getCOVERED_CELLImage();
                else {
                    if (currentCell.getNumber()>0) img = MineImagesProvider.getNumberedImage(currentCell.getNumber());
                    else {
                        switch (currentCell.getStatus()) {
                            case EMPTY_CELL:
                                img = MineImagesProvider.getEMPTY_CELLImage();
                                break;
                            case MINE_CELL:
                                img = MineImagesProvider.getMINE_CELLImage();
                                break;
                            case WRONG_MINE:
                                img = MineImagesProvider.getWRONG_MINEImage();
                                break;
                        }
                    }
                }
                graphicsContext.drawImage(img,i*cellSize.getX(),j*cellSize.getY());
            }
    }
}
