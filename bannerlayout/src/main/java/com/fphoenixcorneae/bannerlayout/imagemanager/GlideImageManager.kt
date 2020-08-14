package com.fphoenixcorneae.bannerlayout.imagemanager

import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.fphoenixcorneae.bannerlayout.listener.BannerModelCallBack
import com.fphoenixcorneae.bannerlayout.listener.ImageDisplayManager
import com.fphoenixcorneae.bannerlayout.widget.BannerDefaults

class GlideImageManager(cornerRadius: Int) : ImageDisplayManager {
    private val requestOptions: RequestOptions = RequestOptions().transform(
            CenterCrop(),
            RoundedCorners(cornerRadius)
    )
            .placeholder(BannerDefaults.GLIDE_PLACEHOLDER_DRAWABLE)
            .error(BannerDefaults.GLIDE_ERROR_DRAWABLE)
            .fallback(BannerDefaults.GLIDE_ERROR_DRAWABLE)
            .diskCacheStrategy(DiskCacheStrategy.ALL)

    override fun display(container: ViewGroup, model: BannerModelCallBack<*>?): ImageView {
        val imageView = ImageView(container.context)
        Glide.with(imageView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(model?.bannerUrl)
                .into(imageView)
        return imageView
    }
}