package com.wkz.progressbanner.sample.bean;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.wkz.bannerlayout.listener.BannerModelCallBack;

public class SimpleBannerModel implements BannerModelCallBack {
    @Nullable
    private Object image;
    @Nullable
    private String title;

    @Nullable
    public final Object getImage() {
        return this.image;
    }

    public final void setImage(@Nullable Object var1) {
        this.image = var1;
    }

    @Nullable
    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(@Nullable String var1) {
        this.title = var1;
    }

    @NonNull
    public String getBannerUrl() {
        return String.valueOf(this.image);
    }

    @NonNull
    public String getBannerTitle() {
        return String.valueOf(this.title);
    }

    public SimpleBannerModel(@NonNull Object image) {
        super();
        this.image = image;
    }

    public SimpleBannerModel(@NonNull Object image, @NonNull String title) {
        super();
        this.image = image;
        this.title = title;
    }

    public SimpleBannerModel() {
    }
}
