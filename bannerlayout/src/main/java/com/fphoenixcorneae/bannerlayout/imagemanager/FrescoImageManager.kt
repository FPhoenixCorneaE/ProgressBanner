package com.fphoenixcorneae.bannerlayout.imagemanager

import android.view.ViewGroup
import android.widget.ImageView
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder
import com.facebook.drawee.generic.RoundingParams
import com.facebook.drawee.view.SimpleDraweeView
import com.fphoenixcorneae.bannerlayout.listener.BannerModelCallBack
import com.fphoenixcorneae.bannerlayout.listener.ImageDisplayManager

class FrescoImageManager(private val cornerRadius: Int) : ImageDisplayManager {

    override fun display(container: ViewGroup, model: BannerModelCallBack<*>?): ImageView {
        val simpleDraweeView = SimpleDraweeView(container.context)
        val roundingParams = RoundingParams()
        roundingParams.setCornersRadius(cornerRadius.toFloat())
        val builder = GenericDraweeHierarchyBuilder(container.context.resources)
        val hierarchy = builder.build()
        hierarchy.roundingParams = roundingParams
        // 一定要先设置Hierarchy，再去加载图片，否则会加载不出来图片
        simpleDraweeView.hierarchy = hierarchy
        simpleDraweeView.setImageURI(model?.bannerUrl as? String)
        return simpleDraweeView
    }
}