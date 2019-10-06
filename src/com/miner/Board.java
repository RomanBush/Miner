package com.miner;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.io.IOException;

class Board {
    private Scene scene;
    private final Stage stage;
    private javafx.scene.canvas.Canvas board;

    private Cells cells;

    Board(Stage stg) throws IOException {
        stage=stg;
        startScene();
        board= (javafx.scene.canvas.Canvas) scene.lookup("#gameBoard"); // ищем поле.
        newGame();
        Actions myAction = new Actions(this);
        // цепляем обработчики событий
        scene.lookup("#newGameButton").setOnMouseClicked(e -> myAction.newGameClick(this));
        board.setOnMouseClicked(e -> myAction.onClick(cells.getCellDimension(e.getX(),e.getY()),e.getButton()));
    }

    void newGame() {
        cells=new Cells(boardProperties.width.get(), boardProperties.height.get());
        cells.placeMines(boardProperties.numberOfMines.get());
        paintBoard();
    }

    private void startScene() throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getResource("Main.fxml")));
        stage.setScene(scene);
        stage.setTitle("Miner");
        stage.setResizable(false);
        stage.show();
    }

    void paintBoard() {
        GraphicsContext gc=board.getGraphicsContext2D();
        double maxX=cells.getDimension().getX();
        double maxY=cells.getDimension().getY();
        double cellSizeX=cells.getCellSize().getX();
        double cellSizeY=cells.getCellSize().getY();

        for(int i=0; i<maxX;i++)
            for (int j=0;j<maxY;j++) {
                gc.drawImage(cells.getImage(i,j),i*cellSizeX,j*cellSizeY);
            }
    }

    void changeMarkAsMine(DimensionXY xy) {
        Cell currentCell=cells.getCell(xy);
        if (!currentCell.opened) currentCell.markAsMine=!currentCell.markAsMine;
        currentCell.setCellImage();
    }

    boolean clickCell(DimensionXY xy) {
        if (cells.getCell(xy).opened) return false;
        return openCell(xy);
    }

    private boolean openCell(DimensionXY xy) {
        Cell cell;
        try {
            cell=cells.getCell(xy);
        } catch (Exception e) { // если мы за пределами массива - просто игнорируем эту клетку.
            return false;
        }
        if (cell.opened || cell.markAsMine) return false;
        cell.opened=true;
        cell.setCellImage();
        int mineStatus=cell.getMine();
        if (mineStatus>=CellStatus.MIN_LEGAL_BOMB_COUNT.get() && mineStatus<=CellStatus.MAX_LEGAL_BOMB_COUNT.get()) return false;
        if (mineStatus==CellStatus.MINE_CELL.get()) {
            cell.setMine(CellStatus.WRONG_MINE.get());
            return true; // game over
        }
        if (mineStatus==CellStatus.EMPTY_CELL.get()) { // если пустая клетка - пробежимся по соседям и откроем их
            DimensionXY[] neighbours=xy.getNeighbours();
            for (DimensionXY current: neighbours) {
                if (openCell(current)) return true; // теоретически такого быть не должно. Оставляем на случай изменения логики.
            }
            return false;
        }
        return true; // если мы здесь, значит что-то пошло не так и мы забыли обработать какой-то вариант.
    }

    void gameOver() {
        cells.openAllCells();
    }
}
