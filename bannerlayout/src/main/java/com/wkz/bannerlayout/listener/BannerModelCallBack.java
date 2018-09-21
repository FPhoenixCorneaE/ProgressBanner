package com.wkz.bannerlayout.listener;

import android.support.annotation.NonNull;

/**
 * by y on 2017/5/27.
 */
public interface BannerModelCallBack<T> {

    T getBannerUrl();

    @NonNull
    String getBannerTitle();

}
