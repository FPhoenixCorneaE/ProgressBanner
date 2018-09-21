package com.wkz.progressbanner.sample.refresh;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wkz.progressbanner.R;
import com.wkz.bannerlayout.listener.BannerModelCallBack;
import com.wkz.bannerlayout.listener.OnBannerClickListener;
import com.wkz.bannerlayout.widget.BannerLayout;
import com.wkz.progressbanner.sample.bean.SimpleBannerModel;
import com.wkz.progressbanner.sample.bean.SimpleData;

import java.util.Collection;
import java.util.List;

public final class RefreshAdapter extends RecyclerView.Adapter {
    private List bannerModels;
    private BannerLayout bannerLayout;
    private final List listModels;
    private static final int TYPE_BANNER = 0;
    private static final int TYPE_ITEM = 1;

    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder var10000;
        View var10003;
        switch (viewType) {
            case 0:
                var10003 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner, parent, false);
                var10000 = (RecyclerView.ViewHolder) (new BannerViewHolder(var10003));
                break;
            default:
                var10003 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
                var10000 = (RecyclerView.ViewHolder) (new ItemViewHolder(var10003));
        }

        return var10000;
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (this.getItemViewType(position)) {
            case 0:
                if (this.bannerModels == null) {
                    return;
                }

                final BannerViewHolder viewHolder = (BannerViewHolder) holder;
                this.bannerLayout = viewHolder.getBannerLayout();

                this.bannerLayout.initTips(true, true, true, true)
                        .setProgressesMargin(10)
                        .setProgressesSite(RelativeLayout.CENTER_HORIZONTAL)
                        .initListResources(this.bannerModels)
                        .setDelayTime(3000)
                        .switchBanner(true)
                        .setOnBannerClickListener((OnBannerClickListener) (new OnBannerClickListener() {
                            public void onBannerClick(@NonNull View view, int position, @NonNull BannerModelCallBack model) {
                                Toast.makeText(view.getContext(), (CharSequence) model.getBannerTitle(), Toast.LENGTH_LONG).show();
                            }

                            // $FF: synthetic method
                            // $FF: bridge method
                            public void onBannerClick(View var1, int var2, Object var3) {
                                this.onBannerClick(var1, var2, (SimpleBannerModel) var3);
                            }
                        }));
                viewHolder.getStart().setOnClickListener((View.OnClickListener) (new View.OnClickListener() {
                    public final void onClick(View it) {
                        viewHolder.getBannerLayout().switchBanner(true);
                    }
                }));
                viewHolder.getStop().setOnClickListener((View.OnClickListener) (new View.OnClickListener() {
                    public final void onClick(View it) {
                        viewHolder.getBannerLayout().switchBanner(false);
                    }
                }));
                viewHolder.getUpdate().setOnClickListener((View.OnClickListener) (new View.OnClickListener() {
                    public final void onClick(View it) {
                        List update = SimpleData.update();
                        RefreshAdapter.this.bannerModels = update;
                        access$getBannerLayout$p(RefreshAdapter.this).initListResources(update);
                    }
                }));
                break;
            case 1:
                if (this.listModels == null) {
                    return;
                }

                ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
                Glide.with(itemViewHolder.getImageView().getContext()).load(((ListModel) this.listModels.get(position - 1)).getTitleImage()).into((ImageView) itemViewHolder.getImageView());
                itemViewHolder.getTextView().setText((CharSequence) ((ListModel) this.listModels.get(position - 1)).getTitle());
        }

    }

    public int getItemCount() {
        return this.listModels == null ? 0 : this.listModels.size() + 1;
    }

    public int getItemViewType(int position) {
        return position == 0 ? 0 : 1;
    }

    public final void addAll(@NonNull List data) {
        if (this.listModels != null) {
            this.listModels.addAll((Collection) data);
            this.notifyDataSetChanged();
        }

    }

    public final void clear() {
        if (this.listModels != null) {
            this.listModels.clear();
            this.notifyDataSetChanged();
        }

    }

    public final void addBanner(@NonNull List bannerModels) {
        this.bannerModels = bannerModels;
    }

    public RefreshAdapter(@Nullable List listModels) {
        this.listModels = listModels;
    }

    // $FF: synthetic method
    @Nullable
    public static final List access$getBannerModels$p(RefreshAdapter $this) {
        return $this.bannerModels;
    }

    // $FF: synthetic method
    @NonNull
    public static final BannerLayout access$getBannerLayout$p(RefreshAdapter $this) {
        BannerLayout var10000 = $this.bannerLayout;

        return var10000;
    }

    // $FF: synthetic method
    public static final void access$setBannerLayout$p(RefreshAdapter $this, @NonNull BannerLayout var1) {
        $this.bannerLayout = var1;
    }

    private final class BannerViewHolder extends RecyclerView.ViewHolder {
        @NonNull
        private final BannerLayout bannerLayout;
        @NonNull
        private final Button start;
        @NonNull
        private final Button stop;
        @NonNull
        private final Button update;

        @NonNull
        public final BannerLayout getBannerLayout() {
            return this.bannerLayout;
        }

        @NonNull
        public final Button getStart() {
            return this.start;
        }

        @NonNull
        public final Button getStop() {
            return this.stop;
        }

        @NonNull
        public final Button getUpdate() {
            return this.update;
        }

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            View var10001 = itemView.findViewById(R.id.banner);
            this.bannerLayout = (BannerLayout) var10001;
            var10001 = itemView.findViewById(R.id.start);
            this.start = (Button) var10001;
            var10001 = itemView.findViewById(R.id.stop);
            this.stop = (Button) var10001;
            var10001 = itemView.findViewById(R.id.update);
            this.update = (Button) var10001;
        }
    }


    private final class ItemViewHolder extends RecyclerView.ViewHolder {
        @NonNull
        private final AppCompatImageView imageView;
        @NonNull
        private final AppCompatTextView textView;

        @NonNull
        public final AppCompatImageView getImageView() {
            return this.imageView;
        }

        @NonNull
        public final AppCompatTextView getTextView() {
            return this.textView;
        }

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            View var10001 = itemView.findViewById(R.id.list_image);
            this.imageView = (AppCompatImageView) var10001;
            var10001 = itemView.findViewById(R.id.list_tv);
            this.textView = (AppCompatTextView) var10001;
        }
    }

}
