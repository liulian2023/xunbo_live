package com.zz.live.bean.evenBus;

public class HideCoverEven {
boolean hideCover;
int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public HideCoverEven(boolean hideCover, int position) {
        this.hideCover = hideCover;
        this.position = position;
    }

    public boolean isHideCover() {
        return hideCover;
    }

    public void setHideCover(boolean hideCover) {
        this.hideCover = hideCover;
    }


}
