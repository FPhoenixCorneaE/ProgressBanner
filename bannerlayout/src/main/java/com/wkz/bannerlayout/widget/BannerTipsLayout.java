package com.wkz.bannerlayout.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
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

import com.wkz.bannerlayout.annotation.TipsLayoutSiteMode;
import com.wkz.bannerlayout.utils.DisplayUtils;

public final class BannerTipsLayout extends RelativeLayout {

    private Context mContext;
    private TextView textView;
    private LinearLayout linearLayout;
    private LinearLayout progressesContainer;

    /**
     * Initialize the dots
     */
    public void setDots(@NonNull BannerTipsLayout.DotsInterface dotsInterface) {
        this.linearLayout.removeAllViews();

        for (int i = 0; i < dotsInterface.dotsCount(); ++i) {
            View view = new View(mContext);
            view.setBackground(dotsInterface.dotsSelector());
            view.setEnabled(i == 0);

            MarginLayoutParams marginLayoutParams = new MarginLayoutParams(
                    DisplayUtils.dp2px(mContext, dotsInterface.dotsWidth()),
                    DisplayUtils.dp2px(mContext, dotsInterface.dotsHeight()));
            MarginLayoutParamsCompat.setMarginStart(marginLayoutParams,
                    DisplayUtils.dp2px(mContext, dotsInterface.dotsLeftMargin()));
            MarginLayoutParamsCompat.setMarginEnd(marginLayoutParams,
                    DisplayUtils.dp2px(mContext, dotsInterface.dotsRightMargin()));
            this.linearLayout.addView(view, marginLayoutParams);
        }

        this.linearLayout.measure(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);

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
            View view = new View(mContext);
            view.setBackground(progressInterface.progressBuilder().build());

            MarginLayoutParams marginLayoutParams = new MarginLayoutParams(
                    (int) progressInterface.progressBuilder().getWidth(),
                    (int) progressInterface.progressBuilder().getHeight());
            MarginLayoutParamsCompat.setMarginStart(marginLayoutParams,
                    DisplayUtils.dp2px(mContext, progressInterface.progressLeftMargin()));
            MarginLayoutParamsCompat.setMarginEnd(marginLayoutParams,
                    DisplayUtils.dp2px(mContext, progressInterface.progressRightMargin()));
            this.progressesContainer.addView(view, marginLayoutParams);
        }

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        params.addRule(progressInterface.progressSite());
        this.addView(this.progressesContainer, params);
    }

    @NonNull
    public FrameLayout.LayoutParams setBannerTips(@NonNull BannerTipsLayout.TipsInterface tipsInterface) {
        FrameLayout.LayoutParams tipsParams = new FrameLayout.LayoutParams(tipsInterface.tipsWidth(), tipsInterface.tipsHeight());
        @TipsLayoutSiteMode
        int tipsSiteMode = tipsInterface.tipsSite();
        switch (tipsSiteMode) {
            case TipsLayoutSiteMode.TOP:
                tipsParams.gravity = Gravity.TOP;
            case TipsLayoutSiteMode.RIGHT:
            default:
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
        this.mContext = context;
        this.textView = new TextView(mContext);
        this.linearLayout = new LinearLayout(mContext);
        this.progressesContainer = new LinearLayout(mContext);
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

        float progressLeftMargin();

        float progressRightMargin();

        int progressSite();

        ProgressDrawable.Builder progressBuilder();
    }
}
