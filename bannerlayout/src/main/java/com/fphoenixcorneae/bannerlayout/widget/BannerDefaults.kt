package com.fphoenixcorneae.bannerlayout.widget

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import com.fphoenixcorneae.bannerlayout.annotation.*

object BannerDefaults {
    /**
     * By default,  isGuide
     */
    const val IS_GUIDE = false

    /**
     * Whether the vertical sliding ,The default is not
     */
    const val IS_VERTICAL = false

    /**
     * Auto rotation is not turned on by default
     */
    const val IS_START_ROTATION = false

    /**
     * Default rotation time
     */
    const val DELAY_TIME = 2000

    /**
     * The default viewpager can be manually swiped
     */
    const val VIEW_PAGER_TOUCH_MODE = false

    /**
     * Default viewpager switching speed
     */
    const val BANNER_DURATION = 800

    /**
     * The Glide default error placeholder
     */
    val GLIDE_ERROR_DRAWABLE: Drawable = ColorDrawable(Color.DKGRAY)

    /**
     * The Glide default placeholder for the load
     */
    val GLIDE_PLACEHOLDER_DRAWABLE: Drawable = ColorDrawable(Color.DKGRAY)

    /**
     * this is BannerTipsLayout default setting
     */
    const val TIPS_LAYOUT_BACKGROUND = Color.BLACK
    val TIPS_LAYOUT_WIDTH: Int = BannerLayout.MATCH_PARENT
    val TIPS_LAYOUT_HEIGHT: Int = BannerLayout.WRAP_CONTENT
    const val IS_TIPS_LAYOUT_BACKGROUND = false
    val TIPS_SITE: Int = TipsLayoutSiteMode.BOTTOM

    /**
     * this is dots default setting
     */
    const val IS_VISIBLE_DOTS = true
    const val DOTS_LEFT_MARGIN = 2f
    const val DOTS_TOP_MARGIN = 0f
    const val DOTS_RIGHT_MARGIN = 2f
    const val DOTS_BOTTOM_MARGIN = 20f
    const val DOTS_WIDth = 5f
    const val DOTS_HEIGHT = 5f
    const val DOTS_ENABLED_RADIUS = 20.0f
    const val DOTS_NORMAL_RADIUS = 20.0f
    const val DOTS_ENABLED_COLOR = Color.RED
    const val DOTS_NORMAL_COLOR = Color.WHITE
    const val DOTS_SELECTOR = 0
    val DOTS_SITE = intArrayOf(TipsDotsSiteMode.BOTTOM, TipsDotsSiteMode.CENTER_HORIZONTAL)

    /**
     * this is title default setting
     */
    const val IS_VISIBLE_TITLE = false
    const val TITLE_SIZE = 13.0f
    const val TITLE_COLOR = Color.BLACK
    const val TITLE_LEFT_MARGIN = 10f
    const val TITLE_TOP_MARGIN = 8f
    const val TITLE_RIGHT_MARGIN = 10f
    const val TITLE_BOTTOM_MARGIN = 8f
    val TITLE_WIDTH: Float = BannerLayout.MATCH_PARENT.toFloat()
    val TITLE_HEIGHT: Float = BannerLayout.WRAP_CONTENT.toFloat()
    const val TITLE_BACKGROUND_COLOR = 0x50000000
    val TITLE_SITE = intArrayOf(TipsTitleSiteMode.LEFT, TipsTitleSiteMode.BOTTOM)

    /**
     * this is pageNumberTextView default setting
     */
    const val PAGE_NUM_VIEW_RADIUS = 25.0f
    const val PAGE_NUM_VIEW_LEFT_MARGIN = 0f
    const val PAGE_NUM_VIEW_TOP_MARGIN = 0f
    const val PAGE_NUM_VIEW_RIGHT_MARGIN = 15f
    const val PAGE_NUM_VIEW_BOTTOM_MARGIN = 0f
    const val PAGE_NUM_VIEW_SIZE = 10.0f
    const val PAGE_NUM_VIEW_PADDING_LEFT = 5f
    const val PAGE_NUM_VIEW_PADDING_TOP = 0f
    const val PAGE_NUM_VIEW_PADDING_RIGHT = 5f
    const val PAGE_NUM_VIEW_PADDING_BOTTOM = 0f
    const val PAGE_NUM_VIEW_BACKGROUND = Color.BLACK
    const val PAGE_NUL_VIEW_TEXT_COLOR = Color.WHITE
    const val PAGE_NUM_VIEW_MARK = " / "
    val PAGE_NUM_VIEW_SITE: Int = TipsPageNumSiteMode.TOP_RIGHT

    /**
     * this is progressDrawable default setting
     */
    const val IS_VISIBLE_PROGRESSES = false
    const val PROGRESSES_LEFT_MARGIN = 2.5f
    const val PROGRESSES_TOP_MARGIN = 0f
    const val PROGRESSES_RIGHT_MARGIN = 2.5f
    const val PROGRESSES_BOTTOM_MARGIN = 0f
    val PROGRESSES_SITE = intArrayOf(TipsProgressesSiteMode.BOTTOM, TipsProgressesSiteMode.CENTER_HORIZONTAL)
}