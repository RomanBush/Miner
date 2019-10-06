package com.miner;

import java.util.ArrayList;

public class DimensionXY {
    private final double x;
    private final double y;

    DimensionXY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public DimensionXY[] getNeighbours() {
        ArrayList<DimensionXY> ret= new ArrayList<>();
        for (int i=-1;i<=1;i++)
            for (int j=-1;j<=1;j++) {
                if (i+x==x && j+y==y) continue;
                if (x+i< boardProperties.width.get() && y+j< boardProperties.height.get() && x+i>=0 && y+j>=0)
                    ret.add(new DimensionXY(i+x,j+y));
            }
        return ret.toArray(DimensionXY[]::new);
    }

    double getX() {
        return x;
    }
    double getY() {
        return y;
    }
}
