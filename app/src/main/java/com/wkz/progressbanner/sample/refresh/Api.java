package com.wkz.progressbanner.sample.refresh;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public final class Api {
    @NonNull
    public static final String ZL_BASE_API = "https://zhuanlan.zhihu.com/api/";

    public interface ZLService {
        @GET("columns/{suffix}/posts")
        @NonNull
        Observable<List<ListModel>> getList(@Path("suffix") @NonNull String var1, @Query("limit") int var2, @Query("offset") int var3);
    }
}
