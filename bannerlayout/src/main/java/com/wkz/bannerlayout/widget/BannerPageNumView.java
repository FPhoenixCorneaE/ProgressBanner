package com.wkz.bannerlayout.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;

import com.wkz.bannerlayout.annotation.TipsPageNumSiteMode;
import com.wkz.bannerlayout.utils.BannerSelectorUtils;
import com.wkz.bannerlayout.utils.DisplayUtils;

public class BannerPageNumView extends AppCompatTextView {

    @NonNull
    public final FrameLayout.LayoutParams initPageView(@NonNull BannerPageNumView.PageNumViewInterface pageNumViewInterface) {
        this.setGravity(Gravity.CENTER);
        this.setTextColor(pageNumViewInterface.pageNumViewTextColor());
        this.setTextSize(pageNumViewInterface.pageNumViewTextSize());
        this.setBackground(
                BannerSelectorUtils.getShape(
                        this.getContext(),
                        pageNumViewInterface.pageNumViewRadius(),
                        pageNumViewInterface.pageNumViewBackgroundColor()
                )
        );
        this.setPadding(
                DisplayUtils.dp2px(getContext(), pageNumViewInterface.pageNumViewPaddingLeft()),
                DisplayUtils.dp2px(getContext(), pageNumViewInterface.pageNumViewPaddingTop()),
                DisplayUtils.dp2px(getContext(), pageNumViewInterface.pageNumViewPaddingRight()),
                DisplayUtils.dp2px(getContext(), pageNumViewInterface.pageNumViewPaddingBottom())
        );

        FrameLayout.LayoutParams pageParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        );
        @TipsPageNumSiteMode
        int pageNumViewSiteMode = pageNumViewInterface.pageNumViewSite();
        switch (pageNumViewSiteMode) {
            case TipsPageNumSiteMode.TOP_LEFT:
            default:
                pageParams.gravity = Gravity.START | Gravity.TOP;
                break;
            case TipsPageNumSiteMode.TOP_RIGHT:
                pageParams.gravity = Gravity.END | Gravity.TOP;
                break;
            case TipsPageNumSiteMode.BOTTOM_LEFT:
                pageParams.gravity = Gravity.START | Gravity.BOTTOM;
                break;
            case TipsPageNumSiteMode.BOTTOM_RIGHT:
                pageParams.gravity = Gravity.END | Gravity.BOTTOM;
                break;
            case TipsPageNumSiteMode.CENTER_LEFT:
                pageParams.gravity = Gravity.START | Gravity.CENTER;
                break;
            case TipsPageNumSiteMode.CENTER_RIGHT:
                pageParams.gravity = Gravity.END | Gravity.CENTER;
                break;
            case TipsPageNumSiteMode.TOP_CENTER:
                pageParams.gravity = Gravity.TOP | Gravity.CENTER;
                break;
            case TipsPageNumSiteMode.BOTTOM_CENTER:
                pageParams.gravity = Gravity.BOTTOM | Gravity.CENTER;
        }

        pageParams.setMargins(
                DisplayUtils.dp2px(getContext(), pageNumViewInterface.pageNumViewLeftMargin()),
                DisplayUtils.dp2px(getContext(), pageNumViewInterface.pageNumViewTopMargin()),
                DisplayUtils.dp2px(getContext(), pageNumViewInterface.pageNumViewRightMargin()),
                DisplayUtils.dp2px(getContext(), pageNumViewInterface.pageNumViewBottomMargin())
        );
        return pageParams;
    }

    public BannerPageNumView(@NonNull Context context) {
        this(context, null);
    }

    public BannerPageNumView(@NonNull Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerPageNumView(@NonNull Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public interface PageNumViewInterface {

        float pageNumViewTopMargin();

        float pageNumViewRightMargin();

        float pageNumViewBottomMargin();

        float pageNumViewLeftMargin();

        int pageNumViewTextColor();

        float pageNumViewTextSize();

        float pageNumViewPaddingTop();

        float pageNumViewPaddingLeft();

        float pageNumViewPaddingBottom();

        float pageNumViewPaddingRight();

        float pageNumViewRadius();

        int pageNumViewBackgroundColor();

        int pageNumViewSite();

    }
}