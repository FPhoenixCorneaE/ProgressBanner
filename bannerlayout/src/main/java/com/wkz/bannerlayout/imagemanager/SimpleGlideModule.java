package com.wkz.bannerlayout.imagemanager;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.AppGlideModule;

public class SimpleGlideModule extends AppGlideModule {
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        builder.setMemoryCache(
                (MemoryCache) (new LruResourceCache(
                         (new MemorySizeCalculator.Builder(context))
                                .setMemoryCacheScreens(2.0F)
                                .build()
                                .getMemoryCacheSize())));
    }
}
