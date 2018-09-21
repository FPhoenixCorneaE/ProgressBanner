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
import com.wkz.bannerlayout.widget.BannerLayout;

/**
 * by y on 2016/11/11
 */
public class TransformerUtils {

    @NonNull
    public static BannerTransformer getTransformer(int type) {
        @AnimationMode
        int transformerType = type;
        switch (transformerType) {
            case BannerLayout.ANIMATION_ACCORDION:
                return new AccordionTransformer();
            case BannerLayout.ANIMATION_BACKGROUND:
                return new BackgroundToForegroundTransformer();
            case BannerLayout.ANIMATION_CUBE_IN:
                return new CubeInTransformer();
            case BannerLayout.ANIMATION_CUBE_OUT:
                return new CubeOutTransformer();
            case BannerLayout.ANIMATION_DEFAULT:
                return new DefaultTransformer();
            case BannerLayout.ANIMATION_DEPTH_PAGE:
                return new DepthPageTransformer();
            case BannerLayout.ANIMATION_DRAWER:
                return new DrawerTransformer();
            case BannerLayout.ANIMATION_FLIPHORIZONTAL:
                return new FlipHorizontalTransformer();
            case BannerLayout.ANIMATION_FLIPVERTICAL:
                return new FlipVerticalTransformer();
            case BannerLayout.ANIMATION_FOREGROUND:
                return new ForegroundToBackgroundTransformer();
            case BannerLayout.ANIMATION_ROTATEDOWN:
                return new RotateDownTransformer();
            case BannerLayout.ANIMATION_ROTATEUP:
                return new RotateUpTransformer();
            case BannerLayout.ANIMATION_SCALEINOUT:
                return new ScaleInOutTransformer();
            case BannerLayout.ANIMATION_STACK:
                return new StackTransformer();
            case BannerLayout.ANIMATION_TABLET:
                return new TabletTransformer();
            case BannerLayout.ANIMATION_VERTICAL:
                return new VerticalTransformer();
            case BannerLayout.ANIMATION_ZOOMIN:
                return new ZoomInTransformer();
            case BannerLayout.ANIMATION_ZOOMOUTPAGE:
                return new ZoomOutPageTransformer();
            case BannerLayout.ANIMATION_ZOOMOUTSLIDE:
                return new ZoomOutSlideTransformer();
            case BannerLayout.ANIMATION_ZOOMOUT:
                return new ZoomOutTranformer();
            default:
                return new DefaultTransformer();
        }
    }
}
