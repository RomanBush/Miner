package com.miner;

import javafx.scene.image.Image;

class MineImages {
    private static Image[] images;

    MineImages() {
        if (images==null) {
            images = new Image[13];
            for (int i = 1; i <= 8; i++) {
                var path = "images/" + i + ".png";
                images[i] = new Image(path);
            }
            images[CellStatus.EMPTY_CELL.get()] = new Image("images/empty_cell.png");
            images[CellStatus.MINE_CELL.get()] = new Image("images/open_mine.png");
            images[CellStatus.COVERED_CELL.get()] = new Image("images/cover_for_cell.png");
            images[CellStatus.MARKED_MINE.get()] = new Image("images/marked_mine.png");
            images[CellStatus.WRONG_MINE.get()] = new Image("images/wrong_mine.png");
        }
    }

    DimensionXY getImageDimension() {return new DimensionXY(images[1].getWidth(), images[1].getHeight());}
    Image getNumberImage(int m) {
        if (m>0 && m<9) return images[m];
        else return null;
    }
    Image CoveredMineImage() { return images[CellStatus.COVERED_CELL.get()]; }
    Image EmptyImage() { return images[CellStatus.EMPTY_CELL.get()]; }
    Image MineImage() { return images[CellStatus.MINE_CELL.get()]; }
    Image MarkedMineImage() { return images[CellStatus.MARKED_MINE.get()]; }
    Image WrongMineImage() { return images[CellStatus.WRONG_MINE.get()]; }
}
