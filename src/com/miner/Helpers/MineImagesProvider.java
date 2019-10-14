package com.miner.Helpers;

import com.miner.Constants.CellStatusEnum;
import javafx.scene.image.Image;

import java.util.EnumMap;

public class MineImagesProvider {
    private static EnumMap<CellStatusEnum, Image> images;
    private static Image[] numImages;

    public MineImagesProvider() {
        if (images==null || numImages==null) {
            makeImages();
        }
    }

    private static void makeImages() {
        numImages=new Image[9];
        for (int i = 1; i <= 8; i++) {
            var path = "images/" + i + ".png";
            numImages[i] = new Image(path);
        }
        images= new EnumMap<>(CellStatusEnum.class);
        images.put(CellStatusEnum.EMPTY_CELL, new Image("images/empty_cell.png"));
        images.put(CellStatusEnum.MINE_CELL, new Image("images/open_mine.png"));
        images.put(CellStatusEnum.COVERED_CELL, new Image("images/cover_for_cell.png"));
        images.put(CellStatusEnum.MARKED_MINE, new Image("images/marked_mine.png"));
        images.put(CellStatusEnum.WRONG_MINE, new Image("images/wrong_mine.png"));
    }

    public static DimensionXY getImageDimension() {
        if (numImages==null) makeImages();
        return new DimensionXY(numImages[1].getWidth(), numImages[1].getHeight());
    }

    public static Image getEMPTY_CELLImage() { return images.get(CellStatusEnum.EMPTY_CELL); }
    public static Image getMINE_CELLImage() { return images.get(CellStatusEnum.MINE_CELL); }
    public static Image getCOVERED_CELLImage() { return images.get(CellStatusEnum.COVERED_CELL); }
    public static Image getMARKED_MINEImage() { return images.get(CellStatusEnum.MARKED_MINE); }
    public static Image getWRONG_MINEImage() { return images.get(CellStatusEnum.WRONG_MINE); }

    public static Image getNumberedImage(int m) {
        if (m>0 && m<9) return numImages[m];
        else return null;
    }
}
