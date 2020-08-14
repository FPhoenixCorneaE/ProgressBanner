package com.fphoenixcorneae.bannerlayout.widget

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatTextView
import com.fphoenixcorneae.bannerlayout.annotation.TipsPageNumSiteMode
import com.fphoenixcorneae.bannerlayout.utils.BannerSelectorUtils
import com.fphoenixcorneae.bannerlayout.utils.DisplayUtils

class BannerPageNumView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : AppCompatTextView(context, attrs, defStyleAttr) {
    fun initPageView(pageNumViewInterface: PageNumViewInterface): FrameLayout.LayoutParams {
        this.gravity = Gravity.CENTER
        this.setTextColor(pageNumViewInterface.pageNumViewTextColor())
        this.textSize = pageNumViewInterface.pageNumViewTextSize()
        this.background = BannerSelectorUtils.getShape(
                this.context,
                pageNumViewInterface.pageNumViewRadius(),
                pageNumViewInterface.pageNumViewBackgroundColor()
        )
        setPadding(
                DisplayUtils.dp2px(context, pageNumViewInterface.pageNumViewPaddingLeft()),
                DisplayUtils.dp2px(context, pageNumViewInterface.pageNumViewPaddingTop()),
                DisplayUtils.dp2px(context, pageNumViewInterface.pageNumViewPaddingRight()),
                DisplayUtils.dp2px(context, pageNumViewInterface.pageNumViewPaddingBottom())
        )
        val pageParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        )

        @TipsPageNumSiteMode
        val pageNumViewSiteMode = pageNumViewInterface.pageNumViewSite()
        when (pageNumViewSiteMode) {
            TipsPageNumSiteMode.TOP_LEFT -> pageParams.gravity = Gravity.START or Gravity.TOP
            TipsPageNumSiteMode.TOP_RIGHT -> pageParams.gravity = Gravity.END or Gravity.TOP
            TipsPageNumSiteMode.BOTTOM_LEFT -> pageParams.gravity = Gravity.START or Gravity.BOTTOM
            TipsPageNumSiteMode.BOTTOM_RIGHT -> pageParams.gravity = Gravity.END or Gravity.BOTTOM
            TipsPageNumSiteMode.CENTER_LEFT -> pageParams.gravity = Gravity.START or Gravity.CENTER
            TipsPageNumSiteMode.CENTER_RIGHT -> pageParams.gravity = Gravity.END or Gravity.CENTER
            TipsPageNumSiteMode.TOP_CENTER -> pageParams.gravity = Gravity.TOP or Gravity.CENTER
            TipsPageNumSiteMode.BOTTOM_CENTER -> pageParams.gravity = Gravity.BOTTOM or Gravity.CENTER
            else -> pageParams.gravity = Gravity.START or Gravity.TOP
        }
        pageParams.setMargins(
                DisplayUtils.dp2px(context, pageNumViewInterface.pageNumViewLeftMargin()),
                DisplayUtils.dp2px(context, pageNumViewInterface.pageNumViewTopMargin()),
                DisplayUtils.dp2px(context, pageNumViewInterface.pageNumViewRightMargin()),
                DisplayUtils.dp2px(context, pageNumViewInterface.pageNumViewBottomMargin())
        )
        return pageParams
    }

    interface PageNumViewInterface {
        fun pageNumViewTopMargin(): Float
        fun pageNumViewRightMargin(): Float
        fun pageNumViewBottomMargin(): Float
        fun pageNumViewLeftMargin(): Float
        fun pageNumViewTextColor(): Int
        fun pageNumViewTextSize(): Float
        fun pageNumViewPaddingTop(): Float
        fun pageNumViewPaddingLeft(): Float
        fun pageNumViewPaddingBottom(): Float
        fun pageNumViewPaddingRight(): Float
        fun pageNumViewRadius(): Float
        fun pageNumViewBackgroundColor(): Int
        fun pageNumViewSite(): Int
    }
}