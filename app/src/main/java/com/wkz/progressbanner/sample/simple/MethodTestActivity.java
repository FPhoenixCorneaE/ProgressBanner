package com.wkz.progressbanner.sample.simple;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wkz.progressbanner.R;
import com.wkz.bannerlayout.listener.BannerModelCallBack;
import com.wkz.bannerlayout.listener.OnBannerChangeListener;
import com.wkz.bannerlayout.listener.OnBannerClickListener;
import com.wkz.bannerlayout.widget.BannerLayout;
import com.wkz.progressbanner.sample.bean.SimpleData;

public final class MethodTestActivity extends AppCompatActivity implements OnBannerClickListener, OnBannerChangeListener {
    private BannerLayout xmlBannerLayout;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_method_test);
        View var10001 = this.findViewById(R.id.method_banner);
        this.xmlBannerLayout = (BannerLayout)var10001;
        BannerLayout var10000 = this.xmlBannerLayout;

        var10000.initListResources(SimpleData.initModel());
    }

    public void onBannerClick(@NonNull View view, int position, @NonNull BannerModelCallBack model) {
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    public void onPageSelected(int position) {
    }

    public void onPageScrollStateChanged(int state) {
    }
}
