package com.wkz.bannerlayout.imagemanager;

import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.wkz.bannerlayout.listener.BannerModelCallBack;
import com.wkz.bannerlayout.listener.ImageDisplayManager;
import com.wkz.bannerlayout.widget.BannerDefaults;

public class GlideImageManager implements ImageDisplayManager {

    private RequestOptions requestOptions;

    @NonNull
    public ImageView display(@NonNull ViewGroup container, @NonNull BannerModelCallBack model) {
        ImageView imageView = new ImageView(container.getContext());
        Glide.with(imageView.getContext())
                .applyDefaultRequestOptions(
                        this.requestOptions
                                .placeholder(BannerDefaults.GLIDE_PLACEHOLDER_DRAWABLE)
                                .error(BannerDefaults.GLIDE_ERROR_DRAWABLE)
                                .fallback(BannerDefaults.GLIDE_ERROR_DRAWABLE)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                )
                .load(model.getBannerUrl())
                .into(imageView);
        return imageView;
    }

    public GlideImageManager() {
        this.requestOptions = (new RequestOptions()).centerCrop();
    }
}
