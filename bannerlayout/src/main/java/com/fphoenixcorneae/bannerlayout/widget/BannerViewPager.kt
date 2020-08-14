package com.fphoenixcorneae.bannerlayout.widget

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.fphoenixcorneae.transformer.VerticalTransformer

class BannerViewPager : ViewPager {
    private var mViewTouchMode = false
    private var isVertical = false
    private var scroller: FixedSpeedScroller? = null
    val duration: Int
        get() = scroller?.fixDuration ?: 2000

    fun setViewTouchMode(b: Boolean): BannerViewPager {
        if (b && !this.isFakeDragging) {
            beginFakeDrag()
        } else if (!b && this.isFakeDragging) {
            endFakeDrag()
        }
        mViewTouchMode = b
        return this
    }

    fun setVertical(vertical: Boolean): BannerViewPager {
        isVertical = vertical
        if (isVertical) {
            this.setPageTransformer(true, VerticalTransformer())
        }
        return this
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        try {
            val mFirstLayout = ViewPager::class.java.getDeclaredField("mFirstLayout")
            mFirstLayout.isAccessible = true
            mFirstLayout[this] = false
        } catch (var2: Exception) {
            var2.printStackTrace()
        }
    }

    override fun onDetachedFromWindow() {
        val context = this.context
        if (context == null) {
            throw ClassCastException("null cannot be cast to non-null type android.app.Activity")
        } else {
            if ((context as Activity).isFinishing) {
                super.onDetachedFromWindow()
            }
        }
    }

    /**
     * When mViewTouchMode is true, ViewPager does not intercept the click event, and the click event will be handled by the childView
     */
    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return if (isVertical) !mViewTouchMode && super.onInterceptTouchEvent(swapEvent(event)) else !mViewTouchMode && super.onInterceptTouchEvent(event)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return if (isVertical) super.onTouchEvent(swapEvent(ev)) else super.onTouchEvent(ev)
    }

    /**
     * In the mViewTouchMode true or sliding direction is not about time, ViewPager will give up control of click events,
     * This is conducive to the ListView in the ListView can be added, such as sliding control, or sliding between the two will have a conflict
     */
    override fun arrowScroll(direction: Int): Boolean {
        return !mViewTouchMode && (direction == View.FOCUS_LEFT || direction == View.FOCUS_RIGHT) && super.arrowScroll(direction)
    }

    /**
     * Set the switching speed
     */
    fun setDuration(duration: Int): BannerViewPager {
        try {
            val mScroller = ViewPager::class.java.getDeclaredField("mScroller")
            mScroller.isAccessible = true
            scroller = FixedSpeedScroller(context)
            mScroller[this] = scroller
            scroller!!.duration = duration
        } catch (var3: Exception) {
            Log.i(this.javaClass.simpleName, var3.message ?: "")
        }
        return this
    }

    private fun swapEvent(event: MotionEvent): MotionEvent {
        val width = this.width.toFloat()
        val height = this.height.toFloat()
        val swappedX = event.y / height * width
        val swappedY = event.x / width * height
        event.setLocation(swappedX, swappedY)
        return event
    }

    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}
}