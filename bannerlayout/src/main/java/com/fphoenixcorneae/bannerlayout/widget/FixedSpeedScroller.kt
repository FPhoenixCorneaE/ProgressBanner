package com.fphoenixcorneae.bannerlayout.widget

import android.content.Context
import android.widget.Scroller

class FixedSpeedScroller(context: Context) : Scroller(context) {
    var fixDuration = 0
        private set

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
        super.startScroll(startX, startY, dx, dy, fixDuration)
    }

    fun setDuration(time: Int) {
        fixDuration = time
    }
}