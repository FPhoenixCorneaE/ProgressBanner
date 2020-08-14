package com.fphoenixcorneae.bannerlayout.listener

import android.view.View

/**
 * Banner Click event, object for the return of data
 */
interface OnBannerClickListener {
    fun onBannerClick(view: View, position: Int, model: BannerModelCallBack<*>?)
}