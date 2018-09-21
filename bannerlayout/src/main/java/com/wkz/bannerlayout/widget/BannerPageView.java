package com.wkz.bannerlayout.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.wkz.bannerlayout.annotation.PageNumViewSiteMode;
import com.wkz.bannerlayout.utils.BannerSelectorUtils;

/**
 * by y on 2017/1/6
 */
public class BannerPageView extends AppCompatTextView {
    @NonNull
    public final FrameLayout.LayoutParams initPageView(@NonNull BannerPageView.PageNumViewInterface pageNumViewInterface) {
        FrameLayout.LayoutParams pageParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        pageParams.setMargins(pageNumViewInterface.pageNumViewLeftMargin(),
                pageNumViewInterface.pageNumViewTopMargin(),
                pageNumViewInterface.pageNumViewRightMargin(),
                pageNumViewInterface.pageNumViewBottomMargin());
        @PageNumViewSiteMode
        int pageNumViewSiteMode = pageNumViewInterface.pageNumViewSite();
        switch (pageNumViewSiteMode) {
            case BannerLayout.PAGE_NUM_VIEW_TOP_LEFT:
            default:
                pageParams.gravity = Gravity.START | Gravity.TOP;
                break;
            case BannerLayout.PAGE_NUM_VIEW_TOP_RIGHT:
                pageParams.gravity = Gravity.END | Gravity.TOP;
                break;
            case BannerLayout.PAGE_NUM_VIEW_BOTTOM_LEFT:
                pageParams.gravity = Gravity.START | Gravity.BOTTOM;
                break;
            case BannerLayout.PAGE_NUM_VIEW_BOTTOM_RIGHT:
                pageParams.gravity = Gravity.END | Gravity.BOTTOM;
                break;
            case BannerLayout.PAGE_NUM_VIEW_CENTER_LEFT:
                pageParams.gravity = Gravity.START | Gravity.CENTER;
                break;
            case BannerLayout.PAGE_NUM_VIEW_CENTER_RIGHT:
                pageParams.gravity = Gravity.END | Gravity.CENTER;
                break;
            case BannerLayout.PAGE_NUM_VIEW_TOP_CENTER:
                pageParams.gravity = Gravity.TOP | Gravity.CENTER;
                break;
            case BannerLayout.PAGE_NUM_VIEW_BOTTOM_CENTER:
                pageParams.gravity = Gravity.BOTTOM | Gravity.CENTER;
        }

        this.setTextColor(pageNumViewInterface.pageNumViewTextColor());
        this.setTextSize(pageNumViewInterface.pageNumViewTextSize());
        this.setPadding(pageNumViewInterface.pageNumViewPaddingLeft(), pageNumViewInterface.pageNumViewPaddingTop(), pageNumViewInterface.pageNumViewPaddingRight(), pageNumViewInterface.pageNumViewPaddingBottom());
        this.setBackgroundDrawable((Drawable) BannerSelectorUtils.getShape(pageNumViewInterface.pageNumViewRadius(), pageNumViewInterface.pageNumViewBackgroundColor()));
        return pageParams;
    }

    public BannerPageView(@NonNull Context context) {
        super(context);
    }

    public BannerPageView(@NonNull Context context, @NonNull AttributeSet attrs) {
        super(context, attrs);
    }

    public BannerPageView(@NonNull Context context, @NonNull AttributeSet attrs, int defStyleAttr) {
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