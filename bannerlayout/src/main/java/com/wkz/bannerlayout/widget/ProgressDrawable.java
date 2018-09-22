package com.wkz.bannerlayout.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.view.animation.LinearInterpolator;

public class ProgressDrawable extends Drawable implements Animatable {

    private float mWidth;
    private float mHeight;
    private long mDuration;
    @ColorInt
    private int mBackgroundColor;
    @ColorInt
    private int mProgressColor;
    private float mRadius;

    private Paint mPaint;
    private ValueAnimator mAnimator;
    private Animator.AnimatorListener mAnimatorListener;
    private ValueAnimator.AnimatorUpdateListener mAnimatorUpdateListener;
    private Context mContext;
    private boolean mIsAnimated;
    private float mProgressWidth;

    private ProgressDrawable(Builder builder) {
        this.mContext = builder.mContext;
        this.mWidth = builder.mWidth;
        this.mHeight = builder.mHeight;
        this.mDuration = builder.mDuration;
        this.mBackgroundColor = builder.mBackgroundColor;
        this.mProgressColor = builder.mProgressColor;
        this.mRadius = builder.mRadius;
        this.mIsAnimated = builder.mIsAnimated;
        this.mAnimatorListener = builder.mAnimatorListener;

        init();
    }

    private void init() {
        // 设置画笔参数
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        if (mIsAnimated) {
            // 设置属性动画参数
            mAnimator = new ValueAnimator();
            mAnimator = ValueAnimator.ofFloat(0f, 1f);
            mAnimator.setRepeatCount(0);
            mAnimator.setRepeatMode(ValueAnimator.RESTART);
            mAnimator.setInterpolator(new LinearInterpolator());
            mAnimator.setDuration(mDuration);
            // 设置动画的回调
            mAnimatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    calculateProgressWidth((float) animation.getAnimatedValue());
                    invalidateSelf();
                }
            };
        }
    }

    @Override
    public void start() {
        if (mAnimator != null) {
            mAnimator.removeAllListeners();
            mAnimator.removeAllUpdateListeners();
            if (mAnimatorListener != null) {
                mAnimator.addListener(mAnimatorListener);
            }
            if (mAnimatorUpdateListener != null) {
                mAnimator.addUpdateListener(mAnimatorUpdateListener);
            }
            if (mAnimator.isPaused()) {
                mAnimator.resume();
            } else {
                mAnimator.start();
            }
        }
    }

    @Override
    public void stop() {
        if (mAnimator != null && mAnimator.isRunning()) {
            mAnimator.pause();
        }
    }

    public void end() {
        if (mAnimator != null) {
            mAnimator.end();
            mProgressWidth = 0;
            invalidateSelf();
        }
    }

    @Override
    public boolean isRunning() {
        return mAnimator != null && mAnimator.isRunning();
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        // 绘制背景
        mPaint.setColor(mBackgroundColor);
        canvas.drawRoundRect(new RectF(0, 0, mWidth, mHeight),
                mRadius, mRadius, mPaint);

        // 绘制进度
        if (mIsAnimated) {
            mPaint.setColor(mProgressColor);
            canvas.drawRoundRect(new RectF(0, 0, mProgressWidth, mHeight),
                    mRadius, mRadius, mPaint);
        }
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    // 获取默认的高度
    @Override
    public int getIntrinsicHeight() {
        return (int) this.mHeight;
    }

    // 获取默认的宽度
    @Override
    public int getIntrinsicWidth() {
        return (int) this.mWidth;
    }

    private void calculateProgressWidth(float animatedValue) {
        this.mProgressWidth = mWidth * animatedValue;
    }

    public static class Builder {

        private static final float DEFAULT_WIDTH = 50.0F;
        private static final float DEFAULT_HEIGHT = 2.5F;
        private static final long DEFAULT_DURATION = 3000;
        private static final int DEFAULT_BACKGROUND_COLOR = Color.RED;
        private static final int DEFAULT_PROGRESS_COLOR = Color.WHITE;

        private Context mContext;
        private float mWidth;
        private float mHeight;
        private long mDuration;
        @ColorInt
        private int mBackgroundColor;
        @ColorInt
        private int mProgressColor;
        private float mRadius;
        private boolean mIsAnimated;
        private Animator.AnimatorListener mAnimatorListener;

        public Builder(Context mContext) {
            this.mContext = mContext;
            this.mWidth = dp2px(DEFAULT_WIDTH);
            this.mHeight = dp2px(DEFAULT_HEIGHT);
            this.mBackgroundColor = DEFAULT_BACKGROUND_COLOR;
            this.mProgressColor = DEFAULT_PROGRESS_COLOR;
            this.mDuration = DEFAULT_DURATION;
        }

        public float getWidth() {
            return mWidth;
        }

        public Builder setWidth(float dpValue) {
            this.mWidth = dp2px(dpValue);
            return this;
        }

        public float getHeight() {
            return mHeight;
        }

        public Builder setHeight(float dpValue) {
            this.mHeight = dp2px(dpValue);
            return this;
        }

        public long getDuration() {
            return mDuration;
        }

        public Builder setDuration(long mDuration) {
            this.mDuration = mDuration;
            return this;
        }

        public int getBackgroundColor() {
            return mBackgroundColor;
        }

        public Builder setBackgroundColor(@ColorInt int mBackgroundColor) {
            this.mBackgroundColor = mBackgroundColor;
            return this;
        }

        public int getProgressColor() {
            return mProgressColor;
        }

        public Builder setProgressColor(@ColorInt int mProgressColor) {
            this.mProgressColor = mProgressColor;
            return this;
        }

        public float getRadius() {
            return mRadius;
        }

        public Builder setRadius(float dpValue) {
            this.mRadius = dp2px(dpValue);
            return this;
        }

        public boolean isAnimated() {
            return mIsAnimated;
        }

        public Builder setAnimated(boolean mIsAnimated) {
            this.mIsAnimated = mIsAnimated;
            return this;
        }

        public Animator.AnimatorListener getAnimatorListener() {
            return mAnimatorListener;
        }

        public Builder setAnimatorListener(Animator.AnimatorListener mAnimatorListener) {
            this.mAnimatorListener = mAnimatorListener;
            return this;
        }

        private float dp2px(float dpValue) {
            float scale = mContext.getResources().getDisplayMetrics().density;
            return dpValue * scale;
        }

        public ProgressDrawable build() {
            return new ProgressDrawable(this);
        }
    }
}
