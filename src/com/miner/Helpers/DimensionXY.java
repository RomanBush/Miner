package com.miner.Helpers;

import com.miner.Constants.BoardPropertiesEnum;

import java.util.ArrayList;

public class DimensionXY {
    private final double x;
    private final double y;

    public DimensionXY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    DimensionXY[] getNeighbours() {
        ArrayList<DimensionXY> ret= new ArrayList<>();
        for (int i=-1;i<=1;i++)
            for (int j=-1;j<=1;j++) {
                if (i+x==x && j+y==y) continue;
                if (x+i< BoardPropertiesEnum.width.get() && y+j< BoardPropertiesEnum.height.get() && x+i>=0 && y+j>=0)
                    ret.add(new DimensionXY(i+x,j+y));
            }
        return ret.toArray(DimensionXY[]::new);
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
}
