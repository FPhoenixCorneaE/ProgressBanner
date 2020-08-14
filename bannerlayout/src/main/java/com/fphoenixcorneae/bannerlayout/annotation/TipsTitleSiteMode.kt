package com.fphoenixcorneae.bannerlayout.annotation

import android.widget.RelativeLayout
import androidx.annotation.IntDef

@IntDef(
        TipsTitleSiteMode.LEFT,
        TipsTitleSiteMode.TOP,
        TipsTitleSiteMode.RIGHT,
        TipsTitleSiteMode.BOTTOM,
        TipsTitleSiteMode.CENTER,
        TipsTitleSiteMode.CENTER_HORIZONTAL,
        TipsTitleSiteMode.CENTER_VERTICAL
)
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
annotation class TipsTitleSiteMode {
    companion object {
        const val LEFT = RelativeLayout.ALIGN_PARENT_LEFT
        const val TOP = RelativeLayout.ALIGN_PARENT_TOP
        const val RIGHT = RelativeLayout.ALIGN_PARENT_RIGHT
        const val BOTTOM = RelativeLayout.ALIGN_PARENT_BOTTOM
        const val CENTER = RelativeLayout.CENTER_IN_PARENT
        const val CENTER_HORIZONTAL = RelativeLayout.CENTER_HORIZONTAL
        const val CENTER_VERTICAL = RelativeLayout.CENTER_VERTICAL
    }
}