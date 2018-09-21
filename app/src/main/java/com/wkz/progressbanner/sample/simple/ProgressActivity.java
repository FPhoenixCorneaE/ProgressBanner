package com.wkz.progressbanner.sample.simple;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.wkz.bannerlayout.widget.ProgressDrawable;
import com.wkz.progressbanner.R;

public class ProgressActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private ImageView mProgressIv;
    private Button mStartBtn;
    private Button mStopBtn;
    private ProgressDrawable mProgressDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        mContext = this;

        initView();
    }

    private void initView() {
        mProgressIv = (ImageView) findViewById(R.id.iv_progress);


        mProgressIv.setImageDrawable(mProgressDrawable = new ProgressDrawable.Builder(mContext)
                .setWidth(100f)
                .setHeight(8f)
                .setDuration(3000)
                .setBackgroundColor(Color.RED)
                .setProgressColor(Color.GREEN)
                .setRadius(10f)
                .setAnimated(true)
                .setAnimatorListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        Toast.makeText(mContext, "动画开始", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Toast.makeText(mContext, "动画结束", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        Toast.makeText(mContext, "动画取消", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        Toast.makeText(mContext, "动画重复", Toast.LENGTH_SHORT).show();
                    }
                })
                .build());
        mStartBtn = (Button) findViewById(R.id.btn_start);
        mStartBtn.setOnClickListener(this);
        mStopBtn = (Button) findViewById(R.id.btn_stop);
        mStopBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                mProgressDrawable.start();
                break;
            case R.id.btn_stop:
                mProgressDrawable.stop();
                break;
            default:
                break;
        }
    }
}
