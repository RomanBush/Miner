package com.miner;

import javafx.scene.image.Image;

class Cell {
    private Image image;
    private int mine;
    boolean opened=false;
    boolean markAsMine=false;

    Image getImage() { return image; }
    private void setImage(Image image) { this.image = image; }
    int getMine() { return mine; }
    void setMine(int mine) { this.mine = mine; }

    void setCellImage() {
        MineImages mineImages=new MineImages();
        if (markAsMine) setImage(mineImages.MarkedMineImage());
        else if (!opened) setImage(mineImages.CoveredMineImage());
        else {
            int mineStatus=getMine();
            if (mineStatus>=CellStatus.MIN_LEGAL_BOMB_COUNT.get() && mineStatus<=CellStatus.MAX_LEGAL_BOMB_COUNT.get()) {
                setImage(mineImages.getNumberImage(mineStatus));
                return;
            }
            if (mineStatus == CellStatus.EMPTY_CELL.get()) {
                setImage(mineImages.EmptyImage());
                return;
            }
            if (mineStatus == CellStatus.MINE_CELL.get()) {
                setImage(mineImages.MineImage());
                return;
            }
            if (mineStatus == CellStatus.WRONG_MINE.get())
                setImage(mineImages.WrongMineImage());
        }
    }
}
