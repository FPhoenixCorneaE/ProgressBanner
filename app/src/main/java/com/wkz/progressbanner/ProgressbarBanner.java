package com.wkz.progressbanner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.wkz.bannerlayout.widget.ProgressDrawable;

import java.util.ArrayList;
import java.util.List;

public class ProgressbarBanner extends FrameLayout {

    private Context mContext;
    private RecyclerView mRecyclerView;
    private List<String> mImageUrls;
    private List<ImageView> mIndicatorViews;
    private List<ProgressDrawable> mIndicatorDrawables;
    private Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    public ProgressbarBanner(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public ProgressbarBanner(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ProgressbarBanner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @SuppressLint("NewApi")
    public ProgressbarBanner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    public void initView(Context context) {
        this.mContext = context;
        mIndicatorViews = new ArrayList<>();
        mIndicatorDrawables = new ArrayList<>();
    }

    public void setImageUrls(@NonNull List<String> imageUrls) {
        if (imageUrls.isEmpty()) {
            return;
        }
        this.mImageUrls = imageUrls;
        for (int i = 0; i < mImageUrls.size(); i++) {

        }
    }
}
