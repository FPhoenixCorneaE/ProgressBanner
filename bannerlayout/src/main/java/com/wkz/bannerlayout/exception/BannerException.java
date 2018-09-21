package com.wkz.bannerlayout.exception;

import android.support.annotation.NonNull;

public class BannerException extends RuntimeException {

    public BannerException(@NonNull String s) {
        super(s);
    }
}
