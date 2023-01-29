package com.zz.live.bean.evenBus;

public class LiveEvenEntity {
    String playUrl;
    String coverUrl;
    int position;

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public LiveEvenEntity(String playUrl, String coverUrl, int position) {
        this.playUrl = playUrl;
        this.coverUrl = coverUrl;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }
}
