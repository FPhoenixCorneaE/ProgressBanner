package com.wkz.bannerlayout.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.wkz.bannerlayout.annotation.PageNumViewSiteMode;
import com.wkz.bannerlayout.utils.BannerSelectorUtils;
import com.wkz.bannerlayout.utils.DisplayUtils;

public class BannerPageNumView extends AppCompatTextView {
    @NonNull
    public final FrameLayout.LayoutParams initPageView(@NonNull BannerPageNumView.PageNumViewInterface pageNumViewInterface) {
        FrameLayout.LayoutParams pageParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        pageParams.setMargins(DisplayUtils.dp2px(getContext(), pageNumViewInterface.pageNumViewLeftMargin()),
                DisplayUtils.dp2px(getContext(), pageNumViewInterface.pageNumViewTopMargin()),
                DisplayUtils.dp2px(getContext(), pageNumViewInterface.pageNumViewRightMargin()),
                DisplayUtils.dp2px(getContext(), pageNumViewInterface.pageNumViewBottomMargin()));
        @PageNumViewSiteMode
        int pageNumViewSiteMode = pageNumViewInterface.pageNumViewSite();
        switch (pageNumViewSiteMode) {
            case PageNumViewSiteMode.PAGE_NUM_VIEW_TOP_LEFT:
            default:
                pageParams.gravity = Gravity.START | Gravity.TOP;
                break;
            case PageNumViewSiteMode.PAGE_NUM_VIEW_TOP_RIGHT:
                pageParams.gravity = Gravity.END | Gravity.TOP;
                break;
            case PageNumViewSiteMode.PAGE_NUM_VIEW_BOTTOM_LEFT:
                pageParams.gravity = Gravity.START | Gravity.BOTTOM;
                break;
            case PageNumViewSiteMode.PAGE_NUM_VIEW_BOTTOM_RIGHT:
                pageParams.gravity = Gravity.END | Gravity.BOTTOM;
                break;
            case PageNumViewSiteMode.PAGE_NUM_VIEW_CENTER_LEFT:
                pageParams.gravity = Gravity.START | Gravity.CENTER;
                break;
            case PageNumViewSiteMode.PAGE_NUM_VIEW_CENTER_RIGHT:
                pageParams.gravity = Gravity.END | Gravity.CENTER;
                break;
            case PageNumViewSiteMode.PAGE_NUM_VIEW_TOP_CENTER:
                pageParams.gravity = Gravity.TOP | Gravity.CENTER;
                break;
            case PageNumViewSiteMode.PAGE_NUM_VIEW_BOTTOM_CENTER:
                pageParams.gravity = Gravity.BOTTOM | Gravity.CENTER;
        }

        this.setTextColor(pageNumViewInterface.pageNumViewTextColor());
        this.setTextSize(pageNumViewInterface.pageNumViewTextSize());
        this.setPadding(pageNumViewInterface.pageNumViewPaddingLeft(), pageNumViewInterface.pageNumViewPaddingTop(), pageNumViewInterface.pageNumViewPaddingRight(), pageNumViewInterface.pageNumViewPaddingBottom());
        this.setBackgroundDrawable(BannerSelectorUtils.getShape(pageNumViewInterface.pageNumViewRadius(), pageNumViewInterface.pageNumViewBackgroundColor()));
        return pageParams;
    }

    public BannerPageNumView(@NonNull Context context) {
        super(context);
    }

    public BannerPageNumView(@NonNull Context context, @NonNull AttributeSet attrs) {
        super(context, attrs);
    }

    public BannerPageNumView(@NonNull Context context, @NonNull AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public interface PageNumViewInterface {

        int pageNumViewTopMargin();

        int pageNumViewRightMargin();

        int pageNumViewBottomMargin();

        int pageNumViewLeftMargin();

        int pageNumViewTextColor();

        float pageNumViewTextSize();

        int pageNumViewPaddingTop();

        int pageNumViewPaddingLeft();

        int pageNumViewPaddingBottom();

        int pageNumViewPaddingRight();

        float pageNumViewRadius();

        int pageNumViewBackgroundColor();

        int pageNumViewSite();

    }
}