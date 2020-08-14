package com.fphoenixcorneae.bannerlayout.utils

import android.R
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable

object BannerSelectorUtils {
    fun getDrawableSelector(context: Context, enabledRadius: Float, enabledColor: Int, normalRadius: Float, normalColor: Int): StateListDrawable {
        val drawable = StateListDrawable()
        drawable.addState(intArrayOf(R.attr.state_enabled), getShape(context, enabledRadius, enabledColor))
        drawable.addState(intArrayOf(-R.attr.state_enabled), getShape(context, normalRadius, normalColor))
        return drawable
    }

    fun getShape(context: Context, radius: Float, color: Int): GradientDrawable {
        val gd = GradientDrawable()
        gd.shape = GradientDrawable.RECTANGLE
        gd.cornerRadius = DisplayUtils.dp2px(context, radius).toFloat()
        gd.setColor(color)
        return gd
    }
}