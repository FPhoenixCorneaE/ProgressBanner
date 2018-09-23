package com.wkz.bannerlayout.annotation;

import android.support.annotation.IntDef;
import android.widget.RelativeLayout;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
        TipsLayoutSiteMode.LEFT,
        TipsLayoutSiteMode.TOP,
        TipsLayoutSiteMode.RIGHT,
        TipsLayoutSiteMode.BOTTOM,
        TipsLayoutSiteMode.CENTER
})
@Retention(RetentionPolicy.CLASS)
public @interface TipsLayoutSiteMode {
    int LEFT = RelativeLayout.ALIGN_PARENT_LEFT;
    int TOP = RelativeLayout.ALIGN_PARENT_TOP;
    int RIGHT = RelativeLayout.ALIGN_PARENT_RIGHT;
    int BOTTOM = RelativeLayout.ALIGN_PARENT_BOTTOM;
    int CENTER = RelativeLayout.CENTER_IN_PARENT;
}
