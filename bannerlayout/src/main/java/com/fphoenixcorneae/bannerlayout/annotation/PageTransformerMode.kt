package com.fphoenixcorneae.bannerlayout.annotation

import androidx.annotation.IntDef

@IntDef(
        PageTransformerMode.ANIMATION_ACCORDION,
        PageTransformerMode.ANIMATION_BACKGROUND,
        PageTransformerMode.ANIMATION_CUBE_IN,
        PageTransformerMode.ANIMATION_CUBE_OUT,
        PageTransformerMode.ANIMATION_DEFAULT,
        PageTransformerMode.ANIMATION_DEPTH_PAGE,
        PageTransformerMode.ANIMATION_DRAWER,
        PageTransformerMode.ANIMATION_FADE_OUT_FADE_IN,
        PageTransformerMode.ANIMATION_FLIP_HORIZONTAL,
        PageTransformerMode.ANIMATION_FLIP_VERTICAL,
        PageTransformerMode.ANIMATION_FOREGROUND,
        PageTransformerMode.ANIMATION_ROTATE_DOWN,
        PageTransformerMode.ANIMATION_ROTATE_UP,
        PageTransformerMode.ANIMATION_SCALE_IN_OUT,
        PageTransformerMode.ANIMATION_STACK,
        PageTransformerMode.ANIMATION_TABLET,
        PageTransformerMode.ANIMATION_VERTICAL,
        PageTransformerMode.ANIMATION_ZOOM_IN,
        PageTransformerMode.ANIMATION_ZOOM_OUT_PAGE,
        PageTransformerMode.ANIMATION_ZOOM_OUT_SLIDE,
        PageTransformerMode.ANIMATION_ZOOM_OUT
)
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
annotation class PageTransformerMode {
    companion object {
        const val ANIMATION_ACCORDION = 0
        const val ANIMATION_BACKGROUND = 1
        const val ANIMATION_CUBE_IN = 2
        const val ANIMATION_CUBE_OUT = 3
        const val ANIMATION_DEFAULT = 4
        const val ANIMATION_DEPTH_PAGE = 5
        const val ANIMATION_DRAWER = 6
        const val ANIMATION_FADE_OUT_FADE_IN = 7
        const val ANIMATION_FLIP_HORIZONTAL = 8
        const val ANIMATION_FLIP_VERTICAL = 9
        const val ANIMATION_FOREGROUND = 10
        const val ANIMATION_ROTATE_DOWN = 11
        const val ANIMATION_ROTATE_UP = 12
        const val ANIMATION_SCALE_IN_OUT = 13
        const val ANIMATION_STACK = 14
        const val ANIMATION_TABLET = 15
        const val ANIMATION_VERTICAL = 16
        const val ANIMATION_ZOOM_IN = 17
        const val ANIMATION_ZOOM_OUT_PAGE = 18
        const val ANIMATION_ZOOM_OUT_SLIDE = 19
        const val ANIMATION_ZOOM_OUT = 20
    }
}