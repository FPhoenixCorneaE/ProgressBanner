package com.wkz.bannerlayout.annotation;

import android.support.annotation.IntDef;
import android.widget.RelativeLayout;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
        TipsProgressesSiteMode.LEFT,
        TipsProgressesSiteMode.TOP,
        TipsProgressesSiteMode.RIGHT,
        TipsProgressesSiteMode.BOTTOM,
        TipsProgressesSiteMode.CENTER,
        TipsProgressesSiteMode.CENTER_HORIZONTAL,
        TipsProgressesSiteMode.CENTER_VERTICAL
})
@Retention(RetentionPolicy.CLASS)
public @interface TipsProgressesSiteMode {
    int LEFT = RelativeLayout.ALIGN_PARENT_LEFT;
    int TOP = RelativeLayout.ALIGN_PARENT_TOP;
    int RIGHT = RelativeLayout.ALIGN_PARENT_RIGHT;
    int BOTTOM = RelativeLayout.ALIGN_PARENT_BOTTOM;
    int CENTER = RelativeLayout.CENTER_IN_PARENT;
    int CENTER_HORIZONTAL = RelativeLayout.CENTER_HORIZONTAL;
    int CENTER_VERTICAL = RelativeLayout.CENTER_VERTICAL;
}
