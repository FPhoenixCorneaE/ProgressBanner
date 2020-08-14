package com.fphoenixcorneae.bannerlayout.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Message
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import androidx.viewpager.widget.ViewPager.PageTransformer
import com.fphoenixcorneae.bannerlayout.annotation.*
import com.fphoenixcorneae.bannerlayout.listener.*
import com.fphoenixcorneae.bannerlayout.utils.BannerHandlerUtils
import com.fphoenixcorneae.bannerlayout.utils.BannerSelectorUtils
import com.fphoenixcorneae.bannerlayout.utils.TransformerUtils
import com.fphoenixcorneae.bannerlayout.widget.BannerPageNumView.PageNumViewInterface
import com.fphoenixcorneae.bannerlayout.widget.BannerTipsLayout.*
import com.fphoenixcorneae.transformer.VerticalTransformer
import java.util.*

class BannerLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : FrameLayout(context, attrs), ViewPagerCurrent, OnPageChangeListener, OnBannerImageClickListener, DotsInterface, TitleInterface, TipsInterface, PageNumViewInterface, ProgressInterface {
    private var transformerList: MutableList<PageTransformer>? = null
    private var onBannerClickListener: OnBannerClickListener? = null
    private var imageList: List<BannerModelCallBack<*>>? = null
    private var viewPager: BannerViewPager? = null
    private var bannerHandlerUtils: BannerHandlerUtils? = null
    private var bannerTipLayout: BannerTipsLayout? = null
    private var imageLoaderManage: ImageDisplayManager? = null
    private var bannerAdapter: BannerAdapter? = null
    private var pageView: BannerPageNumView? = null
    private var onBannerChangeListener: OnBannerChangeListener? = null
    private var bannerTransformer: PageTransformer? = null
    private var preEnablePosition = 0
    private var isGuide = false
    private var isVertical = false
    private var isStartRotation = false
    private var delayTime: Long = 0

    /**
     * viewpager是否能翻页,true为不能,false为可以
     */
    private var viePagerTouchMode = false
    private var mDuration = 0
    private var errorDrawable: Drawable? = null
    private var placeholderDrawable: Drawable? = null
    private var isTipsBackground = false

    /**
     * tips setting,the container for dots,progresses,title
     */
    private var tipsLayoutHeight = 0
    private var tipsLayoutWidth = 0
    private var tipsBackgroundColor = 0
    private var tipsSite = 0

    /**
     * dots setting
     */
    private var isVisibleDots = false
    private var dotsWidth = 0f
    private var dotsHeight = 0f
    private var dotsLeftMargin = 0f
    private var dotsTopMargin = 0f
    private var dotsRightMargin = 0f
    private var dotsBottomMargin = 0f
    private var enabledRadius = 0f
    private var normalRadius = 0f
    private var enabledColor = 0
    private var normalColor = 0

    @DrawableRes
    private var dotsSelector = 0

    @TipsDotsSiteMode
    private var dotsSite: IntArray? = null

    /**
     * progresses setting
     */
    private var isVisibleProgresses = false
    private var progressLeftMargin = 0f
    private var progressTopMargin = 0f
    private var progressRightMargin = 0f
    private var progressBottomMargin = 0f

    @TipsProgressesSiteMode
    private var progressSite: IntArray? = null
    private var progressBuilder: ProgressDrawable.Builder? = null

    /**
     * title setting
     */
    private var isVisibleTitle = false
    private var titleSize = 0f
    private var titleColor = 0
    private var titleLeftMargin = 0f
    private var titleTopMargin = 0f
    private var titleRightMargin = 0f
    private var titleBottomMargin = 0f
    private var titleWidth = 0f
    private var titleHeight = 0f

    @ColorInt
    private var titleBackgroundColor = 0

    @TipsTitleSiteMode
    private var titleSite: IntArray? = null

    /**
     * pageNumView setting
     */
    private var pageNumViewRadius = 0f
    private var pageNumViewPaddingLeft = 0f
    private var pageNumViewPaddingTop = 0f
    private var pageNumViewPaddingRight = 0f
    private var pageNumViewPaddingBottom = 0f
    private var pageNumViewLeftMargin = 0f
    private var pageNumViewTopMargin = 0f
    private var pageNumViewBottomMargin = 0f
    private var pageNumViewRightMargin = 0f
    private var pageNumViewSite = 0
    private var pageNumViewTextColor = 0
    private var pageNumViewBackgroundColor = 0
    private var pageNumViewTextSize = 0f
    private var pageNumViewMark: String? = null

    /**
     * 初始化属性
     */
    private fun init() {
        isGuide = BannerDefaults.IS_GUIDE
        isVertical = BannerDefaults.IS_VERTICAL
        isStartRotation = BannerDefaults.IS_START_ROTATION
        delayTime = BannerDefaults.DELAY_TIME.toLong()
        viePagerTouchMode = BannerDefaults.VIEW_PAGER_TOUCH_MODE
        mDuration = BannerDefaults.BANNER_DURATION
        errorDrawable = BannerDefaults.GLIDE_ERROR_DRAWABLE
        placeholderDrawable = BannerDefaults.GLIDE_PLACEHOLDER_DRAWABLE

        //------------------------------------------------------------------
        isTipsBackground = BannerDefaults.IS_TIPS_LAYOUT_BACKGROUND
        tipsBackgroundColor = BannerDefaults.TIPS_LAYOUT_BACKGROUND
        tipsLayoutWidth = BannerDefaults.TIPS_LAYOUT_WIDTH
        tipsLayoutHeight = BannerDefaults.TIPS_LAYOUT_HEIGHT
        tipsSite = BannerDefaults.TIPS_SITE

        //------------------------------------------------------------------
        isVisibleDots = BannerDefaults.IS_VISIBLE_DOTS
        dotsLeftMargin = BannerDefaults.DOTS_LEFT_MARGIN
        dotsTopMargin = BannerDefaults.DOTS_TOP_MARGIN
        dotsRightMargin = BannerDefaults.DOTS_RIGHT_MARGIN
        dotsBottomMargin = BannerDefaults.DOTS_BOTTOM_MARGIN
        dotsWidth = BannerDefaults.DOTS_WIDth
        dotsHeight = BannerDefaults.DOTS_HEIGHT
        enabledRadius = BannerDefaults.DOTS_ENABLED_RADIUS
        enabledColor = BannerDefaults.DOTS_ENABLED_COLOR
        normalRadius = BannerDefaults.DOTS_NORMAL_RADIUS
        normalColor = BannerDefaults.DOTS_NORMAL_COLOR
        dotsSelector = BannerDefaults.DOTS_SELECTOR
        dotsSite = BannerDefaults.DOTS_SITE

        //------------------------------------------------------------------
        isVisibleTitle = BannerDefaults.IS_VISIBLE_TITLE
        titleColor = BannerDefaults.TITLE_COLOR
        titleSize = BannerDefaults.TITLE_SIZE
        titleLeftMargin = BannerDefaults.TITLE_LEFT_MARGIN
        titleTopMargin = BannerDefaults.TITLE_TOP_MARGIN
        titleRightMargin = BannerDefaults.TITLE_RIGHT_MARGIN
        titleBottomMargin = BannerDefaults.TITLE_BOTTOM_MARGIN
        titleWidth = BannerDefaults.TITLE_WIDTH
        titleHeight = BannerDefaults.TITLE_HEIGHT
        titleBackgroundColor = BannerDefaults.TITLE_BACKGROUND_COLOR
        titleSite = BannerDefaults.TITLE_SITE

        //------------------------------------------------------------------
        pageNumViewSite = BannerDefaults.PAGE_NUM_VIEW_SITE
        pageNumViewRadius = BannerDefaults.PAGE_NUM_VIEW_RADIUS
        pageNumViewPaddingTop = BannerDefaults.PAGE_NUM_VIEW_PADDING_TOP
        pageNumViewPaddingLeft = BannerDefaults.PAGE_NUM_VIEW_PADDING_LEFT
        pageNumViewPaddingBottom = BannerDefaults.PAGE_NUM_VIEW_PADDING_BOTTOM
        pageNumViewPaddingRight = BannerDefaults.PAGE_NUM_VIEW_PADDING_RIGHT
        pageNumViewTopMargin = BannerDefaults.PAGE_NUM_VIEW_TOP_MARGIN
        pageNumViewRightMargin = BannerDefaults.PAGE_NUM_VIEW_RIGHT_MARGIN
        pageNumViewBottomMargin = BannerDefaults.PAGE_NUM_VIEW_BOTTOM_MARGIN
        pageNumViewLeftMargin = BannerDefaults.PAGE_NUM_VIEW_LEFT_MARGIN
        pageNumViewTextColor = BannerDefaults.PAGE_NUL_VIEW_TEXT_COLOR
        pageNumViewBackgroundColor = BannerDefaults.PAGE_NUM_VIEW_BACKGROUND
        pageNumViewTextSize = BannerDefaults.PAGE_NUM_VIEW_SIZE
        pageNumViewMark = BannerDefaults.PAGE_NUM_VIEW_MARK

        //------------------------------------------------------------------
        isVisibleProgresses = BannerDefaults.IS_VISIBLE_PROGRESSES
        progressLeftMargin = BannerDefaults.PROGRESSES_LEFT_MARGIN
        progressTopMargin = BannerDefaults.PROGRESSES_TOP_MARGIN
        progressRightMargin = BannerDefaults.PROGRESSES_RIGHT_MARGIN
        progressBottomMargin = BannerDefaults.PROGRESSES_BOTTOM_MARGIN
        progressSite = BannerDefaults.PROGRESSES_SITE
        progressBuilder = ProgressDrawable.Builder(context)
    }

    override fun setCurrentItem(page: Int) {
        if (viewPager != null) {
            viewPager!!.currentItem = page
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        if (onBannerChangeListener != null) {
            onBannerChangeListener!!.onPageScrolled(position % dotsSize, positionOffset, positionOffsetPixels)
        }
    }

    override fun onPageSelected(position: Int) {
        val newPosition = position % dotsSize
        if (pageView != null) {
            pageView!!.text = TextUtils.concat((newPosition + 1).toString(), pageNumViewMark, dotsSize.toString())
        }
        if (isVisibleDots) {
            bannerTipLayout?.changeDotsPosition(preEnablePosition, newPosition)
        }
        if (isVisibleProgresses) {
            bannerTipLayout?.changeProgressesPosition(preEnablePosition, newPosition)
        }
        if (isVisibleTitle) {
            bannerTipLayout?.setTitle(imageList!![newPosition].bannerTitle)
        }
        preEnablePosition = newPosition
        if (transformerList != null) {
            if (transformerList!!.size > 1 && !isVertical) {
                if (viewPager != null) {
                    viewPager!!.setPageTransformer(true, transformerList!![(Math.random() * transformerList!!.size.toDouble()).toInt()])
                }
            }
        }
        if (bannerHandlerUtils != null) {
            bannerHandlerUtils!!.sendMessage(Message.obtain(bannerHandlerUtils, BannerHandlerUtils.Companion.MSG_PAGE, if (viewPager != null) viewPager!!.currentItem else 0, 0))
        }
        if (onBannerChangeListener != null) {
            onBannerChangeListener!!.onPageSelected(newPosition)
        }
    }

    override fun onPageScrollStateChanged(state: Int) {
        if (bannerHandlerUtils != null && isStartRotation) {
            bannerHandlerUtils!!.removeCallbacksAndMessages(null)
            when (state) {
                0 -> if (bannerHandlerUtils != null) {
                    bannerHandlerUtils!!.sendEmptyMessageDelayed(BannerHandlerUtils.Companion.MSG_UPDATE, delayTime)
                }
                1 -> if (bannerHandlerUtils != null) {
                    bannerHandlerUtils!!.sendEmptyMessage(BannerHandlerUtils.Companion.MSG_KEEP)
                }
            }
        }
        if (onBannerChangeListener != null) {
            onBannerChangeListener!!.onPageScrollStateChanged(state)
        }
    }

    override fun onBannerClick(view: View, position: Int, model: BannerModelCallBack<*>?) {
        onBannerClickListener?.onBannerClick(view, position, model)
    }

    fun addOnPageChangeListener(onBannerChangeListener: OnBannerChangeListener): BannerLayout {
        this.onBannerChangeListener = onBannerChangeListener
        return this
    }

    fun setGuide(guide: Boolean): BannerLayout {
        isGuide = guide
        return this
    }

    fun initPageNumView(): BannerLayout {
        clearPageView()
        pageView = BannerPageNumView(this.context)
        return this
    }

    fun initTips(): BannerLayout {
        this.initTips(isTipsBackground, isVisibleDots, isVisibleProgresses, isVisibleTitle)
        return this
    }

    fun initTips(isBackgroundColor: Boolean, isVisibleDots: Boolean, isVisibleProgresses: Boolean, isVisibleTitle: Boolean): BannerLayout {
        isTipsBackground = isBackgroundColor
        this.isVisibleDots = isVisibleDots
        this.isVisibleProgresses = isVisibleProgresses
        this.isVisibleTitle = isVisibleTitle
        clearBannerTipLayout()
        bannerTipLayout = BannerTipsLayout(this.context)
        return this
    }

    fun initListResources(imageList: List<BannerModelCallBack<*>>?): BannerLayout {
        this.imageList = imageList
        initBannerMethod()
        return this
    }

    fun setDelayTime(delayTime: Int): BannerLayout {
        this.delayTime = delayTime.toLong()
        return this
    }

    /**
     * 是否开始循环Banner
     *
     * @param isStartRotation true为开始循环
     * @return
     */
    fun startRotation(isStartRotation: Boolean): BannerLayout {
        this.isStartRotation = isStartRotation
        if (bannerHandlerUtils != null) {
            bannerHandlerUtils!!.removeCallbacksAndMessages(null)
        }
        if (isStartRotation) {
            if (bannerHandlerUtils != null) {
                bannerHandlerUtils!!.setDelayTime(delayTime)
                //开始循环时，须等待时间为：this.mDuration + this.delayTime
                bannerHandlerUtils!!.sendEmptyMessageDelayed(BannerHandlerUtils.Companion.MSG_UPDATE, mDuration + delayTime)
            }
            if (isVisibleDots) {
                if (bannerTipLayout != null) {
                    bannerTipLayout!!.changeDotsPosition(preEnablePosition, preEnablePosition)
                }
            }
            if (isVisibleProgresses) {
                if (bannerTipLayout != null) {
                    bannerTipLayout!!.changeProgressesPosition(preEnablePosition, preEnablePosition)
                }
            }
        } else {
            if (bannerHandlerUtils != null) {
                bannerHandlerUtils!!.sendEmptyMessage(BannerHandlerUtils.Companion.MSG_KEEP)
                bannerHandlerUtils!!.removeCallbacksAndMessages(null)
            }
            if (isVisibleProgresses) {
                if (bannerTipLayout != null) {
                    bannerTipLayout!!.stopProgresses(preEnablePosition)
                }
            }
        }
        return this
    }

    fun setErrorDrawableRes(@DrawableRes errorDrawableRes: Int): BannerLayout {
        setErrorDrawable(ContextCompat.getDrawable(this.context, errorDrawableRes))
        return this
    }

    fun setErrorDrawable(errorDrawable: Drawable?): BannerLayout {
        this.errorDrawable = errorDrawable
        return this
    }

    fun setPlaceholderDrawableRes(@DrawableRes placeholderDrawableRes: Int): BannerLayout {
        setPlaceholderDrawable(ContextCompat.getDrawable(this.context, placeholderDrawableRes))
        return this
    }

    fun setPlaceholderDrawable(placeholderDrawable: Drawable?): BannerLayout {
        this.placeholderDrawable = placeholderDrawable
        return this
    }

    fun setDuration(pace: Int): BannerLayout {
        mDuration = pace
        return this
    }

    fun setViewPagerTouchMode(b: Boolean): BannerLayout {
        viePagerTouchMode = b
        return this
    }

    fun setVertical(vertical: Boolean): BannerLayout {
        isVertical = vertical
        return this
    }

    fun setTipsBackgroundColor(@ColorInt colorId: Int): BannerLayout {
        tipsBackgroundColor = colorId
        return this
    }

    fun setTipsWidthAndHeight(width: Int, height: Int): BannerLayout {
        tipsLayoutHeight = height
        tipsLayoutWidth = width
        return this
    }

    fun setTipsSite(@TipsLayoutSiteMode tipsSite: Int): BannerLayout {
        this.tipsSite = tipsSite
        return this
    }

    fun setTitleTextColor(@ColorInt titleColor: Int): BannerLayout {
        this.titleColor = titleColor
        return this
    }

    fun setTitleTextSize(titleSize: Float): BannerLayout {
        this.titleSize = titleSize
        return this
    }

    fun setTitleMargin(leftMargin: Float, topMargin: Float, rightMargin: Float, bottomMargin: Float): BannerLayout {
        titleLeftMargin = leftMargin
        titleTopMargin = topMargin
        titleRightMargin = rightMargin
        titleBottomMargin = bottomMargin
        return this
    }

    fun setTitleMargin(margin: Float): BannerLayout {
        titleLeftMargin = margin
        titleTopMargin = margin
        titleRightMargin = margin
        titleBottomMargin = margin
        return this
    }

    fun setTitleWidthAndHeight(titleWidth: Int, titleHeight: Int): BannerLayout {
        this.titleWidth = titleWidth.toFloat()
        this.titleHeight = titleHeight.toFloat()
        return this
    }

    fun setTitleBackgroundColor(@ColorInt titleBackgroundColor: Int): BannerLayout {
        this.titleBackgroundColor = titleBackgroundColor
        return this
    }

    /**
     * @param titleSite [TipsTitleSiteMode]
     * @return
     */
    fun setTitleSite(vararg titleSite: Int): BannerLayout {
        this.titleSite = titleSite
        return this
    }

    fun setDotsWidthAndHeight(width: Float, height: Float): BannerLayout {
        dotsWidth = width
        dotsHeight = height
        return this
    }

    /**
     * @param dotsSite [TipsDotsSiteMode]
     * @return
     */
    fun setDotsSite(vararg dotsSite: Int): BannerLayout {
        this.dotsSite = dotsSite
        return this
    }

    fun setDotsMargin(leftMargin: Float, topMargin: Float, rightMargin: Float, bottomMargin: Float): BannerLayout {
        dotsLeftMargin = leftMargin
        dotsTopMargin = topMargin
        dotsRightMargin = rightMargin
        dotsBottomMargin = bottomMargin
        return this
    }

    fun setDotsMargin(margin: Float): BannerLayout {
        dotsLeftMargin = margin
        dotsRightMargin = margin
        dotsTopMargin = margin
        dotsBottomMargin = margin
        return this
    }

    fun setDotsNormalRadius(normalRadius: Float): BannerLayout {
        this.normalRadius = normalRadius
        return this
    }

    fun setDotsNormalColor(@ColorInt normalColor: Int): BannerLayout {
        this.normalColor = normalColor
        return this
    }

    fun setDotsEnabledColor(@ColorInt enabledColor: Int): BannerLayout {
        this.enabledColor = enabledColor
        return this
    }

    fun setDotsEnabledRadius(enabledRadius: Float): BannerLayout {
        this.enabledRadius = enabledRadius
        return this
    }

    fun setDotsSelectorRes(@DrawableRes dotsSelector: Int): BannerLayout {
        this.dotsSelector = dotsSelector
        return this
    }

    /**
     * @param progressSite [TipsProgressesSiteMode]
     * @return
     */
    fun setProgressesSite(vararg progressSite: Int): BannerLayout {
        this.progressSite = progressSite
        return this
    }

    fun setProgressesMargin(leftMargin: Float, topMargin: Float, rightMargin: Float, bottomMargin: Float): BannerLayout {
        progressLeftMargin = leftMargin
        progressTopMargin = topMargin
        progressRightMargin = rightMargin
        progressBottomMargin = bottomMargin
        return this
    }

    fun setProgressesMargin(margin: Float): BannerLayout {
        progressLeftMargin = margin
        progressRightMargin = margin
        return this
    }

    fun setProgressesBuilder(progressBuilder: ProgressDrawable.Builder?): BannerLayout {
        this.progressBuilder = progressBuilder?.setDuration(mDuration.toLong())
        return this
    }

    fun setBannerTransformer(@PageTransformerMode type: Int): BannerLayout {
        this.setBannerTransformer(TransformerUtils.getTransformer(type))
        return this
    }

    fun setBannerTransformer(bannerTransformer: PageTransformer): BannerLayout {
        if (isVertical) {
            this.bannerTransformer = VerticalTransformer()
        } else {
            this.bannerTransformer = bannerTransformer
        }
        if (viewPager != null) {
            viewPager!!.setPageTransformer(true, this.bannerTransformer)
        }
        return this
    }

    fun setBannerSystemTransformerList(list: List<Int>): BannerLayout {
        val bannerTransformers = ArrayList<PageTransformer>()
        for (i in list.indices) {
            bannerTransformers.add(TransformerUtils.getTransformer((list[i])))
        }
        setBannerTransformerList(bannerTransformers)
        return this
    }

    fun setBannerTransformerList(list: MutableList<PageTransformer>): BannerLayout {
        transformerList = list
        return this
    }

    fun setPageNumViewRadius(pageNumViewRadius: Float): BannerLayout {
        this.pageNumViewRadius = pageNumViewRadius
        return this
    }

    fun setPageNumViewPadding(left: Float, top: Float, right: Float, bottom: Float): BannerLayout {
        pageNumViewPaddingLeft = left
        pageNumViewPaddingTop = top
        pageNumViewPaddingRight = right
        pageNumViewPaddingBottom = bottom
        return this
    }

    fun setPageNumViewPadding(padding: Float): BannerLayout {
        pageNumViewPaddingLeft = padding
        pageNumViewPaddingTop = padding
        pageNumViewPaddingRight = padding
        pageNumViewPaddingBottom = padding
        return this
    }

    fun setPageNumViewMargin(left: Float, top: Float, right: Float, bottom: Float): BannerLayout {
        pageNumViewLeftMargin = left
        pageNumViewTopMargin = top
        pageNumViewRightMargin = right
        pageNumViewBottomMargin = bottom
        return this
    }

    fun setPageNumViewMargin(margin: Int): BannerLayout {
        pageNumViewTopMargin = margin.toFloat()
        pageNumViewBottomMargin = margin.toFloat()
        pageNumViewLeftMargin = margin.toFloat()
        pageNumViewRightMargin = margin.toFloat()
        return this
    }

    fun setPageNumViewTextColor(@ColorInt pageNumViewTextColor: Int): BannerLayout {
        this.pageNumViewTextColor = pageNumViewTextColor
        return this
    }

    fun setPageNumViewBackgroundColor(@ColorInt pageNumViewBackgroundColor: Int): BannerLayout {
        this.pageNumViewBackgroundColor = pageNumViewBackgroundColor
        return this
    }

    fun setPageNumViewTextSize(pageNumViewTextSize: Float): BannerLayout {
        this.pageNumViewTextSize = pageNumViewTextSize
        return this
    }

    fun setPageNumViewSite(@TipsPageNumSiteMode pageNumViewSite: Int): BannerLayout {
        this.pageNumViewSite = pageNumViewSite
        return this
    }

    fun setPageNumViewMark(pageNumViewMark: String): BannerLayout {
        this.pageNumViewMark = pageNumViewMark
        return this
    }

    fun setPageNumViewMark(@StringRes pageNumViewMark: Int): BannerLayout {
        this.pageNumViewMark = this.context.getString(pageNumViewMark)
        return this
    }

    fun setOnBannerClickListener(onBannerClickListener: OnBannerClickListener): BannerLayout {
        this.onBannerClickListener = onBannerClickListener
        return this
    }

    fun setImageLoaderManager(loaderManage: ImageDisplayManager): BannerLayout {
        imageLoaderManage = loaderManage
        return this
    }

    private fun initBannerMethod(): BannerLayout {
        clearHandler()
        removeAllViews()
        preEnablePosition = 0
        setProgressesBuilder(progressBuilder)
        val currentPosition = if (isGuide) 0 else Int.MAX_VALUE / 2 - Int.MAX_VALUE / 2 % dotsSize
        bannerHandlerUtils = BannerHandlerUtils(this, currentPosition)
        bannerHandlerUtils!!.setDelayTime(delayTime)
        bannerAdapter = BannerAdapter(imageList!!, imageLoaderManage, errorDrawable, placeholderDrawable, isGuide)
        bannerAdapter!!.setImageClickListener(this)
        viewPager = BannerViewPager(this.context)
        viewPager?.let {
            it.setDuration(mDuration)
            it.setViewTouchMode(viePagerTouchMode)
            it.addOnPageChangeListener(this)
            it.adapter = bannerAdapter
            if (isVertical) {
                bannerTransformer = VerticalTransformer()
                it.setVertical(true)
            }
            it.setPageTransformer(true, bannerTransformer)
            this.addView(it)
            it.currentItem = currentPosition
        }
        pageView?.let {
            it.text = TextUtils.concat(1.toString(), pageNumViewMark, imageList!!.size.toString())
            this.addView(it, it.initPageView(this))
        }
        bannerTipLayout?.let {
            it.removeAllViews()
            if (isVisibleDots) {
                it.setDots(this)
            }
            if (isVisibleProgresses) {
                it.setProgresses(this)
            }
            if (isVisibleTitle) {
                it.setTitle(this)
                if (imageList?.isNotEmpty() == true) {
                    it.setTitle(imageList!![0].bannerTitle)
                }
            }
            this.addView(it, it.setBannerTips(this))
        }
        return this
    }

    override fun dotsCount(): Int {
        return dotsSize
    }

    override fun dotsSelector(): Drawable {
        var drawable: Drawable? = null
        try {
            drawable = ContextCompat.getDrawable(this.context, dotsSelector)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (drawable == null) {
            drawable = BannerSelectorUtils.getDrawableSelector(
                    this.context,
                    enabledRadius,
                    enabledColor,
                    normalRadius,
                    normalColor
            )
        }
        return drawable
    }

    override fun dotsHeight(): Float {
        return dotsHeight
    }

    override fun dotsWidth(): Float {
        return dotsWidth
    }

    override fun dotsLeftMargin(): Float {
        return dotsLeftMargin
    }

    override fun dotsTopMargin(): Float {
        return dotsTopMargin
    }

    override fun dotsRightMargin(): Float {
        return dotsRightMargin
    }

    override fun dotsBottomMargin(): Float {
        return dotsBottomMargin
    }

    override fun dotsSite(): IntArray? {
        return dotsSite
    }

    override fun progressCount(): Int {
        return imageList!!.size
    }

    override fun progressLeftMargin(): Float {
        return progressLeftMargin
    }

    override fun progressTopMargin(): Float {
        return progressTopMargin
    }

    override fun progressRightMargin(): Float {
        return progressRightMargin
    }

    override fun progressBottomMargin(): Float {
        return progressBottomMargin
    }

    override fun progressSite(): IntArray? {
        return progressSite
    }

    override fun progressBuilder(): ProgressDrawable.Builder? {
        return progressBuilder
    }

    override fun titleColor(): Int {
        return titleColor
    }

    override fun titleSize(): Float {
        return titleSize
    }

    override fun titleLeftMargin(): Float {
        return titleLeftMargin
    }

    override fun titleTopMargin(): Float {
        return titleTopMargin
    }

    override fun titleRightMargin(): Float {
        return titleRightMargin
    }

    override fun titleBottomMargin(): Float {
        return titleBottomMargin
    }

    override fun titleWidth(): Float {
        return titleWidth
    }

    override fun titleHeight(): Float {
        return titleHeight
    }

    override fun titleBackgroundColor(): Int {
        return titleBackgroundColor
    }

    override fun titleSite(): IntArray? {
        return titleSite
    }

    override fun tipsSite(): Int {
        return tipsSite
    }

    override fun tipsWidth(): Int {
        return tipsLayoutWidth
    }

    override fun tipsHeight(): Int {
        return tipsLayoutHeight
    }

    override fun tipsLayoutBackgroundColor(): Int {
        return tipsBackgroundColor
    }

    override fun showBackgroundColor(): Boolean {
        return isTipsBackground
    }

    override fun pageNumViewTopMargin(): Float {
        return pageNumViewTopMargin
    }

    override fun pageNumViewRightMargin(): Float {
        return pageNumViewRightMargin
    }

    override fun pageNumViewBottomMargin(): Float {
        return pageNumViewBottomMargin
    }

    override fun pageNumViewLeftMargin(): Float {
        return pageNumViewLeftMargin
    }

    override fun pageNumViewSite(): Int {
        return pageNumViewSite
    }

    override fun pageNumViewTextColor(): Int {
        return pageNumViewTextColor
    }

    override fun pageNumViewTextSize(): Float {
        return pageNumViewTextSize
    }

    override fun pageNumViewPaddingTop(): Float {
        return pageNumViewPaddingTop
    }

    override fun pageNumViewPaddingLeft(): Float {
        return pageNumViewPaddingLeft
    }

    override fun pageNumViewPaddingBottom(): Float {
        return pageNumViewPaddingBottom
    }

    override fun pageNumViewPaddingRight(): Float {
        return pageNumViewPaddingRight
    }

    override fun pageNumViewRadius(): Float {
        return pageNumViewRadius
    }

    override fun pageNumViewBackgroundColor(): Int {
        return pageNumViewBackgroundColor
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        clearBanner()
    }

    /**
     * 释放资源
     *
     * @return
     */
    fun clearBanner(): BannerLayout {
        return clearViewPager()
                .clearHandler()
                .clearBannerTipLayout()
                .clearTransformerList()
                .clearPageView()
    }

    fun clearViewPager(): BannerLayout {
        if (viewPager != null) {
            viewPager!!.removeAllViews()
            removeView(viewPager)
            viewPager = null
        }
        return this
    }

    fun clearTransformerList(): BannerLayout {
        if (transformerList != null) {
            transformerList!!.clear()
            transformerList = null
        }
        return this
    }

    fun clearHandler(): BannerLayout {
        if (bannerHandlerUtils != null) {
            bannerHandlerUtils!!.removeCallbacksAndMessages(null)
            bannerHandlerUtils = null
        }
        return this
    }

    fun clearBannerTipLayout(): BannerLayout {
        if (bannerTipLayout != null) {
            bannerTipLayout!!.removeAllViews()
            removeView(bannerTipLayout)
            bannerTipLayout = null
        }
        return this
    }

    fun clearPageView(): BannerLayout {
        if (pageView != null) {
            removeView(pageView)
            pageView = null
        }
        return this
    }

    fun getImageList(): List<*>? {
        return imageList
    }

    private val dotsSize: Int
        get() = imageList!!.size

    val duration: Int
        get() = viewPager?.duration ?: 0

    val bannerStatus: Int
        get() = bannerHandlerUtils?.status ?: 0

    companion object {
        const val MATCH_PARENT = LayoutParams.MATCH_PARENT
        const val WRAP_CONTENT = LayoutParams.WRAP_CONTENT
    }

    init {
        init()
    }
}