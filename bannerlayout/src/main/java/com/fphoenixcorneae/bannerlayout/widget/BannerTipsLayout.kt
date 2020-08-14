package com.fphoenixcorneae.bannerlayout.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.core.view.MarginLayoutParamsCompat
import com.fphoenixcorneae.bannerlayout.annotation.TipsDotsSiteMode
import com.fphoenixcorneae.bannerlayout.annotation.TipsLayoutSiteMode
import com.fphoenixcorneae.bannerlayout.annotation.TipsProgressesSiteMode
import com.fphoenixcorneae.bannerlayout.annotation.TipsTitleSiteMode
import com.fphoenixcorneae.bannerlayout.utils.DisplayUtils

class BannerTipsLayout(private val mContext: Context) : RelativeLayout(mContext) {
    private val mTextView = TextView(mContext)
    private val mDotsContainer = FixedLinearLayout(mContext)
    private val mProgressesContainer = FixedLinearLayout(mContext)

    /**
     * Initialize the dots
     */
    fun setDots(dotsInterface: DotsInterface) {
        mDotsContainer.removeAllViews()
        for (i in 0 until dotsInterface.dotsCount()) {
            val view = View(mContext)
            view.background = dotsInterface.dotsSelector()
            view.isEnabled = i == 0
            val marginLayoutParams = MarginLayoutParams(
                    DisplayUtils.dp2px(mContext, dotsInterface.dotsWidth()),
                    DisplayUtils.dp2px(mContext, dotsInterface.dotsHeight()))
            MarginLayoutParamsCompat.setMarginStart(marginLayoutParams,
                    DisplayUtils.dp2px(mContext, dotsInterface.dotsLeftMargin()))
            marginLayoutParams.topMargin = DisplayUtils.dp2px(mContext, dotsInterface.dotsTopMargin())
            MarginLayoutParamsCompat.setMarginEnd(marginLayoutParams,
                    DisplayUtils.dp2px(mContext, dotsInterface.dotsRightMargin()))
            marginLayoutParams.bottomMargin = DisplayUtils.dp2px(mContext, dotsInterface.dotsBottomMargin())
            mDotsContainer.addView(view, marginLayoutParams)
        }
        mDotsContainer.measure(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        val params = LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        for (i in dotsInterface.dotsSite()!!.indices) {
            params.addRule(dotsInterface.dotsSite()!![i])
        }
        this.addView(mDotsContainer, params)
    }

    /**
     * Initialize the Progresses
     */
    fun setProgresses(progressInterface: ProgressInterface) {
        mProgressesContainer.removeAllViews()
        for (i in 0 until progressInterface.progressCount()) {
            val view = View(mContext)
            view.background = progressInterface.progressBuilder()!!.build()
            val marginLayoutParams = MarginLayoutParams(
                    progressInterface.progressBuilder()?.width?.toInt() ?: 0,
                    progressInterface.progressBuilder()?.height?.toInt() ?: 0)
            MarginLayoutParamsCompat.setMarginStart(marginLayoutParams,
                    DisplayUtils.dp2px(mContext, progressInterface.progressLeftMargin()))
            marginLayoutParams.topMargin = DisplayUtils.dp2px(mContext, progressInterface.progressTopMargin())
            MarginLayoutParamsCompat.setMarginEnd(marginLayoutParams,
                    DisplayUtils.dp2px(mContext, progressInterface.progressRightMargin()))
            marginLayoutParams.bottomMargin = DisplayUtils.dp2px(mContext, progressInterface.progressBottomMargin())
            mProgressesContainer.addView(view, marginLayoutParams)
        }
        mProgressesContainer.measure(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        val params = LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        for (i in progressInterface.progressSite()!!.indices) {
            params.addRule(progressInterface.progressSite()!![i])
        }
        this.addView(mProgressesContainer, params)
    }

    fun setBannerTips(tipsInterface: TipsInterface): FrameLayout.LayoutParams {
        val tipsParams = FrameLayout.LayoutParams(tipsInterface.tipsWidth(), tipsInterface.tipsHeight())
        @TipsLayoutSiteMode val tipsSiteMode = tipsInterface.tipsSite()
        when (tipsSiteMode) {
            TipsLayoutSiteMode.LEFT -> tipsParams.gravity = Gravity.START
            TipsLayoutSiteMode.TOP -> tipsParams.gravity = Gravity.TOP
            TipsLayoutSiteMode.RIGHT -> tipsParams.gravity = Gravity.END
            TipsLayoutSiteMode.BOTTOM -> tipsParams.gravity = Gravity.BOTTOM
            TipsLayoutSiteMode.CENTER -> tipsParams.gravity = Gravity.CENTER
        }
        if (tipsInterface.showBackgroundColor()) {
            setBackgroundColor(tipsInterface.tipsLayoutBackgroundColor())
        }
        return tipsParams
    }

    /**
     * Update the dot position
     */
    fun changeDotsPosition(position: Int, newPosition: Int) {
        val childAt = mDotsContainer.getChildAt(position)
        val newChildAt = mDotsContainer.getChildAt(newPosition)
        if (childAt != null) {
            childAt.isEnabled = false
        }
        if (newChildAt != null) {
            newChildAt.isEnabled = true
        }
    }

    /**
     * Update the Progress position
     */
    fun changeProgressesPosition(position: Int, newPosition: Int) {
        val childAt = mProgressesContainer.getChildAt(position)
        val newChildAt = mProgressesContainer.getChildAt(newPosition)
        if (childAt != null) {
            if (childAt.background is ProgressDrawable) {
                (childAt.background as ProgressDrawable).end()
            }
        }
        if (newChildAt != null) {
            if (newChildAt.background is ProgressDrawable) {
                (newChildAt.background as ProgressDrawable).start()
            }
        }
    }

    /**
     * stop the Progress
     */
    fun stopProgresses(position: Int) {
        val childAt = mProgressesContainer.getChildAt(position)
        if (childAt != null) {
            if (childAt.background is ProgressDrawable) {
                (childAt.background as ProgressDrawable).stop()
            }
        }
    }

    /**
     * Update title, the default on the left
     */
    fun setTitle(titleInterface: TitleInterface) {
        mTextView.gravity = Gravity.CENTER_VERTICAL
        mTextView.setTextColor(titleInterface.titleColor())
        mTextView.textSize = titleInterface.titleSize()
        mTextView.isSingleLine = true
        mTextView.ellipsize = TextUtils.TruncateAt.END
        mTextView.setBackgroundColor(titleInterface.titleBackgroundColor())
        mTextView.setPadding(
                DisplayUtils.dp2px(mContext, titleInterface.titleLeftMargin()),
                DisplayUtils.dp2px(mContext, titleInterface.titleTopMargin()),
                DisplayUtils.dp2px(mContext, titleInterface.titleRightMargin()),
                DisplayUtils.dp2px(mContext, titleInterface.titleBottomMargin())
        )
        val params = LayoutParams(
                if (titleInterface.titleWidth() == -1f) ViewGroup.LayoutParams.MATCH_PARENT else DisplayUtils.dp2px(mContext, titleInterface.titleWidth()),
                if (titleInterface.titleHeight() == -2f) ViewGroup.LayoutParams.WRAP_CONTENT else DisplayUtils.dp2px(mContext, titleInterface.titleHeight())
        )
        for (i in titleInterface.titleSite()!!.indices) {
            params.addRule(titleInterface.titleSite()!![i])
        }
        this.addView(mTextView, params)
    }

    fun setTitle(title: String?) {
        clearText()
        if (!TextUtils.isEmpty(title)) {
            mTextView.text = title
        }
    }

    private fun clearText() {
        mTextView.text = ""
    }

    interface TipsInterface {
        fun showBackgroundColor(): Boolean
        fun tipsSite(): Int
        fun tipsWidth(): Int
        fun tipsHeight(): Int
        fun tipsLayoutBackgroundColor(): Int
    }

    interface TitleInterface {
        fun titleColor(): Int
        fun titleSize(): Float
        fun titleLeftMargin(): Float
        fun titleTopMargin(): Float
        fun titleRightMargin(): Float
        fun titleBottomMargin(): Float
        fun titleWidth(): Float
        fun titleHeight(): Float

        @ColorInt
        fun titleBackgroundColor(): Int

        @TipsTitleSiteMode
        fun titleSite(): IntArray?
    }

    interface DotsInterface {
        fun dotsCount(): Int
        fun dotsSelector(): Drawable
        fun dotsHeight(): Float
        fun dotsWidth(): Float
        fun dotsLeftMargin(): Float
        fun dotsTopMargin(): Float
        fun dotsRightMargin(): Float
        fun dotsBottomMargin(): Float

        @TipsDotsSiteMode
        fun dotsSite(): IntArray?
    }

    interface ProgressInterface {
        fun progressCount(): Int
        fun progressLeftMargin(): Float
        fun progressTopMargin(): Float
        fun progressRightMargin(): Float
        fun progressBottomMargin(): Float

        @TipsProgressesSiteMode
        fun progressSite(): IntArray?
        fun progressBuilder(): ProgressDrawable.Builder?
    }
}