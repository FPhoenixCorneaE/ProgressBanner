package com.wkz.bannerlayout.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wkz.bannerlayout.annotation.TipsDotsSiteMode;
import com.wkz.bannerlayout.annotation.TipsLayoutSiteMode;
import com.wkz.bannerlayout.annotation.TipsProgressesSiteMode;
import com.wkz.bannerlayout.annotation.TipsTitleSiteMode;
import com.wkz.bannerlayout.utils.DisplayUtils;

public final class BannerTipsLayout extends RelativeLayout {

    private Context mContext;
    private TextView mTextView;
    private LinearLayout mDotsContainer;
    private LinearLayout mProgressesContainer;

    /**
     * Initialize the dots
     */
    public void setDots(@NonNull BannerTipsLayout.DotsInterface dotsInterface) {
        this.mDotsContainer.removeAllViews();

        for (int i = 0; i < dotsInterface.dotsCount(); ++i) {
            View view = new View(mContext);
            view.setBackground(dotsInterface.dotsSelector());
            view.setEnabled(i == 0);

            MarginLayoutParams marginLayoutParams = new MarginLayoutParams(
                    DisplayUtils.dp2px(mContext, dotsInterface.dotsWidth()),
                    DisplayUtils.dp2px(mContext, dotsInterface.dotsHeight()));
            MarginLayoutParamsCompat.setMarginStart(marginLayoutParams,
                    DisplayUtils.dp2px(mContext, dotsInterface.dotsLeftMargin()));
            marginLayoutParams.topMargin = DisplayUtils.dp2px(mContext, dotsInterface.dotsTopMargin());
            MarginLayoutParamsCompat.setMarginEnd(marginLayoutParams,
                    DisplayUtils.dp2px(mContext, dotsInterface.dotsRightMargin()));
            marginLayoutParams.bottomMargin = DisplayUtils.dp2px(mContext, dotsInterface.dotsBottomMargin());
            this.mDotsContainer.addView(view, marginLayoutParams);
        }

        this.mDotsContainer.measure(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < dotsInterface.dotsSite().length; i++) {
            params.addRule(dotsInterface.dotsSite()[i]);
        }
        this.addView(this.mDotsContainer, params);
    }

    /**
     * Initialize the Progresses
     */
    public void setProgresses(@NonNull BannerTipsLayout.ProgressInterface progressInterface) {
        this.mProgressesContainer.removeAllViews();

        for (int i = 0; i < progressInterface.progressCount(); ++i) {
            View view = new View(mContext);
            view.setBackground(progressInterface.progressBuilder().build());

            MarginLayoutParams marginLayoutParams = new MarginLayoutParams(
                    (int) progressInterface.progressBuilder().getWidth(),
                    (int) progressInterface.progressBuilder().getHeight());
            MarginLayoutParamsCompat.setMarginStart(marginLayoutParams,
                    DisplayUtils.dp2px(mContext, progressInterface.progressLeftMargin()));
            marginLayoutParams.topMargin = DisplayUtils.dp2px(mContext, progressInterface.progressTopMargin());
            MarginLayoutParamsCompat.setMarginEnd(marginLayoutParams,
                    DisplayUtils.dp2px(mContext, progressInterface.progressRightMargin()));
            marginLayoutParams.bottomMargin = DisplayUtils.dp2px(mContext, progressInterface.progressBottomMargin());
            this.mProgressesContainer.addView(view, marginLayoutParams);
        }

        this.mProgressesContainer.measure(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < progressInterface.progressSite().length; i++) {
            params.addRule(progressInterface.progressSite()[i]);
        }
        this.addView(this.mProgressesContainer, params);
    }

    @NonNull
    public FrameLayout.LayoutParams setBannerTips(@NonNull BannerTipsLayout.TipsInterface tipsInterface) {
        FrameLayout.LayoutParams tipsParams = new FrameLayout.LayoutParams(tipsInterface.tipsWidth(), tipsInterface.tipsHeight());
        @TipsLayoutSiteMode
        int tipsSiteMode = tipsInterface.tipsSite();
        switch (tipsSiteMode) {
            case TipsLayoutSiteMode.LEFT:
                tipsParams.gravity = Gravity.START;
                break;
            case TipsLayoutSiteMode.TOP:
                tipsParams.gravity = Gravity.TOP;
                break;
            case TipsLayoutSiteMode.RIGHT:
                tipsParams.gravity = Gravity.END;
                break;
            case TipsLayoutSiteMode.BOTTOM:
                tipsParams.gravity = Gravity.BOTTOM;
                break;
            case TipsLayoutSiteMode.CENTER:
                tipsParams.gravity = Gravity.CENTER;
        }

        if (tipsInterface.showBackgroundColor()) {
            this.setBackgroundColor(tipsInterface.tipsLayoutBackgroundColor());
        }

        return tipsParams;
    }

    /**
     * Update the dot position
     */
    public void changeDotsPosition(int position, int newPosition) {
        View childAt = this.mDotsContainer.getChildAt(position);
        View newChildAt = this.mDotsContainer.getChildAt(newPosition);
        if (childAt != null) {
            childAt.setEnabled(false);
        }

        if (newChildAt != null) {
            newChildAt.setEnabled(true);
        }

    }

    /**
     * Update the Progress position
     */
    public void changeProgressesPosition(int position, int newPosition) {
        View childAt = this.mProgressesContainer.getChildAt(position);
        View newChildAt = this.mProgressesContainer.getChildAt(newPosition);
        if (childAt != null) {
            if (childAt.getBackground() instanceof ProgressDrawable) {
                ((ProgressDrawable) childAt.getBackground()).end();
            }
        }

        if (newChildAt != null) {
            if (newChildAt.getBackground() instanceof ProgressDrawable) {
                ((ProgressDrawable) newChildAt.getBackground()).start();
            }
        }

    }

    /**
     * Update title, the default on the left
     */
    public final void setTitle(@NonNull BannerTipsLayout.TitleInterface titleInterface) {
        this.mTextView.setGravity(Gravity.CENTER_VERTICAL);
        this.mTextView.setTextColor(titleInterface.titleColor());
        this.mTextView.setTextSize(titleInterface.titleSize());
        this.mTextView.setSingleLine(true);
        this.mTextView.setEllipsize(TextUtils.TruncateAt.END);
        this.mTextView.setBackgroundColor(titleInterface.titleBackgroundColor());
        this.mTextView.setPadding(
                DisplayUtils.dp2px(mContext, titleInterface.titleLeftMargin()),
                DisplayUtils.dp2px(mContext, titleInterface.titleTopMargin()),
                DisplayUtils.dp2px(mContext, titleInterface.titleRightMargin()),
                DisplayUtils.dp2px(mContext, titleInterface.titleBottomMargin())
        );

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                titleInterface.titleWidth() == -1 ?
                        ViewGroup.LayoutParams.MATCH_PARENT :
                        DisplayUtils.dp2px(mContext, titleInterface.titleWidth()),
                titleInterface.titleHeight() == -2 ?
                        ViewGroup.LayoutParams.WRAP_CONTENT :
                        DisplayUtils.dp2px(mContext, titleInterface.titleHeight())
        );
        for (int i = 0; i < titleInterface.titleSite().length; i++) {
            params.addRule(titleInterface.titleSite()[i]);
        }

        this.addView(this.mTextView, params);
    }

    public void setTitle(String title) {
        this.clearText();
        if (!TextUtils.isEmpty(title)) {
            this.mTextView.setText(title);
        }
    }

    private void clearText() {
        this.mTextView.setText("");
    }

    public BannerTipsLayout(@NonNull Context context) {
        super(context);
        this.mContext = context;
        this.mTextView = new TextView(mContext);
        this.mDotsContainer = new LinearLayout(mContext);
        this.mProgressesContainer = new LinearLayout(mContext);
    }

    public interface TipsInterface {
        boolean showBackgroundColor();

        int tipsSite();

        int tipsWidth();

        int tipsHeight();

        int tipsLayoutBackgroundColor();
    }


    public interface TitleInterface {
        int titleColor();

        float titleSize();

        float titleLeftMargin();

        float titleTopMargin();

        float titleRightMargin();

        float titleBottomMargin();

        float titleWidth();

        float titleHeight();

        @ColorInt
        int titleBackgroundColor();

        @TipsTitleSiteMode
        int[] titleSite();
    }

    public interface DotsInterface {
        int dotsCount();

        Drawable dotsSelector();

        float dotsHeight();

        float dotsWidth();

        float dotsLeftMargin();

        float dotsTopMargin();

        float dotsRightMargin();

        float dotsBottomMargin();

        @TipsDotsSiteMode
        int[] dotsSite();
    }

    public interface ProgressInterface {
        int progressCount();

        float progressLeftMargin();

        float progressTopMargin();

        float progressRightMargin();

        float progressBottomMargin();

        @TipsProgressesSiteMode
        int[] progressSite();

        ProgressDrawable.Builder progressBuilder();
    }
}
