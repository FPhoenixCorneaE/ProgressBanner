package com.wkz.bannerlayout.widget;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wkz.bannerlayout.listener.BannerModelCallBack;
import com.wkz.bannerlayout.listener.ImageDisplayManager;
import com.wkz.bannerlayout.listener.OnBannerImageClickListener;

import java.util.List;

public class BannerAdapter extends PagerAdapter {

    private ImageDisplayManager imageLoaderManage;
    private OnBannerImageClickListener imageClickListener;
    private List<BannerModelCallBack> imageList;
    private boolean isGuide;

    public int getCount() {
        return this.isGuide ? this.imageList.size() : Integer.MAX_VALUE;
    }

    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        ImageView img = imageLoaderManage.display(container, this.imageList.get(this.getPosition(position)));
        img.setLayoutParams(new BannerLayout.LayoutParams(BannerLayout.LayoutParams.MATCH_PARENT, BannerLayout.LayoutParams.MATCH_PARENT));
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (imageClickListener != null) {
                    imageClickListener.onBannerClick(v, getPosition(position), imageList.get(getPosition(position)));
                }

            }
        });
        container.addView((View) img);
        return img;
    }

    private int getPosition(int position) {
        return position % this.imageList.size();
    }

    public void setImageClickListener(@NonNull OnBannerImageClickListener imageClickListener) {
        this.imageClickListener = imageClickListener;
    }

    public BannerAdapter(@NonNull List imageList, @Nullable ImageDisplayManager imageLoaderManage, Drawable errorDrawable, Drawable placeholderDrawable, boolean isGuide) {
        super();
        this.imageList = imageList;
        this.isGuide = isGuide;
        if (imageLoaderManage == null) {
            final RequestOptions requestOptions = new RequestOptions()
                    .placeholder(placeholderDrawable)
                    .error(errorDrawable)
                    .centerCrop();
            this.imageLoaderManage = new ImageDisplayManager() {
                @NonNull
                public ImageView display(@NonNull ViewGroup container, @NonNull BannerModelCallBack model) {
                    ImageView imageView = new ImageView(container.getContext());
                    Glide.with(imageView.getContext())
                            .load(model.getBannerUrl())
                            .apply(requestOptions)
                            .into(imageView);
                    return imageView;
                }
            };
        } else {
            this.imageLoaderManage = imageLoaderManage;
        }

    }
}
