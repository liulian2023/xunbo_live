package com.zz.live;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class TestBeen implements MultiItemEntity {
    String title;
    int type;

    public TestBeen(String title, int type) {
        this.title = title;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
