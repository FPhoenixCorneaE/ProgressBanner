package com.fphoenixcorneae.bannerlayout.listener

import android.view.ViewGroup
import android.widget.ImageView

interface ImageDisplayManager {
    fun display(container: ViewGroup, model: BannerModelCallBack<*>?): ImageView
}