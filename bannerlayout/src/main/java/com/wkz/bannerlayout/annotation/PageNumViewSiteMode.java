package com.wkz.bannerlayout.annotation;

import android.support.annotation.IntDef;
import android.widget.RelativeLayout;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
        PageNumViewSiteMode.PAGE_NUM_VIEW_TOP_LEFT,
        PageNumViewSiteMode.PAGE_NUM_VIEW_TOP_RIGHT,
        PageNumViewSiteMode.PAGE_NUM_VIEW_BOTTOM_LEFT,
        PageNumViewSiteMode.PAGE_NUM_VIEW_BOTTOM_RIGHT,
        PageNumViewSiteMode.PAGE_NUM_VIEW_CENTER_LEFT,
        PageNumViewSiteMode.PAGE_NUM_VIEW_CENTER_RIGHT,
        PageNumViewSiteMode.PAGE_NUM_VIEW_TOP_CENTER,
        PageNumViewSiteMode.PAGE_NUM_VIEW_BOTTOM_CENTER
})
@Retention(RetentionPolicy.CLASS)
public @interface PageNumViewSiteMode {
    int PAGE_NUM_VIEW_TOP_LEFT = RelativeLayout.LEFT_OF;
    int PAGE_NUM_VIEW_TOP_RIGHT = RelativeLayout.RIGHT_OF;
    int PAGE_NUM_VIEW_BOTTOM_LEFT = RelativeLayout.ABOVE;
    int PAGE_NUM_VIEW_BOTTOM_RIGHT = RelativeLayout.BELOW;
    int PAGE_NUM_VIEW_CENTER_LEFT = RelativeLayout.ALIGN_BASELINE;
    int PAGE_NUM_VIEW_CENTER_RIGHT = RelativeLayout.ALIGN_LEFT;
    int PAGE_NUM_VIEW_TOP_CENTER = RelativeLayout.ALIGN_TOP;
    int PAGE_NUM_VIEW_BOTTOM_CENTER = RelativeLayout.ALIGN_RIGHT;
}
