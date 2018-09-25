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
    private boolean viePagerTouchMode;
    private int mDuration;
    private int errorImageView;
    private int placeImageView;
    private boolean isTipsBackground;
    private boolean isVisibleDots;
    private int dotsWidth;
    private int dotsHeight;
    private int dotsSelector;
    private int dotsLeftMargin;
    private int dotsRightMargin;
    private int dotsSite;
    private float enabledRadius;
    private float normalRadius;
    private int enabledColor;
    private int normalColor;
    private boolean isVisibleProgresses;
    private float progressLeftMargin;
    private float progressRightMargin;
    private int progressSite;
    private ProgressDrawable.Builder progressBuilder;
    private boolean isVisibleTitle;
    private float titleSize;
    private int titleColor;
    private int titleLeftMargin;
    private int titleRightMargin;
    private int titleWidth;
    private int titleHeight;
    private int titleSite;
    private int tipsLayoutHeight;
    private int tipsLayoutWidth;
    private int tipsBackgroundColor;
    private int tipsSite;
    private float pageNumViewRadius;
    private int pageNumViewPaddingTop;
    private int pageNumViewPaddingLeft;
    private int pageNumViewPaddingBottom;
    private int pageNumViewPaddingRight;
    private int pageNumViewTopMargin;
    private int pageNumViewRightMargin;
    private int pageNumViewBottomMargin;
    private int pageNumViewLeftMargin;
    private int pageNumViewSite;
    private int pageNumViewTextColor;
    private int pageNumViewBackgroundColor;
    private float pageNumViewTextSize;
    private String pageNumViewMark;
    public static final int MATCH_PARENT = FrameLayout.LayoutParams.MATCH_PARENT;
    public static final int WRAP_CONTENT = FrameLayout.LayoutParams.WRAP_CONTENT;

    private void init() {
        this.isGuide = BannerDefaults.IS_GUIDE;
        this.isVertical = BannerDefaults.IS_VERTICAL;
        this.isStartRotation = BannerDefaults.IS_START_ROTATION;
        this.delayTime = BannerDefaults.DELAY_TIME;
        this.viePagerTouchMode = BannerDefaults.VIEW_PAGER_TOUCH_MODE;
        this.mDuration = BannerDefaults.BANNER_DURATION;
        this.errorImageView = BannerDefaults.GLIDE_ERROR_IMAGE;
        this.placeImageView = BannerDefaults.GLIDE_PLACE_IMAGE;

        this.isTipsBackground = BannerDefaults.IS_TIPS_LAYOUT_BACKGROUND;
        this.tipsBackgroundColor = BannerDefaults.TIPS_LAYOUT_BACKGROUND;
        this.tipsLayoutWidth = BannerDefaults.TIPS_LAYOUT_WIDTH;
        this.tipsLayoutHeight = BannerDefaults.TIPS_LAYOUT_HEIGHT;
        this.tipsSite = BannerDefaults.TIPS_SITE;

        this.isVisibleDots = BannerDefaults.IS_VISIBLE_DOTS;
        this.dotsLeftMargin = BannerDefaults.DOTS_LEFT_MARGIN;
        this.dotsRightMargin = BannerDefaults.DOTS_RIGHT_MARGIN;
        this.dotsWidth = BannerDefaults.DOTS_WIDth;
        this.dotsHeight = BannerDefaults.DOTS_HEIGHT;
        this.dotsSelector = BannerDefaults.DOTS_SELECTOR;
        this.enabledRadius = BannerDefaults.DOTS_ENABLED_RADIUS;
        this.enabledColor = BannerDefaults.DOTS_ENABLED_COLOR;
        this.normalRadius = BannerDefaults.DOTS_NORMAL_RADIUS;
        this.normalColor = BannerDefaults.DOTS_NORMAL_COLOR;
        this.dotsSite = BannerDefaults.DOTS_SITE;


        this.isVisibleTitle = BannerDefaults.IS_VISIBLE_TITLE;
        this.titleColor = BannerDefaults.TITLE_COLOR;
        this.titleSize = BannerDefaults.TITLE_SIZE;
        this.titleRightMargin = BannerDefaults.TITLE_RIGHT_MARGIN;
        this.titleLeftMargin = BannerDefaults.TITLE_LEFT_MARGIN;
        this.titleWidth = BannerDefaults.TITLE_WIDTH;
        this.titleHeight = BannerDefaults.TITLE_HEIGHT;
        this.titleSite = BannerDefaults.TITLE_SITE;

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

        this.isVisibleProgresses = BannerDefaults.IS_VISIBLE_PROGRESSES;
        this.progressLeftMargin = BannerDefaults.PROGRESSES_LEFT_MARGIN;
        this.progressRightMargin = BannerDefaults.PROGRESSES_RIGHT_MARGIN;
        this.progressSite = BannerDefaults.PROGRESSES_SITE;
        this.progressBuilder = new ProgressDrawable
                .Builder(getContext())
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
    public final BannerLayout setDelayTime(int time) {
        this.delayTime = (long) time;
        return this;
    }

    @NonNull
    public final BannerLayout startRotation(boolean isStartRotation) {
        this.isStartRotation = isStartRotation;
        if (this.bannerHandlerUtils != null) {
            this.bannerHandlerUtils.removeCallbacksAndMessages(null);
        }

        if (isStartRotation) {
            if (this.bannerHandlerUtils != null) {
                this.bannerHandlerUtils.setDelayTime(this.delayTime);
                this.bannerHandlerUtils.sendEmptyMessageDelayed(BannerHandlerUtils.MSG_UPDATE, this.delayTime);
            }
            if (this.isVisibleProgresses) {
                if (this.bannerTipLayout != null) {
                    bannerTipLayout.changeProgressesPosition(preEnablePosition, preEnablePosition);
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
    public final BannerLayout setErrorImageView(@DrawableRes int errorImageView) {
        this.errorImageView = errorImageView;
        return this;
    }

    @NonNull
    public final BannerLayout setPlaceImageView(@DrawableRes int placeImageView) {
        this.placeImageView = placeImageView;
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
    public final BannerLayout setTipsDotsSelector(@DrawableRes int dotsSelector) {
        this.dotsSelector = dotsSelector;
        return this;
    }

    @NonNull
    public final BannerLayout setTipsWidthAndHeight(int width, int height) {
        this.tipsLayoutHeight = height;
        this.tipsLayoutWidth = width;
        return this;
    }

    @NonNull
    public final BannerLayout setTipsSite(int tipsSite) {
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
    public final BannerLayout setTitleMargin(int leftMargin, int rightMargin) {
        this.titleLeftMargin = leftMargin;
        this.titleRightMargin = rightMargin;
        return this;
    }

    @NonNull
    public final BannerLayout setTitleMargin(int margin) {
        this.titleLeftMargin = margin;
        this.titleRightMargin = margin;
        return this;
    }

    @NonNull
    public final BannerLayout setTitleSite(int titleSite) {
        this.titleSite = titleSite;
        return this;
    }

    @NonNull
    public final BannerLayout setDotsWidthAndHeight(int width, int height) {
        this.dotsWidth = width;
        this.dotsHeight = height;
        return this;
    }

    @NonNull
    public final BannerLayout setDotsSite(int dotsSite) {
        this.dotsSite = dotsSite;
        return this;
    }

    @NonNull
    public final BannerLayout setDotsMargin(int leftMargin, int rightMargin) {
        this.dotsLeftMargin = leftMargin;
        this.dotsRightMargin = rightMargin;
        return this;
    }

    @NonNull
    public final BannerLayout setDotsMargin(int margin) {
        this.dotsLeftMargin = margin;
        this.dotsRightMargin = margin;
        return this;
    }

    @NonNull
    public final BannerLayout setNormalRadius(float normalRadius) {
        this.normalRadius = normalRadius;
        return this;
    }

    @NonNull
    public final BannerLayout setNormalColor(@ColorInt int normalColor) {
        this.normalColor = normalColor;
        return this;
    }

    @NonNull
    public final BannerLayout setEnabledColor(@ColorInt int enabledColor) {
        this.enabledColor = enabledColor;
        return this;
    }

    @NonNull
    public final BannerLayout setEnabledRadius(float enabledRadius) {
        this.enabledRadius = enabledRadius;
        return this;
    }

    @NonNull
    public final BannerLayout setProgressesSite(int progressSite) {
        this.progressSite = progressSite;
        return this;
    }

    @NonNull
    public final BannerLayout setProgressesMargin(float leftMargin, float rightMargin) {
        this.progressLeftMargin = leftMargin;
        this.progressRightMargin = rightMargin;
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
        return this;
    }

    @NonNull
    public final BannerLayout setBannerTransformer(int type) {
        this.setBannerTransformer(TransformerUtils.getTransformer(type));
        return this;
    }

    @NonNull
    public final BannerLayout setBannerTransformer(@NonNull BannerTransformer bannerTransformer) {
        if (this.isVertical) {
            //If it is a vertical slide can not set the animation
            //竖直滚动使用动画实现,因此禁止使用动画
            throw new RuntimeException("If it is a vertical slide can not set the animation");
        } else {
            this.bannerTransformer = bannerTransformer;
            if (this.viewPager != null) {
                this.viewPager.setPageTransformer(true, bannerTransformer);
            }

            return this;
        }
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
    public final BannerLayout setPageNumViewPadding(int top, int bottom, int left, int right) {
        this.pageNumViewPaddingTop = top;
        this.pageNumViewPaddingBottom = bottom;
        this.pageNumViewPaddingLeft = left;
        this.pageNumViewPaddingRight = right;
        return this;
    }

    @NonNull
    public final BannerLayout setPageNumViewPadding(int padding) {
        this.pageNumViewPaddingTop = padding;
        this.pageNumViewPaddingBottom = padding;
        this.pageNumViewPaddingLeft = padding;
        this.pageNumViewPaddingRight = padding;
        return this;
    }

    @NonNull
    public final BannerLayout setPageNumViewMargin(int top, int bottom, int left, int right) {
        this.pageNumViewTopMargin = top;
        this.pageNumViewBottomMargin = bottom;
        this.pageNumViewLeftMargin = left;
        this.pageNumViewRightMargin = right;
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
    public final BannerLayout setPageNumViewSite(int pageNumViewSite) {
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
        this.adapter = new BannerAdapter(this.imageList, this.imageLoaderManage, this.errorImageView, this.placeImageView, this.isGuide);
        this.adapter.setImageClickListener(this);
        this.viewPager = new BannerViewPager(this.getContext());
        this.viewPager.setDuration(this.mDuration);
        this.viewPager.setViewTouchMode(this.viePagerTouchMode);

        this.viewPager.addOnPageChangeListener(this);
        this.viewPager.setAdapter(this.adapter);

        if (this.isVertical) {
            if (this.viewPager != null) {
                this.viewPager.setVertical(true);
            }
        } else {
            if (this.viewPager != null) {
                this.viewPager.setPageTransformer(true, this.bannerTransformer);
            }
        }

        this.addView(this.viewPager);
        int currentItem = this.isGuide ? 0 : Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % this.getDotsSize();
        if (this.viewPager != null) {
            this.viewPager.setCurrentItem(currentItem);
        }

        this.bannerHandlerUtils = new BannerHandlerUtils(this, currentItem);
        this.bannerHandlerUtils.setDelayTime(this.delayTime);

        if (this.pageView != null) {
            this.pageView.setText(TextUtils.concat(String.valueOf(1), this.pageNumViewMark, String.valueOf(this.getDotsSize())));
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
            drawable = BannerSelectorUtils.getDrawableSelector(this.enabledRadius, this.enabledColor, this.normalRadius, this.normalColor);
        } else {
            drawable = ContextCompat.getDrawable(this.getContext(), this.dotsSelector);
            if (drawable == null) {
                drawable = BannerSelectorUtils.getDrawableSelector(this.enabledRadius, this.enabledColor, this.normalRadius, this.normalColor);
            }
        }

        return drawable;
    }

    public int dotsHeight() {
        return this.dotsHeight;
    }

    public int dotsWidth() {
        return this.dotsWidth;
    }

    public int dotsLeftMargin() {
        return this.dotsLeftMargin;
    }

    public int dotsRightMargin() {
        return this.dotsRightMargin;
    }

    public int dotsSite() {
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
    public float progressRightMargin() {
        return this.progressRightMargin;
    }

    @Override
    public int progressSite() {
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

    public int titleLeftMargin() {
        return this.titleLeftMargin;
    }

    public int titleRightMargin() {
        return this.titleRightMargin;
    }

    public int titleWidth() {
        return this.titleWidth;
    }

    public int titleHeight() {
        return this.titleHeight;
    }

    public int titleSite() {
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

    public int pageNumViewTopMargin() {
        return this.pageNumViewTopMargin;
    }

    public int pageNumViewRightMargin() {
        return this.pageNumViewRightMargin;
    }

    public int pageNumViewBottomMargin() {
        return this.pageNumViewBottomMargin;
    }

    public int pageNumViewLeftMargin() {
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

    public int pageNumViewPaddingTop() {
        return this.pageNumViewPaddingTop;
    }

    public int pageNumViewPaddingLeft() {
        return this.pageNumViewPaddingLeft;
    }

    public int pageNumViewPaddingBottom() {
        return this.pageNumViewPaddingBottom;
    }

    public int pageNumViewPaddingRight() {
        return this.pageNumViewPaddingRight;
    }

    public float pageNumViewRadius() {
        return this.pageNumViewRadius;
    }

    public int pageNumViewBackgroundColor() {
        return this.pageNumViewBackgroundColor;
    }

    @NonNull
    public final BannerLayout clearBanner() {
        this.clearViewPager();
        this.clearHandler();
        this.clearBannerTipLayout();
        this.clearTransformerList();
        this.clearPageView();
        return this;
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
        super(context);
        this.init();
    }

    public BannerLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }
}
