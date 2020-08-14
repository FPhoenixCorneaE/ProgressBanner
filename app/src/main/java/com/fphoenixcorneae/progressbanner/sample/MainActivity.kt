package com.fphoenixcorneae.progressbanner.sample

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.fphoenixcorneae.bannerlayout.annotation.TipsDotsSiteMode
import com.fphoenixcorneae.bannerlayout.annotation.TipsPageNumSiteMode
import com.fphoenixcorneae.bannerlayout.annotation.TipsProgressesSiteMode
import com.fphoenixcorneae.bannerlayout.annotation.TipsTitleSiteMode
import com.fphoenixcorneae.bannerlayout.imagemanager.GlideImageManager
import com.fphoenixcorneae.bannerlayout.listener.BannerModelCallBack
import com.fphoenixcorneae.bannerlayout.listener.OnBannerClickListener
import com.fphoenixcorneae.bannerlayout.listener.OnSimpleBannerChangeListener
import com.fphoenixcorneae.bannerlayout.utils.DisplayUtils.dp2px
import com.fphoenixcorneae.bannerlayout.widget.ProgressDrawable
import com.fphoenixcorneae.progressbanner.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener, OnRefreshListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListener()
        initData()
    }

    private fun initListener() {
        mBtnStart.setOnClickListener(this)
        mBtnStop.setOnClickListener(this)
        mBtnUpdate.setOnClickListener(this)
        mSpSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                if (position == 14) {
                    mBlBanner.setVertical(true)
                } else {
                    mBlBanner.setVertical(false)
                }
                mBlBanner.setBannerTransformer(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        mSrlRefresh.setOnRefreshListener(this)
    }

    private fun initData() {
        mBlBanner //初始化指示器显示与否
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
                .setViewPagerTouchMode(false)
                //图片加载管理器
                .setImageLoaderManager(GlideImageManager(dp2px(this, 8f)))
//                .setImageLoaderManager(FrescoImageManager(DisplayUtils.dp2px(this, 8f)))
//                .setImageLoaderManager(ImageLoaderManager())
//                .setImageLoaderManager(PicassoImageManager())
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
                .setProgressesBuilder(ProgressDrawable.Builder(this)
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
                .initListResources(ImageData.images)
                //点击监听
                .setOnBannerClickListener(object : OnBannerClickListener {
                    override fun onBannerClick(view: View, position: Int, model: BannerModelCallBack<*>?) {
                        Toast.makeText(this@MainActivity, model?.bannerTitle, Toast.LENGTH_SHORT).show()
                    }
                })
                //页面切换监听
                .addOnPageChangeListener(object : OnSimpleBannerChangeListener() {
                    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
                    override fun onPageSelected(position: Int) {}
                    override fun onPageScrollStateChanged(state: Int) {}
                })
                //是否开启循环
                .startRotation(true)
    }

    override fun onPause() {
        super.onPause()
        // 停止循环
        mBlBanner.startRotation(false)
    }

    override fun onResume() {
        super.onResume()
        // 开启循环
        mBlBanner.startRotation(true)
    }

    override fun onClick(v: View) {
        when (v) {
            mBtnStart -> mBlBanner.startRotation(true)
            mBtnStop -> mBlBanner.startRotation(false)
            mBtnUpdate -> mBlBanner.initListResources(ImageData.updateImages())
            else -> {
            }
        }
    }

    override fun onRefresh() {
        Handler(Looper.getMainLooper()).postDelayed({
            mSrlRefresh.isRefreshing = false
            mBlBanner.initListResources(ImageData.updateImages())
                    .startRotation(true)
        }, 1000)
    }
}