package com.wkz.progressbanner.sample.simple;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wkz.progressbanner.R;
import com.wkz.bannerlayout.imagemanager.FrescoImageManager;
import com.wkz.bannerlayout.imagemanager.ImageLoaderManager;
import com.wkz.bannerlayout.imagemanager.PicassoImageManager;
import com.wkz.bannerlayout.listener.ImageDisplayManager;
import com.wkz.bannerlayout.widget.BannerLayout;
import com.wkz.progressbanner.sample.bean.SimpleData;

public final class ImageManagerActivity extends AppCompatActivity {
    private BannerLayout frescoBanner;
    private BannerLayout imageLoaderBanner;
    private BannerLayout picassoBanner;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_imagemanager);
        View var10001 = this.findViewById(R.id.fresco_banner);
        this.frescoBanner = (BannerLayout) var10001;
        var10001 = this.findViewById(R.id.imageloader_banner);
        this.imageLoaderBanner = (BannerLayout) var10001;
        var10001 = this.findViewById(R.id.picasso_banner);
        this.picassoBanner = (BannerLayout) var10001;
        BannerLayout var10000 = this.frescoBanner;

        var10000.setImageLoaderManager((ImageDisplayManager) (new FrescoImageManager()))
                .initListResources(SimpleData.initModel())
                .startRotation(true);
        var10000 = this.imageLoaderBanner;

        var10000.setImageLoaderManager((ImageDisplayManager) (new ImageLoaderManager()))
                .initListResources(SimpleData.initModel())
                .startRotation(false);
        var10000 = this.picassoBanner;

        var10000.setImageLoaderManager((ImageDisplayManager) (new PicassoImageManager()))
                .initListResources(SimpleData.initModel())
                .startRotation(true);
    }

    protected void onDestroy() {
        super.onDestroy();
        BannerLayout var10000 = this.frescoBanner;

        var10000.clearBanner();
        var10000 = this.imageLoaderBanner;

        var10000.clearBanner();
        var10000 = this.picassoBanner;

        var10000.clearBanner();
    }
}
