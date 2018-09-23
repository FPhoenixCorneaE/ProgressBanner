package com.wkz.bannerlayout.annotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
        ProgressShapeMode.RECTANGLE,
        ProgressShapeMode.RING
})
@Retention(RetentionPolicy.CLASS)
public @interface ProgressShapeMode {
    int RECTANGLE = 0;
    int RING = 1;
}
