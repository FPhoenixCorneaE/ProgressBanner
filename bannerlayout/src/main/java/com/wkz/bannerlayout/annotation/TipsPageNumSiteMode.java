package com.wkz.bannerlayout.annotation;

import android.support.annotation.IntDef;
import android.widget.RelativeLayout;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
        TipsPageNumSiteMode.TOP_LEFT,
        TipsPageNumSiteMode.TOP_RIGHT,
        TipsPageNumSiteMode.BOTTOM_LEFT,
        TipsPageNumSiteMode.BOTTOM_RIGHT,
        TipsPageNumSiteMode.CENTER_LEFT,
        TipsPageNumSiteMode.CENTER_RIGHT,
        TipsPageNumSiteMode.TOP_CENTER,
        TipsPageNumSiteMode.BOTTOM_CENTER
})
@Retention(RetentionPolicy.CLASS)
public @interface TipsPageNumSiteMode {
    int TOP_LEFT = RelativeLayout.LEFT_OF;
    int TOP_RIGHT = RelativeLayout.RIGHT_OF;
    int BOTTOM_LEFT = RelativeLayout.ABOVE;
    int BOTTOM_RIGHT = RelativeLayout.BELOW;
    int CENTER_LEFT = RelativeLayout.ALIGN_BASELINE;
    int CENTER_RIGHT = RelativeLayout.ALIGN_LEFT;
    int TOP_CENTER = RelativeLayout.ALIGN_TOP;
    int BOTTOM_CENTER = RelativeLayout.ALIGN_RIGHT;
}
