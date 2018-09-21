package com.wkz.bannerlayout.imagemanager;

import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.wkz.bannerlayout.R;
import com.wkz.bannerlayout.listener.BannerModelCallBack;
import com.wkz.bannerlayout.listener.ImageDisplayManager;

public class PicassoImageManager implements ImageDisplayManager {
    @NonNull
    public ImageView display(@NonNull ViewGroup container, @NonNull BannerModelCallBack model) {
        ImageView imageView = new ImageView(container.getContext());
        Picasso.get()
                .load((String) model.getBannerUrl())
                .placeholder(R.drawable.bl_drawable_holder)
                .error(R.drawable.bl_drawable_holder)
                .into(imageView);
        return imageView;
    }
}
