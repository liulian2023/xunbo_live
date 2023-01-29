package com.zz.live.bean;

public class HouseKeeperEnity {
    String amount=" - - - ";
    String remark;
    int drawableId;
    public String getRemark() {
        return remark;
    }

    public HouseKeeperEnity(String remark, int drawableId) {
        this.remark = remark;
        this.drawableId = drawableId;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
