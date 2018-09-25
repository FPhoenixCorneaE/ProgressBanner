package com.wkz.progressbanner.sample.simple;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.wkz.progressbanner.R;
import com.wkz.bannerlayout.listener.OnBannerChangeListener;
import com.wkz.bannerlayout.widget.BannerLayout;
import com.wkz.progressbanner.sample.bean.SimpleData;

public final class TransformerActivity extends AppCompatActivity {
    private BannerLayout transformerBanner;
    private AppCompatTextView positionTv;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_transformer);
        View var10001 = this.findViewById(R.id.transformer_banner);
        this.transformerBanner = (BannerLayout)var10001;
        Spinner spinner = (Spinner)this.findViewById(R.id.transformer_spinner);
        var10001 = this.findViewById(R.id.banner_position);
        this.positionTv = (AppCompatTextView)var10001;
        AppCompatTextView var10000 = this.positionTv;

        var10000.setText((CharSequence)"select position:0");
        BannerLayout var3 = this.transformerBanner;

        var3.setBannerTransformer(0).setDelayTime(300).initListResources(SimpleData.initModel()).startRotation(true).addOnPageChangeListener((OnBannerChangeListener)(new OnBannerChangeListener() {
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int position) {
                TransformerActivity.access$getPositionTv$p(TransformerActivity.this).setText((CharSequence)("select position:" + position));
            }

            public void onPageScrollStateChanged(int state) {
            }
        }));
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener)(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(@NonNull AdapterView parent, @NonNull View view, int position, long id) {
                TransformerActivity.access$getTransformerBanner$p(TransformerActivity.this).setBannerTransformer(position);
            }

            public void onNothingSelected(@NonNull AdapterView parent) {
            }
        }));
    }

    protected void onDestroy() {
        super.onDestroy();
        BannerLayout var10000 = this.transformerBanner;

        var10000.clearBanner();
    }

    // $FF: synthetic method
    @NonNull
    public static final AppCompatTextView access$getPositionTv$p(TransformerActivity $this) {
        AppCompatTextView var10000 = $this.positionTv;

        return var10000;
    }

    // $FF: synthetic method
    public static final void access$setPositionTv$p(TransformerActivity $this, @NonNull AppCompatTextView var1) {
        $this.positionTv = var1;
    }

    // $FF: synthetic method
    @NonNull
    public static final BannerLayout access$getTransformerBanner$p(TransformerActivity $this) {
        BannerLayout var10000 = $this.transformerBanner;

        return var10000;
    }

    // $FF: synthetic method
    public static final void access$setTransformerBanner$p(TransformerActivity $this, @NonNull BannerLayout var1) {
        $this.transformerBanner = var1;
    }
}
