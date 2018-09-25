package com.wkz.progressbanner.sample.simple;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.wkz.progressbanner.R;
import com.wkz.bannerlayout.imagemanager.GlideImageManager;
import com.wkz.bannerlayout.listener.BannerModelCallBack;
import com.wkz.bannerlayout.listener.OnBannerChangeListener;
import com.wkz.bannerlayout.listener.OnBannerClickListener;
import com.wkz.bannerlayout.widget.BannerLayout;
import com.wkz.progressbanner.sample.bean.SimpleData;
import com.wkz.progressbanner.sample.refresh.ArrayUtils;


public class SimpleActivity extends AppCompatActivity implements OnBannerClickListener {
    private BannerLayout defaultBanner;
    private BannerLayout customizeBanner;
    private BannerLayout verticalBanner;
    private final Object[] image = new Object[]{"http://ww2.sinaimg.cn/bmiddle/0060lm7Tgw1f94c6kxwh0j30dw099ta3.jpg", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491588490192&di=c7c9dfd2fc4b1eeb5a4a874ec9a30d1d&imgtype=0&src=http%3A%2F%2Fmvimg2.meitudata.com%2F55713dd0165c89055.jpg", "http://ww1.sinaimg.cn/bmiddle/0060lm7Tgw1f94c6f7f26j30dw0ii76k.jpg", "http://ww4.sinaimg.cn/bmiddle/0060lm7Tgw1f94c63dfjxj30dw0hjjtn.jpg"};
    private final String[] title = new String[]{"At that time just love, this time to break up", "Shame it ~", "The legs are not long but thin", "Late at night"};

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_simple);
        this.defaultBanner = this.findViewById(R.id.default_banner);
        this.customizeBanner = this.findViewById(R.id.customize_banner);
        this.verticalBanner = this.findViewById(R.id.vertical_banner);

        this.defaultBanner.initPageNumView()
                .initTips(false, false, true, false)
                .setImageLoaderManager(new GlideImageManager())
                .setTipsDotsSelector(R.drawable.bl_selector_dots)
                .setPageNumViewMargin(12, 12, 12, 12)
                .initListResources(SimpleData.initModel())
                .startRotation(true)
                .setOnBannerClickListener(this);

        this.customizeBanner.initPageNumView()
                .initTips()
                .setPageNumViewMargin(12, 12, 12, 12)
                .setPageNumViewMark(" & ")
                .setPageNumViewSite(3)
                .setPageNumViewTextColor(ContextCompat.getColor(this.getApplicationContext(), R.color.colorAccent))
                .setDotsSite(RelativeLayout.CENTER_IN_PARENT)
                .initListResources(ArrayUtils.initArrayResources(this.image, this.title))
                .startRotation(true)
                .setOnBannerClickListener(this)
                .addOnPageChangeListener((new OnBannerChangeListener() {
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    }

                    public void onPageSelected(int position) {
                    }

                    public void onPageScrollStateChanged(int state) {
                    }
                }));

        this.verticalBanner.setVertical(true)
                .initTips(true, true, true, true)
                .setOnBannerClickListener(this)
                .initListResources(SimpleData.initModel())
                .startRotation(true);
    }

    protected void onDestroy() {
        super.onDestroy();
        defaultBanner.clearBanner();
        customizeBanner.clearBanner();
        verticalBanner.clearBanner();
    }

    public void onBannerClick(@NonNull View view, int position, @NonNull BannerModelCallBack model) {
        Toast.makeText(view.getContext(), (CharSequence) String.valueOf(position), Toast.LENGTH_SHORT).show();

        startActivity(new Intent(this, RefreshActivity.class));
    }

}
