package com.wkz.bannerlayout.listener;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * by y on 2016/11/11
 * <p>
 * <p>
 * Banner Click event, object for the return of data
 */
public interface OnBannerClickListener {

    void onBannerClick(@NonNull View view, int position, BannerModelCallBack model);

}
