package com.fphoenixcorneae.bannerlayout.utils

import androidx.viewpager.widget.ViewPager.PageTransformer
import com.fphoenixcorneae.bannerlayout.annotation.PageTransformerMode
import com.fphoenixcorneae.transformer.*

object TransformerUtils {
    fun getTransformer(@PageTransformerMode type: Int): PageTransformer {
        return when (type) {
            PageTransformerMode.ANIMATION_ACCORDION -> AccordionTransformer()
            PageTransformerMode.ANIMATION_BACKGROUND -> BackgroundToForegroundTransformer()
            PageTransformerMode.ANIMATION_CUBE_IN -> CubeInTransformer()
            PageTransformerMode.ANIMATION_CUBE_OUT -> CubeOutTransformer()
            PageTransformerMode.ANIMATION_DEFAULT -> DefaultTransformer()
            PageTransformerMode.ANIMATION_DEPTH_PAGE -> DepthPageTransformer()
            PageTransformerMode.ANIMATION_DRAWER -> DrawerTransformer()
            PageTransformerMode.ANIMATION_FADE_OUT_FADE_IN -> FadeOutFadeInTransformer()
            PageTransformerMode.ANIMATION_FLIP_HORIZONTAL -> FlipHorizontalTransformer()
            PageTransformerMode.ANIMATION_FLIP_VERTICAL -> FlipVerticalTransformer()
            PageTransformerMode.ANIMATION_FOREGROUND -> ForegroundToBackgroundTransformer()
            PageTransformerMode.ANIMATION_ROTATE_DOWN -> RotateDownTransformer()
            PageTransformerMode.ANIMATION_ROTATE_UP -> RotateUpTransformer()
            PageTransformerMode.ANIMATION_SCALE_IN_OUT -> ScaleInOutTransformer()
            PageTransformerMode.ANIMATION_STACK -> StackTransformer()
            PageTransformerMode.ANIMATION_TABLET -> TabletTransformer()
            PageTransformerMode.ANIMATION_VERTICAL -> VerticalTransformer()
            PageTransformerMode.ANIMATION_ZOOM_IN -> ZoomInTransformer()
            PageTransformerMode.ANIMATION_ZOOM_OUT_PAGE -> ZoomOutPageTransformer()
            PageTransformerMode.ANIMATION_ZOOM_OUT_SLIDE -> ZoomOutSlideTransformer()
            PageTransformerMode.ANIMATION_ZOOM_OUT -> ZoomOutTransformer()
            else -> DefaultTransformer()
        }
    }
}