package com.fphoenixcorneae.bannerlayout.imagemanager

import android.view.ViewGroup
import android.widget.ImageView
import com.fphoenixcorneae.bannerlayout.listener.BannerModelCallBack
import com.fphoenixcorneae.bannerlayout.listener.ImageDisplayManager
import com.fphoenixcorneae.bannerlayout.widget.BannerDefaults
import com.squareup.picasso.Picasso

class PicassoImageManager : ImageDisplayManager {
    override fun display(container: ViewGroup, model: BannerModelCallBack<*>?): ImageView {
        val imageView = ImageView(container.context)
        Picasso.get()
                .load(model?.bannerUrl as? String)
                .placeholder(BannerDefaults.GLIDE_PLACEHOLDER_DRAWABLE)
                .error(BannerDefaults.GLIDE_ERROR_DRAWABLE)
                .into(imageView)
        return imageView
    }
}