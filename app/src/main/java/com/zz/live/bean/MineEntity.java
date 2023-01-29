package com.zz.live.bean;

public class MineEntity {
    int imageId;
    String typeName;
    int status=5;//不需要显示认证状态的item默认为5

    public MineEntity(int imageId, String typeName, int status) {
        this.imageId = imageId;
        this.typeName = typeName;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

}
