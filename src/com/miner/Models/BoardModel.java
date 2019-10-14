package com.miner.Models;

import com.miner.Helpers.DimensionXY;
import org.jetbrains.annotations.NotNull;

public class BoardModel {
    private final CellModel[][] cells;
    private final DimensionXY cellArrayDimension;

    public DimensionXY getCellArrayDimension() { return cellArrayDimension; }

    public BoardModel(int dX, int dY) {
        cellArrayDimension = new DimensionXY(dX,dY);
        cells = new CellModel[(int) cellArrayDimension.getX()][(int) cellArrayDimension.getY()];
    }

    public void setCell(@NotNull DimensionXY xy, CellModel newCell) {
        setCell((int)xy.getX(), (int)xy.getY(),newCell);
    }

    public void setCell(int x, int y, CellModel newCell) {
        cells[x][y] = newCell;
    }

    public CellModel getCell(@NotNull DimensionXY xy) {
        return getCell((int)xy.getX(), (int)xy.getY());
    }

    public CellModel getCell(int x, int y) {
        return cells[x][y];
    }
}
