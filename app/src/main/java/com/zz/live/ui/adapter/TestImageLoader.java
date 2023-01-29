package com.zz.live.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.previewlibrary.loader.IZoomMediaLoader;
import com.previewlibrary.loader.MySimpleTarget;
import com.zz.live.utils.Utils;

/**
 * Created by yangc on 2017/9/4.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

public class TestImageLoader implements IZoomMediaLoader {
    @Override
    public void displayImage(@NonNull Fragment fragment, @NonNull String s, ImageView imageView, @NonNull MySimpleTarget mySimpleTarget) {
        Glide.with(fragment)
                .load(Utils.checkImageUrl( Utils.checkImageUrl(s)))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        mySimpleTarget.onLoadFailed(null);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        mySimpleTarget.onResourceReady();
                        return false;
                    }
                })
                .into(imageView);
    }

    @Override
    public void displayGifImage(@NonNull Fragment fragment, @NonNull String s, ImageView imageView, @NonNull MySimpleTarget mySimpleTarget) {

    }

    @Override
    public void onStop(@NonNull Fragment fragment) {
        Glide.with(fragment).onStop();
    }

    @Override
    public void clearMemory(@NonNull Context c) {
        Glide.get(c).clearMemory();
    }
}
