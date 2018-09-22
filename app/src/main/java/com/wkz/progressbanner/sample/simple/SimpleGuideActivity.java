package com.wkz.progressbanner.sample.simple;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.Window;

import com.wkz.bannerlayout.widget.BannerLayout;
import com.wkz.progressbanner.R;
import com.wkz.bannerlayout.listener.OnSimpleBannerChangeListener;
import com.wkz.progressbanner.sample.bean.SimpleData;

import java.util.List;

public final class SimpleGuideActivity extends AppCompatActivity {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        this.requestWindowFeature(1);
        int flag = 1024;
        window.setFlags(flag, flag);
        this.setContentView(R.layout.activity_guide);
        final BannerLayout guideBanner = (BannerLayout) this.findViewById(R.id.banner_guide);
        final AppCompatButton guideButton = (AppCompatButton) this.findViewById(R.id.button_guide);
        guideButton.setVisibility(8);
        guideBanner.initTips()
                .setGuide(true)
                .setTipsDotsSelector(R.drawable.bl_selector_dots)
                .setDotsSite(13)
                .setTipsWidthAndHeight(-1, 300)
                .setDotsWidthAndHeight(10, 10)
                .initListResources(SimpleData.update());
        guideBanner.addOnPageChangeListener((new OnSimpleBannerChangeListener() {
            public void onPageSelected(int position) {
                AppCompatButton var10000 = guideButton;
                List var10002 = guideBanner.getImageList();

                var10000.setVisibility(position == var10002.size() - 1 ? 0 : 8);
            }
        }));
        guideButton.setOnClickListener(null);
    }
}
