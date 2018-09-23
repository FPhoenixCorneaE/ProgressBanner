package com.wkz.bannerlayout.annotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
        PageTransformerMode.ANIMATION_ACCORDION,
        PageTransformerMode.ANIMATION_BACKGROUND,
        PageTransformerMode.ANIMATION_CUBE_IN,
        PageTransformerMode.ANIMATION_CUBE_OUT,
        PageTransformerMode.ANIMATION_DEFAULT,
        PageTransformerMode.ANIMATION_DEPTH_PAGE,
        PageTransformerMode.ANIMATION_DRAWER,
        PageTransformerMode.ANIMATION_FLIPHORIZONTAL,
        PageTransformerMode.ANIMATION_FLIPVERTICAL,
        PageTransformerMode.ANIMATION_FOREGROUND,
        PageTransformerMode.ANIMATION_ROTATEDOWN,
        PageTransformerMode.ANIMATION_ROTATEUP,
        PageTransformerMode.ANIMATION_SCALEINOUT,
        PageTransformerMode.ANIMATION_STACK,
        PageTransformerMode.ANIMATION_TABLET,
        PageTransformerMode.ANIMATION_VERTICAL,
        PageTransformerMode.ANIMATION_ZOOMIN,
        PageTransformerMode.ANIMATION_ZOOMOUTPAGE,
        PageTransformerMode.ANIMATION_ZOOMOUTSLIDE,
        PageTransformerMode.ANIMATION_ZOOMOUT
})
@Retention(RetentionPolicy.CLASS)
public @interface PageTransformerMode {
     int ANIMATION_ACCORDION = 0;
     int ANIMATION_BACKGROUND = 1;
     int ANIMATION_CUBE_IN = 2;
     int ANIMATION_CUBE_OUT = 3;
     int ANIMATION_DEFAULT = 4;
     int ANIMATION_DEPTH_PAGE = 5;
     int ANIMATION_FLIPHORIZONTAL = 6;
     int ANIMATION_FLIPVERTICAL = 7;
     int ANIMATION_FOREGROUND = 8;
     int ANIMATION_ROTATEDOWN = 9;
     int ANIMATION_ROTATEUP = 10;
     int ANIMATION_SCALEINOUT = 11;
     int ANIMATION_STACK = 12;
     int ANIMATION_TABLET = 13;
     int ANIMATION_VERTICAL = 14;
     int ANIMATION_ZOOMIN = 15;
     int ANIMATION_ZOOMOUTPAGE = 16;
     int ANIMATION_ZOOMOUTSLIDE = 17;
     int ANIMATION_ZOOMOUT = 18;
     int ANIMATION_DRAWER = 19;
}

