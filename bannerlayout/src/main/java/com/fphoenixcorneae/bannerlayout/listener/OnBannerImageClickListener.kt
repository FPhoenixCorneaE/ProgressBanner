package com.fphoenixcorneae.bannerlayout.listener

import android.view.View

interface OnBannerImageClickListener {
    fun onBannerClick(view: View, position: Int, model: BannerModelCallBack<*>?)
}