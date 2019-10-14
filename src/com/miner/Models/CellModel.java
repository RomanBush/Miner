package com.miner.Models;

import com.miner.Constants.CellStatusEnum;

public class CellModel {
    private CellStatusEnum status= CellStatusEnum.EMPTY_CELL;
    private int number = 0;
    public boolean opened = false;
    public boolean markAsMine = false;

    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }

    public CellStatusEnum getStatus() { return status; }
    public void setStatus(CellStatusEnum status) { this.status = status; }
}
