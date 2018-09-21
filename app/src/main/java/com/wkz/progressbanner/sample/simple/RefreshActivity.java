package com.wkz.progressbanner.sample.simple;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.wkz.progressbanner.R;
import com.wkz.progressbanner.sample.bean.SimpleBannerModel;
import com.wkz.progressbanner.sample.bean.SimpleData;
import com.wkz.progressbanner.sample.refresh.Api;
import com.wkz.progressbanner.sample.refresh.RefreshAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.network.RxNetWork;
import io.reactivex.network.RxNetWorkListener;

public final class RefreshActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, RxNetWorkListener, Runnable {
    private SwipeRefreshLayout swipeRefreshLayout;
    private RefreshAdapter adapter;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_refresh);
        View var10001 = this.findViewById(R.id.refresh_layout);
        this.swipeRefreshLayout = (SwipeRefreshLayout) var10001;
        RecyclerView recyclerView = (RecyclerView) this.findViewById(R.id.recyclerView);
        SwipeRefreshLayout var10000 = this.swipeRefreshLayout;

        var10000.setOnRefreshListener((SwipeRefreshLayout.OnRefreshListener) this);
        var10000 = this.swipeRefreshLayout;

        var10000.post((Runnable) this);
        this.adapter = new RefreshAdapter((List) (new ArrayList()));
        recyclerView.setLayoutManager((RecyclerView.LayoutManager) (new LinearLayoutManager(this.getApplicationContext())));
        RefreshAdapter var3 = this.adapter;

        recyclerView.setAdapter((RecyclerView.Adapter) var3);
    }

    public void onRefresh() {
        RxNetWork.getInstance().cancel("RxNetWork");
        RxNetWork.getInstance()
                .setBaseUrl("https://zhuanlan.zhihu.com/api/")
                .getApi("RxNetWork",
                        ((Api.ZLService) RxNetWork.observable(Api.ZLService.class)).getList("daily", 20, 0),
                        (RxNetWorkListener) this);
    }

    public void onNetWorkStart() {
        SwipeRefreshLayout var10000 = this.swipeRefreshLayout;

        var10000.setRefreshing(true);
    }

    public void onNetWorkError(@NonNull Throwable e) {
        SwipeRefreshLayout var10000 = this.swipeRefreshLayout;

        var10000.setRefreshing(false);
        Toast.makeText(this.getApplicationContext(), (CharSequence) "net work error", Toast.LENGTH_SHORT).show();
    }

    public void onNetWorkComplete() {
        SwipeRefreshLayout var10000 = this.swipeRefreshLayout;

        var10000.setRefreshing(false);
    }

    @Override
    public void onNetWorkSuccess(Object data) {
        RefreshAdapter var10000 = this.adapter;

        var10000.clear();
        var10000 = this.adapter;

        var10000.addBanner(SimpleData.initModel());
        var10000 = this.adapter;

        var10000.addAll((List) data);
    }

    protected void onDestroy() {
        super.onDestroy();
        RxNetWork.getInstance().cancel("RxNetWork");
    }

    public void run() {
        this.onRefresh();
    }
}