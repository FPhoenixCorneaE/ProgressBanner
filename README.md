# ProgressBanner

以进度条为指示器的banner
========================================

![图片预览](https://github.com/FPhoenixCorneaE/ProgressBanner/blob/master/preview/preview.gif)


```
mBlBanner
        //初始化指示器显示与否
        .initTips(false, true, true, true)
        //动画时间
        .setDuration(2000)
        //翻页间隔
        .setDelayTime(800)
        //是否导航页
//        .setGuide(true)
        //循环方向为垂直方向
//        .setVertical(true)
        //viewpager是否能翻页,true为不能,false为可以
        .setViewPagerTouchMode(true)
        //图片加载管理器
        .setImageLoaderManager(new GlideImageManager())
//        .setImageLoaderManager(new FrescoImageManager())
//        .setImageLoaderManager(new ImageLoaderManager())
//        .setImageLoaderManager(new PicassoImageManager())
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
```
-----------------

开启循环
-------
```
mBlBanner.startRotation(true);
```
--------------------------

停止循环
-------------
```
mBlBanner.startRotation(false);
```
-----------------

更新数据
---------------
```
mBlBanner.initListResources(ImageData.updateImages());
```

