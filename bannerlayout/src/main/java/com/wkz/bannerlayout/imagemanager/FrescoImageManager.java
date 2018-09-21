package com.wkz.bannerlayout.imagemanager;

import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wkz.bannerlayout.listener.BannerModelCallBack;
import com.wkz.bannerlayout.listener.ImageDisplayManager;

public class FrescoImageManager implements ImageDisplayManager {
    @NonNull
    public ImageView display(@NonNull ViewGroup container, @NonNull BannerModelCallBack model) {
        SimpleDraweeView draweeView = new SimpleDraweeView(container.getContext());
        draweeView.setImageURI((String) model.getBannerUrl());
        return draweeView;
    }
}
