package com.wkz.progressbanner.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wkz.progressbanner.R;
import com.wkz.progressbanner.sample.simple.ImageManagerActivity;
import com.wkz.progressbanner.sample.simple.MethodTestActivity;
import com.wkz.progressbanner.sample.simple.ProgressActivity;
import com.wkz.progressbanner.sample.simple.RefreshActivity;
import com.wkz.progressbanner.sample.simple.SimpleActivity;
import com.wkz.progressbanner.sample.simple.SimpleGuideActivity;
import com.wkz.progressbanner.sample.simple.SimpleJavaActivity;
import com.wkz.progressbanner.sample.simple.TransformerActivity;
import com.wkz.progressbanner.sample.simple.issues.Issues10Activity;
import com.wkz.progressbanner.sample.simple.issues.Issues12Activity;

public final class MainActivity extends AppCompatActivity {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        this.findViewById(R.id.btn_refresh_simple).setOnClickListener((new View.OnClickListener() {
            public final void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RefreshActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MainActivity.this.startActivity(intent);
            }
        }));
        this.findViewById(R.id.btn_simple).setOnClickListener((new View.OnClickListener() {
            public final void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SimpleActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MainActivity.this.startActivity(intent);
            }
        }));
        this.findViewById(R.id.btn_guide).setOnClickListener((new View.OnClickListener() {
            public final void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SimpleGuideActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MainActivity.this.startActivity(intent);
            }
        }));
        this.findViewById(R.id.btn_image_manager).setOnClickListener((new View.OnClickListener() {
            public final void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ImageManagerActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MainActivity.this.startActivity(intent);
            }
        }));
        this.findViewById(R.id.btn_transformer).setOnClickListener((new View.OnClickListener() {
            public final void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TransformerActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MainActivity.this.startActivity(intent);
            }
        }));
        this.findViewById(R.id.btn_method_test).setOnClickListener((new View.OnClickListener() {
            public final void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MethodTestActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MainActivity.this.startActivity(intent);
            }
        }));
        this.findViewById(R.id.btn_issues_10).setOnClickListener((new View.OnClickListener() {
            public final void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Issues10Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MainActivity.this.startActivity(intent);
            }
        }));
        this.findViewById(R.id.btn_issues_12).setOnClickListener((new View.OnClickListener() {
            public final void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Issues12Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MainActivity.this.startActivity(intent);
            }
        }));
        this.findViewById(R.id.btn_java).setOnClickListener((new View.OnClickListener() {
            public final void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SimpleJavaActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MainActivity.this.startActivity(intent);
            }
        }));
        this.findViewById(R.id.btn_progress).setOnClickListener((new View.OnClickListener() {
            public final void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProgressActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MainActivity.this.startActivity(intent);
            }
        }));
    }
}
