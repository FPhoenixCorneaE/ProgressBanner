package com.fphoenixcorneae.bannerlayout.widget

import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fphoenixcorneae.bannerlayout.listener.BannerModelCallBack
import com.fphoenixcorneae.bannerlayout.listener.ImageDisplayManager
import com.fphoenixcorneae.bannerlayout.listener.OnBannerImageClickListener

class BannerAdapter(
        private val imageList: List<BannerModelCallBack<*>>,
        imageLoaderManage: ImageDisplayManager?,
        errorDrawable: Drawable?,
        placeholderDrawable: Drawable?,
        private val isGuide: Boolean
) : PagerAdapter() {
    private var imageLoaderManage: ImageDisplayManager? = null
    private var imageClickListener: OnBannerImageClickListener? = null
    override fun getCount(): Int {
        return if (isGuide) imageList.size else Int.MAX_VALUE
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val img = imageLoaderManage!!.display(container, imageList[getPosition(position)])
        img.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
        img.scaleType = ImageView.ScaleType.CENTER_CROP
        img.setOnClickListener { v: View ->
            if (imageClickListener != null) {
                imageClickListener!!.onBannerClick(v, getPosition(position), imageList[getPosition(position)])
            }
        }
        container.addView(img)
        return img
    }

    private fun getPosition(position: Int): Int {
        return position % imageList.size
    }

    fun setImageClickListener(imageClickListener: OnBannerImageClickListener) {
        this.imageClickListener = imageClickListener
    }

    init {
        if (imageLoaderManage == null) {
            val requestOptions = RequestOptions()
                    .placeholder(placeholderDrawable)
                    .error(errorDrawable)
                    .centerCrop()
            this.imageLoaderManage = object : ImageDisplayManager {
                override fun display(container: ViewGroup, model: BannerModelCallBack<*>?): ImageView {
                    val imageView = ImageView(container.context)
                    Glide.with(imageView.context)
                            .load(model?.bannerUrl)
                            .apply(requestOptions)
                            .into(imageView)
                    return imageView
                }
            }
        } else {
            this.imageLoaderManage = imageLoaderManage
        }
    }
}