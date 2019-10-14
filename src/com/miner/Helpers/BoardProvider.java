package com.miner.Helpers;

import com.miner.Constants.CellStatusEnum;
import com.miner.Models.BoardModel;
import com.miner.Models.CellModel;

public class BoardProvider {

    private void clearCells(BoardModel board) {
        for(int x = 0; x< board.getCellArrayDimension().getX(); x++)
            for(int y = 0; y< board.getCellArrayDimension().getY(); y++)
                board.setCell(x, y, new CellModel());
    }

    public void placeMines(BoardModel board, int numMines){
        clearCells(board);
        int i=0;
        while(i<numMines){
            int x=random(board.getCellArrayDimension().getX());
            int y=random(board.getCellArrayDimension().getY());
            if(board.getCell(x,y).getStatus()== CellStatusEnum.MINE_CELL)continue;
            board.getCell(x,y).setStatus(CellStatusEnum.MINE_CELL);
            i++;
        }
        calculateBoard(board);
    }
    private int random(double max) {return (int)(Math.random()*max);}

    private void calculateBoard(BoardModel board) {
        int maxX=(int) board.getCellArrayDimension().getX();
        int maxY=(int) board.getCellArrayDimension().getY();
        for(int x=0;x<maxX;x++)
            for(int y=0;y<maxY;y++) {
                if (board.getCell(x,y).getStatus()== CellStatusEnum.MINE_CELL) continue;
                int bombCounter=0;

                DimensionXY[] neighbours=new DimensionXY(x,y).getNeighbours();
                for (DimensionXY current: neighbours) {
                    if (board.getCell((int) current.getX(),(int) current.getY()).getStatus()== CellStatusEnum.MINE_CELL) bombCounter++;
                }
                if (bombCounter==0) board.getCell(x,y).setStatus(CellStatusEnum.EMPTY_CELL);
                else {
                    board.getCell(x,y).setNumber(bombCounter);
                    board.getCell(x,y).setStatus(CellStatusEnum.COVERED_CELL);
                    board.getCell(x,y).opened=false;
                }
            }
    }

    public void openAllCells(BoardModel board) {
        for(int x = 0; x< board.getCellArrayDimension().getX(); x++)
            for(int y = 0; y< board.getCellArrayDimension().getY(); y++) {
                board.getCell(x,y).opened=true;
            }
    }

    public void changeMarkAsMine(BoardModel boardModel, DimensionXY xy) {
        CellModel currentCell = boardModel.getCell(xy);
        if (!currentCell.opened) currentCell.markAsMine = !currentCell.markAsMine;
    }

    public boolean isGameOverAfterOpenCell(BoardModel boardModel, DimensionXY xy) {
        if (boardModel.getCell(xy).opened) return false;
        return openCellRecursive(boardModel, xy);
    }

    private boolean openCellRecursive(BoardModel boardModel, DimensionXY xy) {
        CellModel cell;
        try {
            cell = boardModel.getCell(xy);
        } catch (Exception e) { // если мы за пределами массива - просто игнорируем эту клетку.
            return false;
        }
        if (cell.opened || cell.markAsMine) return false;
        cell.opened = true;
        CellStatusEnum mineStatus = cell.getStatus();

        if (mineStatus == CellStatusEnum.MINE_CELL) {
            cell.setStatus(CellStatusEnum.WRONG_MINE);
            return true; // game over
        }
        if (cell.getNumber() > 0) { return false; }
        if (mineStatus == CellStatusEnum.EMPTY_CELL) { // если пустая клетка - пробежимся по соседям и откроем их
            DimensionXY[] neighbours = xy.getNeighbours();
            for (DimensionXY current: neighbours) {
                if (openCellRecursive(boardModel, current)) return true; // теоретически такого быть не должно. Оставляем на случай изменения логики.
            }
            return false;
        }
        return true; // если мы здесь, значит что-то пошло не так и мы забыли обработать какой-то вариант.
    }
}
