package com.wkz.progressbanner.sample.refresh;

import android.support.annotation.NonNull;

import com.wkz.progressbanner.sample.bean.SimpleBannerModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public final class ArrayUtils {

    @NonNull
    public final List initArrayResources(@NonNull Object[] imageArray) {
        ArrayList imageArrayList = new ArrayList();
        Iterator var4 = Arrays.asList(Arrays.copyOf(imageArray, imageArray.length)).iterator();

        while(var4.hasNext()) {
            Object url = var4.next();
            imageArrayList.add(new SimpleBannerModel(url));
        }

        return (List)imageArrayList;
    }

    @NonNull
    public static final List initArrayResources(@NonNull Object[] imageArray, @NonNull String[] imageArrayTitle) {
        List url = Arrays.asList(Arrays.copyOf(imageArray, imageArray.length));
        List title = Arrays.asList((String[])Arrays.copyOf(imageArrayTitle, imageArrayTitle.length));
        ArrayList imageArrayList = new ArrayList();
        int i = 0;

        for(int var8 = ((Collection)url).size(); i < var8; ++i) {
            SimpleBannerModel bannerModel = new SimpleBannerModel();
            bannerModel.setImage(url.get(i));
            bannerModel.setTitle((String)title.get(i));
            imageArrayList.add(bannerModel);
        }

        return (List)imageArrayList;
    }

}
