package com.wkz.bannerlayout.utils;

import android.support.annotation.NonNull;

import com.wkz.bannerlayout.animation.AccordionTransformer;
import com.wkz.bannerlayout.animation.BackgroundToForegroundTransformer;
import com.wkz.bannerlayout.animation.BannerTransformer;
import com.wkz.bannerlayout.animation.CubeInTransformer;
import com.wkz.bannerlayout.animation.CubeOutTransformer;
import com.wkz.bannerlayout.animation.DefaultTransformer;
import com.wkz.bannerlayout.animation.DepthPageTransformer;
import com.wkz.bannerlayout.animation.DrawerTransformer;
import com.wkz.bannerlayout.animation.FlipHorizontalTransformer;
import com.wkz.bannerlayout.animation.FlipVerticalTransformer;
import com.wkz.bannerlayout.animation.ForegroundToBackgroundTransformer;
import com.wkz.bannerlayout.animation.RotateDownTransformer;
import com.wkz.bannerlayout.animation.RotateUpTransformer;
import com.wkz.bannerlayout.animation.ScaleInOutTransformer;
import com.wkz.bannerlayout.animation.StackTransformer;
import com.wkz.bannerlayout.animation.TabletTransformer;
import com.wkz.bannerlayout.animation.VerticalTransformer;
import com.wkz.bannerlayout.animation.ZoomInTransformer;
import com.wkz.bannerlayout.animation.ZoomOutPageTransformer;
import com.wkz.bannerlayout.animation.ZoomOutSlideTransformer;
import com.wkz.bannerlayout.animation.ZoomOutTranformer;
import com.wkz.bannerlayout.annotation.PageTransformerMode;

public class TransformerUtils {

    @NonNull
    public static BannerTransformer getTransformer(int type) {
        @PageTransformerMode
        int transformerType = type;
        switch (transformerType) {
            case PageTransformerMode.ANIMATION_ACCORDION:
                return new AccordionTransformer();
            case PageTransformerMode.ANIMATION_BACKGROUND:
                return new BackgroundToForegroundTransformer();
            case PageTransformerMode.ANIMATION_CUBE_IN:
                return new CubeInTransformer();
            case PageTransformerMode.ANIMATION_CUBE_OUT:
                return new CubeOutTransformer();
            case PageTransformerMode.ANIMATION_DEFAULT:
                return new DefaultTransformer();
            case PageTransformerMode.ANIMATION_DEPTH_PAGE:
                return new DepthPageTransformer();
            case PageTransformerMode.ANIMATION_DRAWER:
                return new DrawerTransformer();
            case PageTransformerMode.ANIMATION_FLIPHORIZONTAL:
                return new FlipHorizontalTransformer();
            case PageTransformerMode.ANIMATION_FLIPVERTICAL:
                return new FlipVerticalTransformer();
            case PageTransformerMode.ANIMATION_FOREGROUND:
                return new ForegroundToBackgroundTransformer();
            case PageTransformerMode.ANIMATION_ROTATEDOWN:
                return new RotateDownTransformer();
            case PageTransformerMode.ANIMATION_ROTATEUP:
                return new RotateUpTransformer();
            case PageTransformerMode.ANIMATION_SCALEINOUT:
                return new ScaleInOutTransformer();
            case PageTransformerMode.ANIMATION_STACK:
                return new StackTransformer();
            case PageTransformerMode.ANIMATION_TABLET:
                return new TabletTransformer();
            case PageTransformerMode.ANIMATION_VERTICAL:
                return new VerticalTransformer();
            case PageTransformerMode.ANIMATION_ZOOMIN:
                return new ZoomInTransformer();
            case PageTransformerMode.ANIMATION_ZOOMOUTPAGE:
                return new ZoomOutPageTransformer();
            case PageTransformerMode.ANIMATION_ZOOMOUTSLIDE:
                return new ZoomOutSlideTransformer();
            case PageTransformerMode.ANIMATION_ZOOMOUT:
                return new ZoomOutTranformer();
            default:
                return new DefaultTransformer();
        }
    }
}
