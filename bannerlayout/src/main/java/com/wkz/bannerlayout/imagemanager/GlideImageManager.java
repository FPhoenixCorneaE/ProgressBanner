package com.wkz.bannerlayout.imagemanager;

import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.wkz.bannerlayout.R;
import com.wkz.bannerlayout.listener.BannerModelCallBack;
import com.wkz.bannerlayout.listener.ImageDisplayManager;

public class GlideImageManager implements ImageDisplayManager {

    private RequestOptions requestOptions;

    @NonNull
    public ImageView display(@NonNull ViewGroup container, @NonNull BannerModelCallBack model) {
        ImageView imageView = new ImageView(container.getContext());
        Glide.with(imageView.getContext())
                .applyDefaultRequestOptions(
                        this.requestOptions
                                .placeholder(R.drawable.bl_drawable_holder)
                                .error(R.drawable.bl_drawable_holder)
                                .fallback(R.drawable.bl_drawable_holder)
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
