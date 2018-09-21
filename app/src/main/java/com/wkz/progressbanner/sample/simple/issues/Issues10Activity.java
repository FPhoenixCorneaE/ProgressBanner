package com.wkz.progressbanner.sample.simple.issues;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wkz.progressbanner.R;
import com.wkz.bannerlayout.listener.OnBannerChangeListener;
import com.wkz.bannerlayout.widget.BannerLayout;
import com.wkz.progressbanner.sample.OnSimpleBannerChangeListener;
import com.wkz.progressbanner.sample.bean.SimpleData;

import java.util.List;

public final class Issues10Activity extends AppCompatActivity {
    private BannerLayout bannerLayout;
    private BannerLayout bannerlnstagram;
    private LinearLayout linearLayout;
    private boolean isShowTips;
    private int preEnablePosition;
    private int sizelnstagram;
    private static final String TAG = Issues10Activity.class.getSimpleName();

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_issues_10);
        View var10001 = this.findViewById(R.id.banner);
        this.bannerLayout = (BannerLayout)var10001;
        var10001 = this.findViewById(R.id.banner_lnstagram);
        this.bannerlnstagram = (BannerLayout)var10001;
        var10001 = this.findViewById(R.id.ll_view);
        this.linearLayout = (LinearLayout)var10001;
        this.test();
        this.testlnstagram();
    }

    protected void onDestroy() {
        super.onDestroy();
        BannerLayout var10000 = this.bannerLayout;

        var10000.clearBanner();
        var10000 = this.bannerlnstagram;

        var10000.clearBanner();
    }

    @TargetApi(16)
    private final void testlnstagram() {
        List data = SimpleData.lnstagramData();
        BannerLayout var10000 = this.bannerlnstagram;
        

        var10000.setPageNumViewMargin(10).initPageNumView().initListResources(data).setDelayTime(1000).switchBanner(false);
        this.sizelnstagram = data.size();
        LinearLayout var6 = this.linearLayout;
        

        var6.removeAllViews();
        int i = 0;

        for(int var3 = this.sizelnstagram; i < var3; ++i) {
            View view = new View((Context)this);
            view.setBackground(ContextCompat.getDrawable((Context)this, R.drawable.bl_selector_dots));
            view.setEnabled(i == 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15, 15);
            view.setLayoutParams((android.view.ViewGroup.LayoutParams)params);
            params.gravity = 17;
            params.leftMargin = 5;
            params.rightMargin = 5;
            var6 = this.linearLayout;
            

            var6.addView(view);
        }

        var10000 = this.bannerlnstagram;
        

        var10000.addOnPageChangeListener((OnBannerChangeListener)(new OnSimpleBannerChangeListener() {
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                View var10000 = Issues10Activity.access$getLinearLayout$p(Issues10Activity.this).getChildAt(position);
                var10000.setEnabled(true);
                var10000 = Issues10Activity.access$getLinearLayout$p(Issues10Activity.this).getChildAt(Issues10Activity.this.preEnablePosition);
                var10000.setEnabled(false);
                Issues10Activity.this.preEnablePosition = position;
                View startView = Issues10Activity.access$getLinearLayout$p(Issues10Activity.this).getChildAt(0);
                View endView = Issues10Activity.access$getLinearLayout$p(Issues10Activity.this).getChildAt(Issues10Activity.this.sizelnstagram - 1);
                if (position == Issues10Activity.this.sizelnstagram - 1) {
                    startView.setScaleX(0.6F);
                    startView.setScaleY(0.6F);
                    endView.setScaleX(1.0F);
                    endView.setScaleY(1.0F);
                } else if (position == 0) {
                    startView.setScaleX(1.0F);
                    startView.setScaleY(1.0F);
                    endView.setScaleX(0.6F);
                    endView.setScaleY(0.6F);
                }

            }
        }));
    }

    private final void test() {
        Window var10000 = this.getWindow();
        WindowManager var5 = var10000.getWindowManager();
        Display display = var5.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        byte dotWidthAndHeight;
        byte dotMargin;
        if (dm.widthPixels >= 1080) {
            dotWidthAndHeight = 15;
            dotMargin = 6;
        } else {
            dotWidthAndHeight = 6;
            dotMargin = 3;
        }

        BannerLayout var6 = this.bannerLayout;

        var6.setPageNumViewMargin(10).setDotsSite(13).setDotsWidthAndHeight(dotWidthAndHeight, dotWidthAndHeight).setDotsMargin(dotMargin).initTips().initPageNumView().initListResources(SimpleData.data()).switchBanner(true);
        this.findViewById(R.id.btn_alter_count).setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                Issues10Activity.this.alterBannerCount();
            }
        }));
    }

    private final void alterBannerCount() {
        List alterData = !this.isShowTips ? SimpleData.alterData() : SimpleData.data();
        this.isShowTips = !this.isShowTips;
        int size = alterData.size();
        BannerLayout var10000;
        if (size <= 1) {
            var10000 = this.bannerLayout;
            

            var10000.initTips(false, false,false, false).initListResources(alterData).switchBanner(false);
            Toast.makeText((Context)this, (CharSequence)"size <=1 , stopBanner , not show tipsLayout", Toast.LENGTH_SHORT).show();
        } else {
            var10000 = this.bannerLayout;
            

            var10000.initTips(true, true,true, true).initListResources(alterData).switchBanner(true);
            Toast.makeText((Context)this, (CharSequence)"size >1 , startBanner , show tipsLayout", Toast.LENGTH_SHORT).show();
        }

    }

    // $FF: synthetic method
    @NonNull
    public static final LinearLayout access$getLinearLayout$p(Issues10Activity $this) {
        LinearLayout var10000 = $this.linearLayout;


        return var10000;
    }

    // $FF: synthetic method
    public static final void access$setLinearLayout$p(Issues10Activity $this, @NonNull LinearLayout var1) {
        $this.linearLayout = var1;
    }

    // $FF: synthetic method
    public static final void access$setSizelnstagram$p(Issues10Activity $this, int var1) {
        $this.sizelnstagram = var1;
    }

}
