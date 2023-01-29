package com.zz.live.bean;

import android.graphics.Rect;
import android.os.Parcel;

import androidx.annotation.Nullable;

import com.previewlibrary.enitity.IThumbViewInfo;

public class PictureBean implements IThumbViewInfo {
    private String url;  //图片地址
    private Rect mBounds; // 记录坐标
    private String user;//
    private String videoUrl;//视频链接 //不为空是视频

    public PictureBean(String url) {
        this.url = url;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public Rect getBounds() {
        return mBounds;
    }

    @Nullable
    @Override
    public String getVideoUrl() {
        return videoUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeParcelable(this.mBounds, flags);
        dest.writeString(this.user);
        dest.writeString(this.videoUrl);
    }
    protected PictureBean(Parcel in) {
        this.url = in.readString();
        this.mBounds = in.readParcelable(Rect.class.getClassLoader());
        this.user = in.readString();
        this.videoUrl = in.readString();
    }
    public static final Creator<PictureBean> CREATOR = new Creator<PictureBean>() {
        @Override
        public PictureBean createFromParcel(Parcel source) {
            return new PictureBean(source);
        }

        @Override
        public PictureBean[] newArray(int size) {
            return new PictureBean[size];
        }
    };
}
