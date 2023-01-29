package com.zz.live.bean;


public class ToyConnectEvent {
    private int connect;
    private String id;

    public ToyConnectEvent(int connect, String id) {
        this.connect = connect;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getConnect() {
        return connect;
    }

    public void setConnect(int connect) {
        this.connect = connect;
    }
}
