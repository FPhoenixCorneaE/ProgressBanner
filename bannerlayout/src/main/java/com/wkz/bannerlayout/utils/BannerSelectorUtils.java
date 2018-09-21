package com.wkz.bannerlayout.utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.NonNull;

/**
 * by y on 2016/12/28
 */
public class BannerSelectorUtils {

    @NonNull
    public static StateListDrawable getDrawableSelector(float enabledRadius, int enabledColor, float normalRadius, int normalColor) {
        StateListDrawable drawable = new StateListDrawable();
        drawable.addState(new int[]{android.R.attr.state_enabled}, (Drawable) getShape(enabledRadius, enabledColor));
        drawable.addState(new int[]{-android.R.attr.state_enabled}, (Drawable) getShape(normalRadius, normalColor));
        return drawable;
    }

    @NonNull
    public static GradientDrawable getShape(float radius, int color) {
        GradientDrawable gd = new GradientDrawable();
        gd.setCornerRadius(radius);
        gd.setColor(color);
        return gd;
    }

}
