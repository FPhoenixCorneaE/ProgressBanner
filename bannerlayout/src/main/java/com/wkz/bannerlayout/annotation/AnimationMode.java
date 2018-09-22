package com.wkz.bannerlayout.annotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
        AnimationMode.ANIMATION_ACCORDION,
        AnimationMode.ANIMATION_BACKGROUND,
        AnimationMode.ANIMATION_CUBE_IN,
        AnimationMode.ANIMATION_CUBE_OUT,
        AnimationMode.ANIMATION_DEFAULT,
        AnimationMode.ANIMATION_DEPTH_PAGE,
        AnimationMode.ANIMATION_DRAWER,
        AnimationMode.ANIMATION_FLIPHORIZONTAL,
        AnimationMode.ANIMATION_FLIPVERTICAL,
        AnimationMode.ANIMATION_FOREGROUND,
        AnimationMode.ANIMATION_ROTATEDOWN,
        AnimationMode.ANIMATION_ROTATEUP,
        AnimationMode.ANIMATION_SCALEINOUT,
        AnimationMode.ANIMATION_STACK,
        AnimationMode.ANIMATION_TABLET,
        AnimationMode.ANIMATION_VERTICAL,
        AnimationMode.ANIMATION_ZOOMIN,
        AnimationMode.ANIMATION_ZOOMOUTPAGE,
        AnimationMode.ANIMATION_ZOOMOUTSLIDE,
        AnimationMode.ANIMATION_ZOOMOUT
})
@Retention(RetentionPolicy.CLASS)
public @interface AnimationMode {
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

