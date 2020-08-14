package com.fphoenixcorneae.bannerlayout.annotation

import android.widget.RelativeLayout
import androidx.annotation.IntDef

@IntDef(
        TipsLayoutSiteMode.LEFT,
        TipsLayoutSiteMode.TOP,
        TipsLayoutSiteMode.RIGHT,
        TipsLayoutSiteMode.BOTTOM,
        TipsLayoutSiteMode.CENTER
)
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
annotation class TipsLayoutSiteMode {
    companion object {
        const val LEFT = RelativeLayout.ALIGN_PARENT_LEFT
        const val TOP = RelativeLayout.ALIGN_PARENT_TOP
        const val RIGHT = RelativeLayout.ALIGN_PARENT_RIGHT
        const val BOTTOM = RelativeLayout.ALIGN_PARENT_BOTTOM
        const val CENTER = RelativeLayout.CENTER_IN_PARENT
    }
}