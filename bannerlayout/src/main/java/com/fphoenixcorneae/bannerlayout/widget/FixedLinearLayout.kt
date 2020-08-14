package com.fphoenixcorneae.bannerlayout.widget

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout

/**
 * @desc fixed:在 Android SDK 23 以下设置 Margin 的时候可能会失效问题。
 * @date 2020-08-14 17:01
 */
class FixedLinearLayout : LinearLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun generateLayoutParams(lp: ViewGroup.LayoutParams?): LayoutParams {
        if (lp is LayoutParams && Build.VERSION.SDK_INT >= 19) {
            return LayoutParams(lp)
        } else if (lp is MarginLayoutParams) {
            return LayoutParams(lp)
        }
        return super.generateLayoutParams(lp)
    }

}