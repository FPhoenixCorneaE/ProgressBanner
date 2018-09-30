package com.wkz.progressbanner.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.wkz.bannerlayout.annotation.TipsDotsSiteMode;
import com.wkz.bannerlayout.annotation.TipsPageNumSiteMode;
import com.wkz.bannerlayout.annotation.TipsProgressesSiteMode;
import com.wkz.bannerlayout.annotation.TipsTitleSiteMode;
import com.wkz.bannerlayout.imagemanager.GlideImageManager;
import com.wkz.bannerlayout.listener.BannerModelCallBack;
import com.wkz.bannerlayout.listener.OnBannerClickListener;
import com.wkz.bannerlayout.listener.OnSimpleBannerChangeListener;
import com.wkz.bannerlayout.widget.BannerLayout;
import com.wkz.bannerlayout.widget.ProgressDrawable;
import com.wkz.progressbanner.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private BannerLayout mBlBanner;
    private SwipeRefreshLayout mSrlRefresh;
    /**
     * Start
     */
    private Button mBtnStart;
    /**
     * Stop
     */
    private Button mBtnStop;
    /**
     * Update
     */
    private Button mBtnUpdate;
    private Spinner mSpSpinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        initData();
    }

    private void initView() {
        mBlBanner = findViewById(R.id.bl_banner);
        mSrlRefresh = findViewById(R.id.srl_refresh);
        mBtnStart = findViewById(R.id.btn_start);
        mBtnStop = findViewById(R.id.btn_stop);
        mBtnUpdate = findViewById(R.id.btn_update);
        mSpSpinner = findViewById(R.id.sp_spinner);
    }

    private void initListener() {
        mBtnStart.setOnClickListener(this);
        mBtnStop.setOnClickListener(this);
        mBtnUpdate.setOnClickListener(this);
        mSpSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(@NonNull AdapterView parent, @NonNull View view, int position, long id) {
                if (position == 14) {
                    mBlBanner.setVertical(true);
                } else {
                    mBlBanner.setVertical(false);
                }
                mBlBanner.setBannerTransformer(position);
            }

            public void onNothingSelected(@NonNull AdapterView parent) {
            }
        });
        mSrlRefresh.setOnRefreshListener(this);
    }

    private void initData() {
        mBlBanner
                //初始化指示器显示与否
                .initTips(false, true, true, true)
                //动画时间
                .setDuration(2000)
                //翻页间隔
                .setDelayTime(800)
                //是否导航页
//                .setGuide(true)
                //循环方向为垂直方向
//                .setVertical(true)
                //viewpager是否能翻页,true为不能,false为可以
                .setViewPagerTouchMode(true)
                //图片加载管理器
                .setImageLoaderManager(new GlideImageManager())
//                .setImageLoaderManager(new FrescoImageManager())
//                .setImageLoaderManager(new ImageLoaderManager())
//                .setImageLoaderManager(new PicassoImageManager())
                //初始化页数指示器
                .initPageNumView()
                .setPageNumViewTextColor(Color.WHITE)
                .setPageNumViewTextSize(13f)
                .setPageNumViewMargin(10f, 10f, 10f, 10f)
                .setPageNumViewPadding(5f, 0f, 5f, 0f)
                .setPageNumViewRadius(30f)
                .setPageNumViewBackgroundColor(Color.BLACK)
                .setPageNumViewMark(" & ")
                .setPageNumViewSite(TipsPageNumSiteMode.TOP_CENTER)
                //初始化小圆点指示器
                .setDotsMargin(2.5f, 0f, 2.5f, 50f)
                .setDotsWidthAndHeight(5f, 5f)
                .setDotsEnabledColor(Color.RED)
                .setDotsEnabledRadius(20f)
                .setDotsNormalColor(Color.WHITE)
                .setDotsNormalRadius(20f)
                .setDotsSite(TipsDotsSiteMode.BOTTOM, TipsDotsSiteMode.CENTER_HORIZONTAL)
                //初始化进度指示器
                .setProgressesMargin(2.5f, 0f, 2.5f, 40f)
                .setProgressesBuilder(new ProgressDrawable.Builder(this)
                        .setWidth(40f)
                        .setHeight(2.5f)
                        .setDuration(2000)
                        .setBackgroundColor(Color.WHITE)
                        .setProgressColor(Color.RED)
                        .setRadius(10f)
                        .setAnimated(true)
                )
                .setProgressesSite(TipsProgressesSiteMode.BOTTOM, TipsProgressesSiteMode.CENTER_HORIZONTAL)
                //初始化标题
                .setTitleTextColor(Color.BLACK)
                .setTitleTextSize(13f)
                .setTitleMargin(10f, 8f, 10f, 8f)
                .setTitleBackgroundColor(0x50000000)
                .setTitleSite(TipsTitleSiteMode.BOTTOM)
                //初始化数据,须在最后
                .initListResources(ImageData.getImages())
                //点击监听
                .setOnBannerClickListener(new OnBannerClickListener() {
                    @Override
                    public void onBannerClick(@NonNull View view, int position, BannerModelCallBack model) {
                        Toast.makeText(MainActivity.this, model.getBannerTitle(), Toast.LENGTH_SHORT).show();
                    }
                })
                //页面切换监听
                .addOnPageChangeListener(new OnSimpleBannerChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    }

                    @Override
                    public void onPageSelected(int position) {
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                    }
                })
                //是否开启循环
                .startRotation(true)
        ;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_start:
                mBlBanner.startRotation(true);
                break;
            case R.id.btn_stop:
                mBlBanner.startRotation(false);
                break;
            case R.id.btn_update:
                mBlBanner.initListResources(ImageData.updateImages());
                break;
        }
    }

    @Override
    public void onRefresh() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                mSrlRefresh.setRefreshing(false);
                mBlBanner.initListResources(ImageData.updateImages())
                        .startRotation(true);
            }
        }, 1000);
    }
}
