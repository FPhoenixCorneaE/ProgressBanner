package com.fphoenixcorneae.bannerlayout.imagemanager

import android.view.ViewGroup
import android.widget.ImageView
import com.fphoenixcorneae.bannerlayout.listener.BannerModelCallBack
import com.fphoenixcorneae.bannerlayout.listener.ImageDisplayManager
import com.nostra13.universalimageloader.core.ImageLoader

class ImageLoaderManager : ImageDisplayManager {
    override fun display(container: ViewGroup, model: BannerModelCallBack<*>?): ImageView {
        val imageView = ImageView(container.context)
        val imageLoader = ImageLoader.getInstance()
        imageLoader.displayImage(model?.bannerUrl as? String, imageView)
        return imageView
    }
}