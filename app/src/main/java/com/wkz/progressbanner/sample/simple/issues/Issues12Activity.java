package com.wkz.progressbanner.sample.simple.issues;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wkz.progressbanner.R;
import com.wkz.bannerlayout.widget.BannerLayout;
import com.wkz.progressbanner.sample.bean.SimpleData;

public final class Issues12Activity extends AppCompatActivity {
    private BannerLayout bannerLayout;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_issues_12);
        View var10001 = this.findViewById(R.id.issues_12_banner);
        this.bannerLayout = (BannerLayout)var10001;
        BannerLayout var10000 = this.bannerLayout;

        var10000.initPageNumView().initTips().setTipsDotsSelector(R.drawable.bl_selector_dots).setPageNumViewMargin(12, 12, 12, 12).initListResources(SimpleData.initModel()).switchBanner(true);
    }

    protected void onDestroy() {
        super.onDestroy();
        BannerLayout var10000 = this.bannerLayout;

        var10000.clearBanner();
    }
}
