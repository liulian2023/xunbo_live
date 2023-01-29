package com.zz.live.bean.evenBus;

public class leaveAppEvenModel {
    boolean isBack2App=false;

    public leaveAppEvenModel(boolean isBack2App) {
        this.isBack2App = isBack2App;
    }

    public boolean isBack2App() {
        return isBack2App;
    }

    public void setBack2App(boolean back2App) {
        isBack2App = back2App;
    }
}
