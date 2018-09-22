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
import com.wkz.bannerlayout.annotation.AnimationMode;

public class TransformerUtils {

    @NonNull
    public static BannerTransformer getTransformer(int type) {
        @AnimationMode
        int transformerType = type;
        switch (transformerType) {
            case AnimationMode.ANIMATION_ACCORDION:
                return new AccordionTransformer();
            case AnimationMode.ANIMATION_BACKGROUND:
                return new BackgroundToForegroundTransformer();
            case AnimationMode.ANIMATION_CUBE_IN:
                return new CubeInTransformer();
            case AnimationMode.ANIMATION_CUBE_OUT:
                return new CubeOutTransformer();
            case AnimationMode.ANIMATION_DEFAULT:
                return new DefaultTransformer();
            case AnimationMode.ANIMATION_DEPTH_PAGE:
                return new DepthPageTransformer();
            case AnimationMode.ANIMATION_DRAWER:
                return new DrawerTransformer();
            case AnimationMode.ANIMATION_FLIPHORIZONTAL:
                return new FlipHorizontalTransformer();
            case AnimationMode.ANIMATION_FLIPVERTICAL:
                return new FlipVerticalTransformer();
            case AnimationMode.ANIMATION_FOREGROUND:
                return new ForegroundToBackgroundTransformer();
            case AnimationMode.ANIMATION_ROTATEDOWN:
                return new RotateDownTransformer();
            case AnimationMode.ANIMATION_ROTATEUP:
                return new RotateUpTransformer();
            case AnimationMode.ANIMATION_SCALEINOUT:
                return new ScaleInOutTransformer();
            case AnimationMode.ANIMATION_STACK:
                return new StackTransformer();
            case AnimationMode.ANIMATION_TABLET:
                return new TabletTransformer();
            case AnimationMode.ANIMATION_VERTICAL:
                return new VerticalTransformer();
            case AnimationMode.ANIMATION_ZOOMIN:
                return new ZoomInTransformer();
            case AnimationMode.ANIMATION_ZOOMOUTPAGE:
                return new ZoomOutPageTransformer();
            case AnimationMode.ANIMATION_ZOOMOUTSLIDE:
                return new ZoomOutSlideTransformer();
            case AnimationMode.ANIMATION_ZOOMOUT:
                return new ZoomOutTranformer();
            default:
                return new DefaultTransformer();
        }
    }
}
