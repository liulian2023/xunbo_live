package com.zz.live.bean;

public class HomeEntity {
    int imageId;
    String typeName;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public HomeEntity(int imageId, String typeName) {
        this.imageId = imageId;
        this.typeName = typeName;
    }
}
