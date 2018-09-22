package com.wkz.bannerlayout.annotation;

import android.support.annotation.IntDef;
import android.widget.RelativeLayout;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
        DotsAndTitleSiteMode.CENTER,
        DotsAndTitleSiteMode.LEFT,
        DotsAndTitleSiteMode.RIGHT
})
@Retention(RetentionPolicy.CLASS)
public @interface DotsAndTitleSiteMode {
     int LEFT = RelativeLayout.ALIGN_PARENT_LEFT;
     int RIGHT = RelativeLayout.ALIGN_PARENT_RIGHT;
     int CENTER = RelativeLayout.CENTER_IN_PARENT;
}
