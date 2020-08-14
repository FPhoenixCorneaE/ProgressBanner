package com.fphoenixcorneae.bannerlayout.utils

import android.os.Handler
import android.os.Message
import com.fphoenixcorneae.bannerlayout.listener.ViewPagerCurrent

class BannerHandlerUtils(private val mCurrent: ViewPagerCurrent, private var page: Int) : Handler() {
    var status = 0
    private var delayTime: Long = 0

    fun setDelayTime(time: Long) {
        delayTime = time
    }

    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        if (page != -1) {
            if (this.hasMessages(MSG_UPDATE)) {
                this.removeMessages(MSG_UPDATE)
            }
            val what = msg.what
            when (what) {
                MSG_UPDATE -> {
                    ++page
                    mCurrent.setCurrentItem(page)
                    sendEmptyMessageDelayed(MSG_UPDATE, delayTime)
                }
                MSG_KEEP -> {
                }
                MSG_PAGE -> page = msg.arg1
                else -> {
                }
            }
            status = what
        }
    }

    companion object {
        const val MSG_UPDATE = 1
        const val MSG_KEEP = 2
        const val MSG_PAGE = 3
    }
}