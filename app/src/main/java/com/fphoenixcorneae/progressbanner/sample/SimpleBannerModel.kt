package com.fphoenixcorneae.progressbanner.sample

import com.fphoenixcorneae.bannerlayout.listener.BannerModelCallBack

class SimpleBannerModel : BannerModelCallBack<String?> {

    var image: Any? = null

    override var bannerTitle: String? = null

    override val bannerUrl: String
        get() = image.toString()

    constructor(image: Any) : super() {
        this.image = image
    }

    constructor(image: Any, title: String) : super() {
        this.image = image
        bannerTitle = title
    }

    constructor() {}
}