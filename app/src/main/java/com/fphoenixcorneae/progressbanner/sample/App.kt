package com.fphoenixcorneae.progressbanner.sample

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this))
    }
}