package com.fphoenixcorneae.bannerlayout.annotation

import android.widget.RelativeLayout
import androidx.annotation.IntDef

@IntDef(
        TipsPageNumSiteMode.TOP_LEFT,
        TipsPageNumSiteMode.TOP_RIGHT,
        TipsPageNumSiteMode.BOTTOM_LEFT,
        TipsPageNumSiteMode.BOTTOM_RIGHT,
        TipsPageNumSiteMode.CENTER_LEFT,
        TipsPageNumSiteMode.CENTER_RIGHT,
        TipsPageNumSiteMode.TOP_CENTER,
        TipsPageNumSiteMode.BOTTOM_CENTER
)
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
annotation class TipsPageNumSiteMode {
    companion object {
        const val TOP_LEFT = RelativeLayout.LEFT_OF
        const val TOP_RIGHT = RelativeLayout.RIGHT_OF
        const val BOTTOM_LEFT = RelativeLayout.ABOVE
        const val BOTTOM_RIGHT = RelativeLayout.BELOW
        const val CENTER_LEFT = RelativeLayout.ALIGN_BASELINE
        const val CENTER_RIGHT = RelativeLayout.ALIGN_LEFT
        const val TOP_CENTER = RelativeLayout.ALIGN_TOP
        const val BOTTOM_CENTER = RelativeLayout.ALIGN_RIGHT
    }
}