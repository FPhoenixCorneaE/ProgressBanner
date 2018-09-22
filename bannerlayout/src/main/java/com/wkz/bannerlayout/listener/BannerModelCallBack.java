package com.wkz.bannerlayout.listener;

import android.support.annotation.NonNull;

public interface BannerModelCallBack<T> {

    T getBannerUrl();

    @NonNull
    String getBannerTitle();

}
