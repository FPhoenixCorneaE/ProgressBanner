package com.fphoenixcorneae.bannerlayout.widget

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.view.animation.LinearInterpolator
import androidx.annotation.ColorInt
import com.fphoenixcorneae.bannerlayout.annotation.ProgressShapeMode

class ProgressDrawable private constructor(builder: Builder) : Drawable(), Animatable {
    private val mWidth: Float
    private val mHeight: Float
    private val mDuration: Long

    @ColorInt
    private val mBackgroundColor: Int

    @ColorInt
    private val mProgressColor: Int
    private val mRadius: Float

    @ProgressShapeMode
    private val mShapeMode: Int
    private val mRingThickness: Float
    private var mPaint: Paint? = null
    private var mAnimator: ValueAnimator? = null
    private val mAnimatorListener: Animator.AnimatorListener?
    private var mAnimatorUpdateListener: ValueAnimator.AnimatorUpdateListener? = null
    private val mContext: Context
    private val mIsAnimated: Boolean
    private var mProgressWidth = 0f
    private var mProgress = 0f
    private fun init() {
        // 设置画笔参数
        mPaint = Paint()
        mPaint!!.style = Paint.Style.FILL
        mPaint!!.isAntiAlias = true

        //是否动画
        if (mIsAnimated) {
            // 设置属性动画参数
            mAnimator = ValueAnimator()
            mAnimator = ValueAnimator.ofFloat(0f, 1f)
            mAnimator!!.repeatCount = 0
            mAnimator!!.repeatMode = ValueAnimator.RESTART
            mAnimator!!.interpolator = LinearInterpolator()
            mAnimator!!.duration = mDuration
            // 设置动画的回调
            mAnimatorUpdateListener = ValueAnimator.AnimatorUpdateListener { animation: ValueAnimator ->
                mProgress = animation.animatedValue as Float
                calculateProgressWidth()
                invalidateSelf()
            }
        }
    }

    override fun start() {
        if (mAnimator != null) {
            mAnimator!!.removeAllListeners()
            mAnimator!!.removeAllUpdateListeners()
            if (mAnimatorListener != null) {
                mAnimator!!.addListener(mAnimatorListener)
            }
            if (mAnimatorUpdateListener != null) {
                mAnimator!!.addUpdateListener(mAnimatorUpdateListener)
            }
            if (mAnimator!!.isPaused) {
                mAnimator!!.resume()
            } else {
                mAnimator!!.start()
            }
        }
    }

    override fun stop() {
        if (mAnimator != null && mAnimator!!.isRunning) {
            mAnimator!!.pause()
        }
    }

    fun end() {
        if (mAnimator != null) {
            mAnimator!!.end()
            mProgress = 0f
            mProgressWidth = 0f
            invalidateSelf()
        }
    }

    override fun isRunning(): Boolean {
        return mAnimator != null && mAnimator!!.isRunning
    }

    override fun draw(canvas: Canvas) {
        if (ProgressShapeMode.RECTANGLE == mShapeMode) {
            // 绘制背景
            mPaint!!.color = mBackgroundColor
            canvas.drawRoundRect(RectF(0f, 0f, mWidth, mHeight),
                    mRadius, mRadius, mPaint!!)

            // 绘制进度
            if (mIsAnimated) {
                mPaint!!.color = mProgressColor
                canvas.drawRoundRect(RectF(0f, 0f, mProgressWidth, mHeight),
                        mRadius, mRadius, mPaint!!)
            }
        } else if (ProgressShapeMode.RING == mShapeMode) {
            // 绘制背景
            mPaint!!.color = mBackgroundColor
            canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2, mPaint!!)

            // 绘制进度
            mPaint!!.color = mProgressColor
            canvas.drawArc(RectF(0f, 0f, mWidth, mHeight), -90f, 360 * mProgress, true, mPaint!!)

            // 绘制空心圆
            mPaint!!.color = Color.WHITE
            canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2 - mRingThickness, mPaint!!)
        }
    }

    override fun setAlpha(alpha: Int) {
        mPaint!!.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        mPaint!!.colorFilter = colorFilter
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    /**
     * 获取默认的高度
     */
    override fun getIntrinsicHeight(): Int {
        return mHeight.toInt()
    }

    /**
     * 获取默认的宽度
     */
    override fun getIntrinsicWidth(): Int {
        return mWidth.toInt()
    }

    private fun calculateProgressWidth() {
        mProgressWidth = mWidth * mProgress
    }

    class Builder(val mContext: Context) {
        var animatorListener: Animator.AnimatorListener? = null
            private set
        var isAnimated: Boolean
            private set
        var width: Float
            private set
        var height: Float
            private set
        var duration: Long
            private set

        @ColorInt
        var backgroundColor: Int
            private set

        @ColorInt
        var progressColor: Int
            private set
        var radius: Float
            private set

        @ProgressShapeMode
        var shapeMode: Int
            private set
        var ringThickness: Float
            private set

        fun setAnimated(mIsAnimated: Boolean): Builder {
            isAnimated = mIsAnimated
            return this
        }

        fun setAnimatorListener(mAnimatorListener: Animator.AnimatorListener?): Builder {
            animatorListener = mAnimatorListener
            return this
        }

        fun setWidth(dpValue: Float): Builder {
            width = dp2px(dpValue)
            return this
        }

        fun setHeight(dpValue: Float): Builder {
            height = dp2px(dpValue)
            return this
        }

        fun setDuration(mDuration: Long): Builder {
            duration = mDuration
            return this
        }

        fun setBackgroundColor(@ColorInt mBackgroundColor: Int): Builder {
            backgroundColor = mBackgroundColor
            return this
        }

        fun setProgressColor(@ColorInt mProgressColor: Int): Builder {
            progressColor = mProgressColor
            return this
        }

        fun setRadius(dpValue: Float): Builder {
            radius = dp2px(dpValue)
            return this
        }

        fun setShapeMode(@ProgressShapeMode mShapeMode: Int): Builder {
            shapeMode = mShapeMode
            return this
        }

        fun setRingThickness(mRingThickness: Float): Builder {
            ringThickness = mRingThickness
            return this
        }

        private fun dp2px(dpValue: Float): Float {
            val scale = mContext.resources.displayMetrics.density
            return dpValue * scale
        }

        fun build(): ProgressDrawable {
            return ProgressDrawable(this)
        }

        companion object {
            private const val DEFAULT_IS_ANIMATED = true
            private const val DEFAULT_WIDTH = 50.0f
            private const val DEFAULT_HEIGHT = 2.5f
            private const val DEFAULT_DURATION: Long = 3000
            private const val DEFAULT_BACKGROUND_COLOR = Color.RED
            private const val DEFAULT_PROGRESS_COLOR = Color.WHITE
            private const val DEFAULT_RADIUS = 10f
            private val DEFAULT_SHAPE_MODE: Int = ProgressShapeMode.RECTANGLE
            private const val DEFAULT_RING_THICKNESS = 2.5f
        }

        init {
            isAnimated = DEFAULT_IS_ANIMATED
            width = dp2px(DEFAULT_WIDTH)
            height = dp2px(DEFAULT_HEIGHT)
            duration = DEFAULT_DURATION
            backgroundColor = DEFAULT_BACKGROUND_COLOR
            progressColor = DEFAULT_PROGRESS_COLOR
            radius = dp2px(DEFAULT_RADIUS)
            shapeMode = DEFAULT_SHAPE_MODE
            ringThickness = dp2px(DEFAULT_RING_THICKNESS)
        }
    }

    init {
        mContext = builder.mContext
        mAnimatorListener = builder.animatorListener
        mIsAnimated = builder.isAnimated
        mWidth = builder.width
        mHeight = builder.height
        mDuration = builder.duration
        mBackgroundColor = builder.backgroundColor
        mProgressColor = builder.progressColor
        mRadius = builder.radius
        mShapeMode = builder.shapeMode
        mRingThickness = builder.ringThickness
        init()
    }
}