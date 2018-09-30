package com.wkz.bannerlayout.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Message;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.wkz.bannerlayout.animation.BannerTransformer;
import com.wkz.bannerlayout.animation.VerticalTransformer;
import com.wkz.bannerlayout.annotation.PageTransformerMode;
import com.wkz.bannerlayout.annotation.TipsDotsSiteMode;
import com.wkz.bannerlayout.annotation.TipsLayoutSiteMode;
import com.wkz.bannerlayout.annotation.TipsPageNumSiteMode;
import com.wkz.bannerlayout.annotation.TipsProgressesSiteMode;
import com.wkz.bannerlayout.annotation.TipsTitleSiteMode;
import com.wkz.bannerlayout.listener.BannerModelCallBack;
import com.wkz.bannerlayout.listener.ImageDisplayManager;
import com.wkz.bannerlayout.listener.OnBannerChangeListener;
import com.wkz.bannerlayout.listener.OnBannerClickListener;
import com.wkz.bannerlayout.listener.OnBannerImageClickListener;
import com.wkz.bannerlayout.listener.ViewPagerCurrent;
import com.wkz.bannerlayout.utils.BannerHandlerUtils;
import com.wkz.bannerlayout.utils.BannerSelectorUtils;
import com.wkz.bannerlayout.utils.TransformerUtils;

import java.util.ArrayList;
import java.util.List;

public final class BannerLayout extends FrameLayout implements ViewPagerCurrent, ViewPager.OnPageChangeListener, OnBannerImageClickListener, BannerTipsLayout.DotsInterface, BannerTipsLayout.TitleInterface, BannerTipsLayout.TipsInterface, BannerPageNumView.PageNumViewInterface, BannerTipsLayout.ProgressInterface {

    public static final int MATCH_PARENT = FrameLayout.LayoutParams.MATCH_PARENT;
    public static final int WRAP_CONTENT = FrameLayout.LayoutParams.WRAP_CONTENT;

    private List transformerList;
    private OnBannerClickListener onBannerClickListener;
    private List<BannerModelCallBack> imageList;
    private BannerViewPager viewPager;
    private BannerHandlerUtils bannerHandlerUtils;
    private BannerTipsLayout bannerTipLayout;
    private ImageDisplayManager imageLoaderManage;
    private BannerAdapter adapter;
    private BannerPageNumView pageView;
    private OnBannerChangeListener onBannerChangeListener;
    private BannerTransformer bannerTransformer;
    private int preEnablePosition;


    private boolean isGuide;
    private boolean isVertical;
    private boolean isStartRotation;
    private long delayTime;
    /**
     * viewpager是否能翻页,true为不能,false为可以
     */
    private boolean viePagerTouchMode;
    private int mDuration;
    private Drawable errorDrawable;
    private Drawable placeholderDrawable;
    private boolean isTipsBackground;

    /**
     * tips setting,the container for dots,progresses,title
     */
    private int tipsLayoutHeight;
    private int tipsLayoutWidth;
    private int tipsBackgroundColor;
    private int tipsSite;

    /**
     * dots setting
     */
    private boolean isVisibleDots;
    private float dotsWidth;
    private float dotsHeight;
    private float dotsLeftMargin;
    private float dotsTopMargin;
    private float dotsRightMargin;
    private float dotsBottomMargin;
    private float enabledRadius;
    private float normalRadius;
    private int enabledColor;
    private int normalColor;
    @DrawableRes
    private int dotsSelector;
    @TipsDotsSiteMode
    private int[] dotsSite;

    /**
     * progresses setting
     */
    private boolean isVisibleProgresses;
    private float progressLeftMargin;
    private float progressTopMargin;
    private float progressRightMargin;
    private float progressBottomMargin;
    @TipsProgressesSiteMode
    private int[] progressSite;
    private ProgressDrawable.Builder progressBuilder;

    /**
     * title setting
     */
    private boolean isVisibleTitle;
    private float titleSize;
    private int titleColor;
    private float titleLeftMargin;
    private float titleTopMargin;
    private float titleRightMargin;
    private float titleBottomMargin;
    private float titleWidth;
    private float titleHeight;
    @ColorInt
    private int titleBackgroundColor;
    @TipsTitleSiteMode
    private int[] titleSite;

    /**
     * pageNumView settting
     */
    private float pageNumViewRadius;
    private float pageNumViewPaddingLeft;
    private float pageNumViewPaddingTop;
    private float pageNumViewPaddingRight;
    private float pageNumViewPaddingBottom;
    private float pageNumViewLeftMargin;
    private float pageNumViewTopMargin;
    private float pageNumViewBottomMargin;
    private float pageNumViewRightMargin;
    private int pageNumViewSite;
    private int pageNumViewTextColor;
    private int pageNumViewBackgroundColor;
    private float pageNumViewTextSize;
    private String pageNumViewMark;

    /**
     * 初始化属性
     */
    private void init() {
        this.isGuide = BannerDefaults.IS_GUIDE;
        this.isVertical = BannerDefaults.IS_VERTICAL;
        this.isStartRotation = BannerDefaults.IS_START_ROTATION;
        this.delayTime = BannerDefaults.DELAY_TIME;
        this.viePagerTouchMode = BannerDefaults.VIEW_PAGER_TOUCH_MODE;
        this.mDuration = BannerDefaults.BANNER_DURATION;
        this.errorDrawable = BannerDefaults.GLIDE_ERROR_DRAWABLE;
        this.placeholderDrawable = BannerDefaults.GLIDE_PLACEHOLDER_DRAWABLE;

        //------------------------------------------------------------------

        this.isTipsBackground = BannerDefaults.IS_TIPS_LAYOUT_BACKGROUND;
        this.tipsBackgroundColor = BannerDefaults.TIPS_LAYOUT_BACKGROUND;
        this.tipsLayoutWidth = BannerDefaults.TIPS_LAYOUT_WIDTH;
        this.tipsLayoutHeight = BannerDefaults.TIPS_LAYOUT_HEIGHT;
        this.tipsSite = BannerDefaults.TIPS_SITE;

        //------------------------------------------------------------------

        this.isVisibleDots = BannerDefaults.IS_VISIBLE_DOTS;
        this.dotsLeftMargin = BannerDefaults.DOTS_LEFT_MARGIN;
        this.dotsTopMargin = BannerDefaults.DOTS_TOP_MARGIN;
        this.dotsRightMargin = BannerDefaults.DOTS_RIGHT_MARGIN;
        this.dotsBottomMargin = BannerDefaults.DOTS_BOTTOM_MARGIN;
        this.dotsWidth = BannerDefaults.DOTS_WIDth;
        this.dotsHeight = BannerDefaults.DOTS_HEIGHT;
        this.enabledRadius = BannerDefaults.DOTS_ENABLED_RADIUS;
        this.enabledColor = BannerDefaults.DOTS_ENABLED_COLOR;
        this.normalRadius = BannerDefaults.DOTS_NORMAL_RADIUS;
        this.normalColor = BannerDefaults.DOTS_NORMAL_COLOR;
        this.dotsSelector = BannerDefaults.DOTS_SELECTOR;
        this.dotsSite = BannerDefaults.DOTS_SITE;

        //------------------------------------------------------------------

        this.isVisibleTitle = BannerDefaults.IS_VISIBLE_TITLE;
        this.titleColor = BannerDefaults.TITLE_COLOR;
        this.titleSize = BannerDefaults.TITLE_SIZE;
        this.titleLeftMargin = BannerDefaults.TITLE_LEFT_MARGIN;
        this.titleTopMargin = BannerDefaults.TITLE_TOP_MARGIN;
        this.titleRightMargin = BannerDefaults.TITLE_RIGHT_MARGIN;
        this.titleBottomMargin = BannerDefaults.TITLE_BOTTOM_MARGIN;
        this.titleWidth = BannerDefaults.TITLE_WIDTH;
        this.titleHeight = BannerDefaults.TITLE_HEIGHT;
        this.titleBackgroundColor = BannerDefaults.TITLE_BACKGROUND_COLOR;
        this.titleSite = BannerDefaults.TITLE_SITE;

        //------------------------------------------------------------------

        this.pageNumViewSite = BannerDefaults.PAGE_NUM_VIEW_SITE;
        this.pageNumViewRadius = BannerDefaults.PAGE_NUM_VIEW_RADIUS;
        this.pageNumViewPaddingTop = BannerDefaults.PAGE_NUM_VIEW_PADDING_TOP;
        this.pageNumViewPaddingLeft = BannerDefaults.PAGE_NUM_VIEW_PADDING_LEFT;
        this.pageNumViewPaddingBottom = BannerDefaults.PAGE_NUM_VIEW_PADDING_BOTTOM;
        this.pageNumViewPaddingRight = BannerDefaults.PAGE_NUM_VIEW_PADDING_RIGHT;
        this.pageNumViewTopMargin = BannerDefaults.PAGE_NUM_VIEW_TOP_MARGIN;
        this.pageNumViewRightMargin = BannerDefaults.PAGE_NUM_VIEW_RIGHT_MARGIN;
        this.pageNumViewBottomMargin = BannerDefaults.PAGE_NUM_VIEW_BOTTOM_MARGIN;
        this.pageNumViewLeftMargin = BannerDefaults.PAGE_NUM_VIEW_LEFT_MARGIN;
        this.pageNumViewTextColor = BannerDefaults.PAGE_NUL_VIEW_TEXT_COLOR;
        this.pageNumViewBackgroundColor = BannerDefaults.PAGE_NUM_VIEW_BACKGROUND;
        this.pageNumViewTextSize = BannerDefaults.PAGE_NUM_VIEW_SIZE;
        this.pageNumViewMark = BannerDefaults.PAGE_NUM_VIEW_MARK;

        //------------------------------------------------------------------

        this.isVisibleProgresses = BannerDefaults.IS_VISIBLE_PROGRESSES;
        this.progressLeftMargin = BannerDefaults.PROGRESSES_LEFT_MARGIN;
        this.progressTopMargin = BannerDefaults.PROGRESSES_TOP_MARGIN;
        this.progressRightMargin = BannerDefaults.PROGRESSES_RIGHT_MARGIN;
        this.progressBottomMargin = BannerDefaults.PROGRESSES_BOTTOM_MARGIN;
        this.progressSite = BannerDefaults.PROGRESSES_SITE;
        this.progressBuilder = new ProgressDrawable.Builder(getContext())
                .setDuration(this.mDuration);
    }

    public void setCurrentItem(int page) {
        if (this.viewPager != null) {
            this.viewPager.setCurrentItem(page);
        }

    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (this.onBannerChangeListener != null) {
            this.onBannerChangeListener.onPageScrolled(position % this.getDotsSize(), positionOffset, positionOffsetPixels);
        }
    }

    public void onPageSelected(int position) {
        int newPosition = position % this.getDotsSize();
        if (this.pageView != null) {
            this.pageView.setText(TextUtils.concat(String.valueOf(newPosition + 1), this.pageNumViewMark, String.valueOf(this.getDotsSize())));
        }

        if (this.isVisibleDots) {
            if (this.bannerTipLayout != null) {
                this.bannerTipLayout.changeDotsPosition(this.preEnablePosition, newPosition);
            }
        }

        if (this.isVisibleProgresses) {
            if (this.bannerTipLayout != null) {
                this.bannerTipLayout.changeProgressesPosition(this.preEnablePosition, newPosition);
            }
        }

        if (this.isVisibleTitle) {
            if (this.bannerTipLayout != null) {
                this.bannerTipLayout.setTitle(this.imageList.get(newPosition).getBannerTitle());
            }
        }

        this.preEnablePosition = newPosition;
        if (this.transformerList != null) {
            if (this.transformerList.size() > 1 && !this.isVertical) {
                if (this.viewPager != null) {
                    this.viewPager.setPageTransformer(true, (ViewPager.PageTransformer) this.transformerList.get((int) (Math.random() * (double) this.transformerList.size())));
                }
            }
        }

        if (this.bannerHandlerUtils != null) {
            this.bannerHandlerUtils.sendMessage(Message.obtain(this.bannerHandlerUtils, BannerHandlerUtils.MSG_PAGE, this.viewPager != null ? this.viewPager.getCurrentItem() : 0, 0));
        }

        if (this.onBannerChangeListener != null) {
            this.onBannerChangeListener.onPageSelected(newPosition);
        }

    }

    public void onPageScrollStateChanged(int state) {
        if (this.bannerHandlerUtils != null && this.isStartRotation) {
            bannerHandlerUtils.removeCallbacksAndMessages(null);

            switch (state) {
                case 0:
                    if (this.bannerHandlerUtils != null) {
                        bannerHandlerUtils.sendEmptyMessageDelayed(BannerHandlerUtils.MSG_UPDATE, this.delayTime);
                    }
                    break;
                case 1:
                    if (this.bannerHandlerUtils != null) {
                        bannerHandlerUtils.sendEmptyMessage(BannerHandlerUtils.MSG_KEEP);
                    }
            }
        }

        if (this.onBannerChangeListener != null) {
            this.onBannerChangeListener.onPageScrollStateChanged(state);
        }

    }

    public void onBannerClick(@NonNull View view, int position, @NonNull BannerModelCallBack model) {
        if (this.onBannerClickListener != null) {
            onBannerClickListener.onBannerClick(view, position, model);
        }
    }

    @NonNull
    public final BannerLayout addOnPageChangeListener(@NonNull OnBannerChangeListener onBannerChangeListener) {
        this.onBannerChangeListener = onBannerChangeListener;
        return this;
    }

    @NonNull
    public final BannerLayout setGuide(boolean guide) {
        this.isGuide = guide;
        return this;
    }

    @NonNull
    public final BannerLayout initPageNumView() {
        this.clearPageView();
        this.pageView = new BannerPageNumView(this.getContext());
        return this;
    }

    @NonNull
    public final BannerLayout initTips() {
        this.initTips(this.isTipsBackground, this.isVisibleDots, this.isVisibleProgresses, this.isVisibleTitle);
        return this;
    }

    @NonNull
    public final BannerLayout initTips(boolean isBackgroundColor, boolean isVisibleDots, boolean isVisibleProgresses, boolean isVisibleTitle) {
        this.isTipsBackground = isBackgroundColor;
        this.isVisibleDots = isVisibleDots;
        this.isVisibleProgresses = isVisibleProgresses;
        this.isVisibleTitle = isVisibleTitle;
        this.clearBannerTipLayout();
        this.bannerTipLayout = new BannerTipsLayout(this.getContext());
        return this;
    }

    @NonNull
    public final BannerLayout initListResources(@NonNull List imageList) {
        this.imageList = imageList;
        this.initBannerMethod();
        return this;
    }

    @NonNull
    public final BannerLayout setDelayTime(int delayTime) {
        this.delayTime = (long) delayTime;
        return this;
    }

    /**
     * 是否开始循环Banner
     *
     * @param isStartRotation true为开始循环
     * @return
     */
    @NonNull
    public final BannerLayout startRotation(boolean isStartRotation) {
        this.isStartRotation = isStartRotation;
        if (this.bannerHandlerUtils != null) {
            this.bannerHandlerUtils.removeCallbacksAndMessages(null);
        }

        if (isStartRotation) {
            if (this.bannerHandlerUtils != null) {
                this.bannerHandlerUtils.setDelayTime(this.delayTime);
                //开始循环时，须等待时间为：this.mDuration + this.delayTime
                this.bannerHandlerUtils.sendEmptyMessageDelayed(BannerHandlerUtils.MSG_UPDATE, this.mDuration + this.delayTime);
            }
            if (this.isVisibleDots) {
                if (this.bannerTipLayout != null) {
                    this.bannerTipLayout.changeDotsPosition(preEnablePosition, preEnablePosition);
                }
            }
            if (this.isVisibleProgresses) {
                if (this.bannerTipLayout != null) {
                    this.bannerTipLayout.changeProgressesPosition(preEnablePosition, preEnablePosition);
                }
            }
        } else {
            if (this.bannerHandlerUtils != null) {
                this.bannerHandlerUtils.sendEmptyMessage(BannerHandlerUtils.MSG_KEEP);
                this.bannerHandlerUtils.removeCallbacksAndMessages(null);
            }
        }

        return this;
    }

    @NonNull
    public final BannerLayout setErrorDrawableRes(@DrawableRes int errorDrawableRes) {
        this.setErrorDrawable(ContextCompat.getDrawable(this.getContext(), errorDrawableRes));
        return this;
    }

    public BannerLayout setErrorDrawable(Drawable errorDrawable) {
        this.errorDrawable = errorDrawable;
        return this;
    }

    @NonNull
    public final BannerLayout setPlaceholderDrawableRes(@DrawableRes int placeholderDrawableRes) {
        this.setPlaceholderDrawable(ContextCompat.getDrawable(this.getContext(), placeholderDrawableRes));
        return this;
    }

    public BannerLayout setPlaceholderDrawable(Drawable placeholderDrawable) {
        this.placeholderDrawable = placeholderDrawable;
        return this;
    }

    @NonNull
    public final BannerLayout setDuration(int pace) {
        this.mDuration = pace;
        return this;
    }

    @NonNull
    public final BannerLayout setViewPagerTouchMode(boolean b) {
        this.viePagerTouchMode = b;
        return this;
    }

    @NonNull
    public final BannerLayout setVertical(boolean vertical) {
        this.isVertical = vertical;
        return this;
    }

    @NonNull
    public final BannerLayout setTipsBackgroundColor(@ColorInt int colorId) {
        this.tipsBackgroundColor = colorId;
        return this;
    }

    @NonNull
    public final BannerLayout setTipsWidthAndHeight(int width, int height) {
        this.tipsLayoutHeight = height;
        this.tipsLayoutWidth = width;
        return this;
    }

    @NonNull
    public final BannerLayout setTipsSite(@TipsLayoutSiteMode int tipsSite) {
        this.tipsSite = tipsSite;
        return this;
    }

    @NonNull
    public final BannerLayout setTitleTextColor(@ColorInt int titleColor) {
        this.titleColor = titleColor;
        return this;
    }

    @NonNull
    public final BannerLayout setTitleTextSize(float titleSize) {
        this.titleSize = titleSize;
        return this;
    }

    @NonNull
    public final BannerLayout setTitleMargin(float leftMargin, float topMargin, float rightMargin, float bottomMargin) {
        this.titleLeftMargin = leftMargin;
        this.titleTopMargin = topMargin;
        this.titleRightMargin = rightMargin;
        this.titleBottomMargin = bottomMargin;
        return this;
    }

    @NonNull
    public final BannerLayout setTitleMargin(float margin) {
        this.titleLeftMargin = margin;
        this.titleTopMargin = margin;
        this.titleRightMargin = margin;
        this.titleBottomMargin = margin;
        return this;
    }

    public BannerLayout setTitleWidthAndHeight(int titleWidth, int titleHeight) {
        this.titleWidth = titleWidth;
        this.titleHeight = titleHeight;
        return this;
    }

    public BannerLayout setTitleBackgroundColor(@ColorInt int titleBackgroundColor) {
        this.titleBackgroundColor = titleBackgroundColor;
        return this;
    }

    /**
     * @param titleSite {@link TipsTitleSiteMode}
     * @return
     */
    @NonNull
    public final BannerLayout setTitleSite(int... titleSite) {
        this.titleSite = titleSite;
        return this;
    }

    @NonNull
    public final BannerLayout setDotsWidthAndHeight(float width, float height) {
        this.dotsWidth = width;
        this.dotsHeight = height;
        return this;
    }

    /**
     * @param dotsSite {@link TipsDotsSiteMode}
     * @return
     */
    @NonNull
    public final BannerLayout setDotsSite(int... dotsSite) {
        this.dotsSite = dotsSite;
        return this;
    }

    @NonNull
    public final BannerLayout setDotsMargin(float leftMargin, float topMargin, float rightMargin, float bottomMargin) {
        this.dotsLeftMargin = leftMargin;
        this.dotsTopMargin = topMargin;
        this.dotsRightMargin = rightMargin;
        this.dotsBottomMargin = bottomMargin;
        return this;
    }

    @NonNull
    public final BannerLayout setDotsMargin(float margin) {
        this.dotsLeftMargin = margin;
        this.dotsRightMargin = margin;
        this.dotsTopMargin = margin;
        this.dotsBottomMargin = margin;
        return this;
    }

    @NonNull
    public final BannerLayout setDotsNormalRadius(float normalRadius) {
        this.normalRadius = normalRadius;
        return this;
    }

    @NonNull
    public final BannerLayout setDotsNormalColor(@ColorInt int normalColor) {
        this.normalColor = normalColor;
        return this;
    }

    @NonNull
    public final BannerLayout setDotsEnabledColor(@ColorInt int enabledColor) {
        this.enabledColor = enabledColor;
        return this;
    }

    @NonNull
    public final BannerLayout setDotsEnabledRadius(float enabledRadius) {
        this.enabledRadius = enabledRadius;
        return this;
    }

    @NonNull
    public final BannerLayout setDotsSelectorRes(@DrawableRes int dotsSelector) {
        this.dotsSelector = dotsSelector;
        return this;
    }

    /**
     * @param progressSite {@link TipsProgressesSiteMode}
     * @return
     */
    @NonNull
    public final BannerLayout setProgressesSite(int... progressSite) {
        this.progressSite = progressSite;
        return this;
    }

    @NonNull
    public final BannerLayout setProgressesMargin(float leftMargin, float topMargin, float rightMargin, float bottomMargin) {
        this.progressLeftMargin = leftMargin;
        this.progressTopMargin = topMargin;
        this.progressRightMargin = rightMargin;
        this.progressBottomMargin = bottomMargin;
        return this;
    }

    @NonNull
    public final BannerLayout setProgressesMargin(float margin) {
        this.progressLeftMargin = margin;
        this.progressRightMargin = margin;
        return this;
    }

    public BannerLayout setProgressesBuilder(ProgressDrawable.Builder progressBuilder) {
        this.progressBuilder = progressBuilder;
        this.progressBuilder.setDuration(this.mDuration);
        return this;
    }

    @NonNull
    public final BannerLayout setBannerTransformer(@PageTransformerMode int type) {
        this.setBannerTransformer(TransformerUtils.getTransformer(type));
        return this;
    }

    @NonNull
    public final BannerLayout setBannerTransformer(@NonNull BannerTransformer bannerTransformer) {
        if (this.isVertical) {
            this.bannerTransformer = new VerticalTransformer();
        } else {
            this.bannerTransformer = bannerTransformer;
        }
        if (this.viewPager != null) {
            this.viewPager.setPageTransformer(true, this.bannerTransformer);
        }
        return this;
    }

    @NonNull
    public final BannerLayout setBannerSystemTransformerList(@NonNull List list) {
        ArrayList bannerTransformers = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            bannerTransformers.add(TransformerUtils.getTransformer(((Number) list.get(i)).intValue()));
        }

        this.setBannerTransformerList(bannerTransformers);
        return this;
    }

    @NonNull
    public final BannerLayout setBannerTransformerList(@NonNull List list) {
        this.transformerList = list;
        return this;
    }

    @NonNull
    public final BannerLayout setPageNumViewRadius(float pageNumViewRadius) {
        this.pageNumViewRadius = pageNumViewRadius;
        return this;
    }

    @NonNull
    public final BannerLayout setPageNumViewPadding(float left, float top, float right, float bottom) {
        this.pageNumViewPaddingLeft = left;
        this.pageNumViewPaddingTop = top;
        this.pageNumViewPaddingRight = right;
        this.pageNumViewPaddingBottom = bottom;
        return this;
    }

    @NonNull
    public final BannerLayout setPageNumViewPadding(float padding) {
        this.pageNumViewPaddingLeft = padding;
        this.pageNumViewPaddingTop = padding;
        this.pageNumViewPaddingRight = padding;
        this.pageNumViewPaddingBottom = padding;
        return this;
    }

    @NonNull
    public final BannerLayout setPageNumViewMargin(float left, float top, float right, float bottom) {
        this.pageNumViewLeftMargin = left;
        this.pageNumViewTopMargin = top;
        this.pageNumViewRightMargin = right;
        this.pageNumViewBottomMargin = bottom;
        return this;
    }

    @NonNull
    public final BannerLayout setPageNumViewMargin(int margin) {
        this.pageNumViewTopMargin = margin;
        this.pageNumViewBottomMargin = margin;
        this.pageNumViewLeftMargin = margin;
        this.pageNumViewRightMargin = margin;
        return this;
    }

    @NonNull
    public final BannerLayout setPageNumViewTextColor(@ColorInt int pageNumViewTextColor) {
        this.pageNumViewTextColor = pageNumViewTextColor;
        return this;
    }

    @NonNull
    public final BannerLayout setPageNumViewBackgroundColor(@ColorInt int pageNumViewBackgroundColor) {
        this.pageNumViewBackgroundColor = pageNumViewBackgroundColor;
        return this;
    }

    @NonNull
    public final BannerLayout setPageNumViewTextSize(float pageNumViewTextSize) {
        this.pageNumViewTextSize = pageNumViewTextSize;
        return this;
    }

    @NonNull
    public final BannerLayout setPageNumViewSite(@TipsPageNumSiteMode int pageNumViewSite) {
        this.pageNumViewSite = pageNumViewSite;
        return this;
    }

    @NonNull
    public final BannerLayout setPageNumViewMark(@NonNull String pageNumViewMark) {
        this.pageNumViewMark = pageNumViewMark;
        return this;
    }

    @NonNull
    public final BannerLayout setPageNumViewMark(@StringRes int pageNumViewMark) {
        this.pageNumViewMark = this.getContext().getString(pageNumViewMark);
        return this;
    }

    @NonNull
    public final BannerLayout setOnBannerClickListener(@NonNull OnBannerClickListener onBannerClickListener) {
        this.onBannerClickListener = onBannerClickListener;
        return this;
    }

    @NonNull
    public final BannerLayout setImageLoaderManager(@NonNull ImageDisplayManager loaderManage) {
        this.imageLoaderManage = loaderManage;
        return this;
    }

    private BannerLayout initBannerMethod() {
        this.clearHandler();
        this.removeAllViews();
        this.preEnablePosition = 0;
        this.adapter = new BannerAdapter(this.imageList, this.imageLoaderManage, this.errorDrawable, this.placeholderDrawable, this.isGuide);
        this.adapter.setImageClickListener(this);
        this.viewPager = new BannerViewPager(this.getContext());
        this.viewPager.setDuration(this.mDuration);
        this.viewPager.setViewTouchMode(this.viePagerTouchMode);

        this.viewPager.addOnPageChangeListener(this);
        this.viewPager.setAdapter(this.adapter);

        this.progressBuilder.setDuration(this.mDuration);

        if (this.isVertical) {
            this.bannerTransformer = new VerticalTransformer();
            if (this.viewPager != null) {
                this.viewPager.setVertical(true);
            }
        }

        if (this.viewPager != null) {
            this.viewPager.setPageTransformer(true, this.bannerTransformer);
        }

        this.addView(this.viewPager);
        int currentItem = this.isGuide ? 0 : Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % this.getDotsSize();
        if (this.viewPager != null) {
            this.viewPager.setCurrentItem(currentItem);
        }

        this.bannerHandlerUtils = new BannerHandlerUtils(this, currentItem);
        this.bannerHandlerUtils.setDelayTime(this.delayTime);

        if (this.pageView != null) {
            this.pageView.setText(TextUtils.concat(String.valueOf(1), this.pageNumViewMark, String.valueOf(this.imageList.size())));
        }

        if (this.pageView != null) {
            this.addView(this.pageView, this.pageView.initPageView(this));
        }

        if (this.bannerTipLayout != null) {
            this.bannerTipLayout.removeAllViews();
        }

        if (this.isVisibleDots) {
            if (this.bannerTipLayout != null) {
                this.bannerTipLayout.setDots(this);
            }
        }

        if (this.isVisibleProgresses) {
            if (this.bannerTipLayout != null) {
                this.bannerTipLayout.setProgresses(this);
            }
        }

        if (this.isVisibleTitle) {
            if (this.bannerTipLayout != null) {
                this.bannerTipLayout.setTitle(this);
            }

            if (this.bannerTipLayout != null) {
                if (this.imageList != null && !this.imageList.isEmpty()) {
                    this.bannerTipLayout.setTitle(this.imageList.get(0).getBannerTitle());
                }

            }
        }

        if (this.bannerTipLayout != null) {
            this.addView(bannerTipLayout, bannerTipLayout.setBannerTips(this));
        }

        return this;
    }

    public int dotsCount() {
        return this.getDotsSize();
    }

    @NonNull
    public Drawable dotsSelector() {
        Drawable drawable;
        if (this.dotsSelector == 0) {
            drawable = BannerSelectorUtils.getDrawableSelector(this.getContext(), this.enabledRadius, this.enabledColor, this.normalRadius, this.normalColor);
        } else {
            drawable = ContextCompat.getDrawable(this.getContext(), this.dotsSelector);
            if (drawable == null) {
                drawable = BannerSelectorUtils.getDrawableSelector(this.getContext(), this.enabledRadius, this.enabledColor, this.normalRadius, this.normalColor);
            }
        }

        return drawable;
    }

    public float dotsHeight() {
        return this.dotsHeight;
    }

    public float dotsWidth() {
        return this.dotsWidth;
    }

    public float dotsLeftMargin() {
        return this.dotsLeftMargin;
    }

    @Override
    public float dotsTopMargin() {
        return this.dotsTopMargin;
    }

    public float dotsRightMargin() {
        return this.dotsRightMargin;
    }

    @Override
    public float dotsBottomMargin() {
        return this.dotsBottomMargin;
    }

    @Override
    public int[] dotsSite() {
        return this.dotsSite;
    }

    @Override
    public int progressCount() {
        return this.imageList.size();
    }

    @Override
    public float progressLeftMargin() {
        return this.progressLeftMargin;
    }

    @Override
    public float progressTopMargin() {
        return this.progressTopMargin;
    }

    @Override
    public float progressRightMargin() {
        return this.progressRightMargin;
    }

    @Override
    public float progressBottomMargin() {
        return this.progressBottomMargin;
    }

    @Override
    public int[] progressSite() {
        return this.progressSite;
    }

    @Override
    public ProgressDrawable.Builder progressBuilder() {
        return this.progressBuilder;
    }

    public int titleColor() {
        return this.titleColor;
    }

    public float titleSize() {
        return this.titleSize;
    }

    public float titleLeftMargin() {
        return this.titleLeftMargin;
    }

    @Override
    public float titleTopMargin() {
        return this.titleTopMargin;
    }

    public float titleRightMargin() {
        return this.titleRightMargin;
    }

    @Override
    public float titleBottomMargin() {
        return this.titleBottomMargin;
    }

    public float titleWidth() {
        return this.titleWidth;
    }

    public float titleHeight() {
        return this.titleHeight;
    }

    @Override
    public int titleBackgroundColor() {
        return this.titleBackgroundColor;
    }

    public int[] titleSite() {
        return this.titleSite;
    }

    public int tipsSite() {
        return this.tipsSite;
    }

    public int tipsWidth() {
        return this.tipsLayoutWidth;
    }

    public int tipsHeight() {
        return this.tipsLayoutHeight;
    }

    public int tipsLayoutBackgroundColor() {
        return this.tipsBackgroundColor;
    }

    public boolean showBackgroundColor() {
        return this.isTipsBackground;
    }

    public float pageNumViewTopMargin() {
        return this.pageNumViewTopMargin;
    }

    public float pageNumViewRightMargin() {
        return this.pageNumViewRightMargin;
    }

    public float pageNumViewBottomMargin() {
        return this.pageNumViewBottomMargin;
    }

    public float pageNumViewLeftMargin() {
        return this.pageNumViewLeftMargin;
    }

    public int pageNumViewSite() {
        return this.pageNumViewSite;
    }

    public int pageNumViewTextColor() {
        return this.pageNumViewTextColor;
    }

    public float pageNumViewTextSize() {
        return this.pageNumViewTextSize;
    }

    public float pageNumViewPaddingTop() {
        return this.pageNumViewPaddingTop;
    }

    public float pageNumViewPaddingLeft() {
        return this.pageNumViewPaddingLeft;
    }

    public float pageNumViewPaddingBottom() {
        return this.pageNumViewPaddingBottom;
    }

    public float pageNumViewPaddingRight() {
        return this.pageNumViewPaddingRight;
    }

    public float pageNumViewRadius() {
        return this.pageNumViewRadius;
    }

    public int pageNumViewBackgroundColor() {
        return this.pageNumViewBackgroundColor;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        clearBanner();
    }

    /**
     * 释放资源
     *
     * @return
     */
    @NonNull
    public final BannerLayout clearBanner() {
        return this.clearViewPager()
                .clearHandler()
                .clearBannerTipLayout()
                .clearTransformerList()
                .clearPageView();
    }

    @NonNull
    public final BannerLayout clearViewPager() {
        if (this.viewPager != null) {
            this.viewPager.removeAllViews();
            this.removeView(this.viewPager);
            this.viewPager = null;
        }

        return this;
    }

    @NonNull
    public final BannerLayout clearTransformerList() {
        if (this.transformerList != null) {
            this.transformerList.clear();
            this.transformerList = null;
        }

        return this;
    }

    @NonNull
    public final BannerLayout clearHandler() {
        if (this.bannerHandlerUtils != null) {
            this.bannerHandlerUtils.removeCallbacksAndMessages(null);
            this.bannerHandlerUtils = null;
        }

        return this;
    }

    @NonNull
    public final BannerLayout clearBannerTipLayout() {
        if (this.bannerTipLayout != null) {
            this.bannerTipLayout.removeAllViews();
            this.removeView(this.bannerTipLayout);
            this.bannerTipLayout = null;
        }

        return this;
    }

    @NonNull
    public BannerLayout clearPageView() {
        if (this.pageView != null) {
            this.removeView(this.pageView);
            this.pageView = null;
        }

        return this;
    }

    @Nullable
    public List getImageList() {
        return this.imageList;
    }

    private int getDotsSize() {
        return this.imageList.size();
    }

    public final int getDuration() {
        return this.viewPager != null ? this.viewPager.getDuration() : 0;
    }

    public final int getBannerStatus() {
        return this.bannerHandlerUtils != null ? this.bannerHandlerUtils.getStatus() : 0;
    }

    public BannerLayout(@NonNull Context context) {
        this(context, null);
    }

    public BannerLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }
}
