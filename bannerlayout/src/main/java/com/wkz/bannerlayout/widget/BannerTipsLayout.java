package com.wkz.bannerlayout.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wkz.bannerlayout.annotation.TipsSiteMode;

/**
 * by y on 2016/10/25
 */
public final class BannerTipsLayout extends RelativeLayout {

    private TextView textView;
    private LinearLayout linearLayout;
    private LinearLayout progressesContainer;

    /**
     * Initialize the dots
     */
    public void setDots(@NonNull BannerTipsLayout.DotsInterface dotsInterface) {
        this.linearLayout.removeAllViews();
        int i = 0;

        for (int var3 = dotsInterface.dotsCount(); i < var3; ++i) {
            View view = new View(this.getContext());
            view.setBackground(dotsInterface.dotsSelector());
            view.setEnabled(i == 0);
            LayoutParams params = new LayoutParams(dotsInterface.dotsWidth(), dotsInterface.dotsHeight());
            view.setLayoutParams(params);
            params.leftMargin = dotsInterface.dotsLeftMargin();
            params.rightMargin = dotsInterface.dotsRightMargin();
            this.linearLayout.addView(view);
        }

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        params.addRule(dotsInterface.dotsSite());
        this.addView(this.linearLayout, params);
    }

    /**
     * Initialize the Progresses
     */
    public void setProgresses(@NonNull BannerTipsLayout.ProgressInterface progressInterface) {
        this.progressesContainer.removeAllViews();

        for (int i = 0; i < progressInterface.progressCount(); ++i) {
            View view = new View(this.getContext());
            view.setBackground(progressInterface.progressBuilder().build());
            LayoutParams params = new LayoutParams((int) progressInterface.progressBuilder().getWidth(), (int) progressInterface.progressBuilder().getHeight());
            view.setLayoutParams(params);
            params.leftMargin = progressInterface.progressLeftMargin();
            params.rightMargin = progressInterface.progressRightMargin();
            this.progressesContainer.addView(view);
        }

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        params.addRule(progressInterface.progressSite());
        this.addView(this.progressesContainer, params);
    }

    @NonNull
    public FrameLayout.LayoutParams setBannerTips(@NonNull BannerTipsLayout.TipsInterface tipsInterface) {
        FrameLayout.LayoutParams tipsParams = new FrameLayout.LayoutParams(tipsInterface.tipsWidth(), tipsInterface.tipsHeight());
        @TipsSiteMode
        int tipsSiteMode = tipsInterface.tipsSite();
        switch (tipsSiteMode) {
            case BannerLayout.TOP:
                tipsParams.gravity = Gravity.TOP;
            case BannerLayout.RIGHT:
            default:
                tipsParams.gravity = Gravity.END;
                break;
            case BannerLayout.BOTTOM:
                tipsParams.gravity = Gravity.BOTTOM;
                break;
            case BannerLayout.CENTER:
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
        View childAt = this.linearLayout.getChildAt(position);
        View newChildAt = this.linearLayout.getChildAt(newPosition);
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
        View childAt = this.progressesContainer.getChildAt(position);
        View newChildAt = this.progressesContainer.getChildAt(newPosition);
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
        this.textView.setGravity(Gravity.CENTER_VERTICAL);
        this.textView.setTextColor(titleInterface.titleColor());
        this.textView.setTextSize(titleInterface.titleSize());
        this.textView.setSingleLine(true);
        this.textView.setEllipsize(TextUtils.TruncateAt.END);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(titleInterface.titleWidth(), titleInterface.titleHeight());
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        params.leftMargin = titleInterface.titleLeftMargin();
        params.rightMargin = titleInterface.titleRightMargin();
        params.addRule(titleInterface.titleSite());
        this.addView(this.textView, params);
    }

    public void setTitle(@NonNull String title) {
        this.clearText();
        if (!TextUtils.isEmpty(title)) {
            this.textView.setText(title);
        }

    }

    private void clearText() {
        this.textView.setText("");
    }

    public BannerTipsLayout(@NonNull Context context) {
        super(context);
        this.textView = new TextView(this.getContext());
        this.linearLayout = new LinearLayout(this.getContext());
        this.progressesContainer = new LinearLayout(this.getContext());
    }

    public BannerTipsLayout(@NonNull Context context, @NonNull AttributeSet attrs) {
        super(context, attrs);
        this.textView = new TextView(this.getContext());
        this.linearLayout = new LinearLayout(this.getContext());
        this.progressesContainer = new LinearLayout(this.getContext());
    }

    public BannerTipsLayout(@NonNull Context context, @NonNull AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.textView = new TextView(this.getContext());
        this.linearLayout = new LinearLayout(this.getContext());
        this.progressesContainer = new LinearLayout(this.getContext());
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

        int titleLeftMargin();

        int titleRightMargin();

        int titleWidth();

        int titleHeight();

        int titleSite();
    }

    public interface DotsInterface {
        int dotsCount();

        @NonNull
        Drawable dotsSelector();

        int dotsHeight();

        int dotsWidth();

        int dotsLeftMargin();

        int dotsRightMargin();

        int dotsSite();
    }

    public interface ProgressInterface {
        int progressCount();

        int progressLeftMargin();

        int progressRightMargin();

        int progressSite();

        ProgressDrawable.Builder progressBuilder();
    }
}
