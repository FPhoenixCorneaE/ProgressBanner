package com.fphoenixcorneae.bannerlayout.utils

import android.content.Context

object DisplayUtils {
    /**
     * Convert dp to px by the density of phone
     */
    @kotlin.jvm.JvmStatic
    fun dp2px(mContext: Context, dp: Float): Int {
        return (dpToPx(mContext, dp) + 0.5f).toInt()
    }

    /**
     * Convert dp to px
     */
    fun dpToPx(mContext: Context, dp: Float): Float {
        val scale = mContext.resources.displayMetrics.density
        return dp * scale
    }

    /**
     * Convert px to dp by the density of phone
     */
    fun px2dp(mContext: Context, px: Float): Int {
        return (pxToDp(mContext, px) + 0.5f).toInt()
    }

    /**
     * Convert px to dp
     */
    fun pxToDp(mContext: Context, px: Float): Float {
        val scale = mContext.resources.displayMetrics.density
        return px / scale
    }

    /**
     * Convert px to sp
     */
    fun px2sp(mContext: Context, px: Float): Int {
        return (pxToSp(mContext, px) + 0.5f).toInt()
    }

    /**
     * Convert px to sp
     */
    fun pxToSp(mContext: Context, px: Float): Float {
        val fontScale = mContext.resources.displayMetrics.scaledDensity
        return px / fontScale
    }

    /**
     * Convert sp to px
     */
    fun sp2px(mContext: Context, sp: Float): Int {
        return (spToPx(mContext, sp) + 0.5f).toInt()
    }

    /**
     * Convert sp to px
     */
    fun spToPx(mContext: Context, sp: Float): Float {
        val fontScale = mContext.resources.displayMetrics.scaledDensity
        return sp * fontScale
    }
}