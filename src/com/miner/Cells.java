package com.miner;

import javafx.scene.image.Image;
import org.jetbrains.annotations.NotNull;

class Cells {
    private final Cell[][] cells;

    private final DimensionXY dimension;
    private final DimensionXY cellSize;

    Cells(int dX, int dY) {
        cellSize=new MineImages().getImageDimension();
        dimension=new DimensionXY(dX,dY);
        cells=new Cell[(int)dimension.getX()][(int)dimension.getY()];
    }
    Cell getCell(@NotNull DimensionXY xy) {
        return cells[(int)xy.getX()][(int)xy.getY()];
    }

    DimensionXY getDimension() { return dimension; }
    DimensionXY getCellSize() { return cellSize;}

    Image getImage(int x,int y) {
        return cells[x][y].getImage();
    }

    private void clearCells() {
        for(int x=0;x<dimension.getX();x++)
            for(int y=0;y<dimension.getY();y++){
                cells[x][y]= new Cell();
            }
    }

    DimensionXY getCellDimension(double MouseX, double MouseY) {
        int x= (int) ((int)MouseX/cellSize.getX());
        int y= (int) ((int)MouseY/cellSize.getY());
        return new DimensionXY(x, y);
    }

    void placeMines(int numMines){
        clearCells();
        int i=0;
        while(i<numMines){
            int x=random(dimension.getX());
            int y=random(dimension.getY());
            if(cells[x][y].getMine()==CellStatus.MINE_CELL.get())continue;
            cells[x][y].setMine(CellStatus.MINE_CELL.get());
            i++;
        }
        calculateBoard();
        fillCellsImages();
    }
    private int random(double max) {return (int)(Math.random()*max);}

    private void calculateBoard() {
        int maxX=(int)dimension.getX();
        int maxY=(int)dimension.getY();
        for(int x=0;x<maxX;x++)
            for(int y=0;y<maxY;y++) {
                if (cells[x][y].getMine()==CellStatus.MINE_CELL.get()) continue;
                int bombCounter=0;

                DimensionXY[] neighbours=new DimensionXY(x,y).getNeighbours();
                for (DimensionXY current: neighbours) {
                    if (cells[(int) current.getX()][(int) current.getY()].getMine()==CellStatus.MINE_CELL.get()) bombCounter++;
                }
                if (bombCounter==0) cells[x][y].setMine(CellStatus.EMPTY_CELL.get()); // если друг EMPTY_CELL будет не нулём, эта строка пригодится.
                else cells[x][y].setMine(bombCounter);
            }
    }

    private void fillCellsImages() {
        for(int x=0;x<dimension.getX();x++)
            for(int y=0;y<dimension.getY();y++) {
                cells[x][y].setCellImage();
            }
    }
    void openAllCells() {
        for(int x=0;x<dimension.getX();x++)
            for(int y=0;y<dimension.getY();y++) {
                cells[x][y].opened=true;
            }
        fillCellsImages();
    }
}
