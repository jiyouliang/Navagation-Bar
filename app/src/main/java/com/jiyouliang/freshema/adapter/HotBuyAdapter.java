package com.jiyouliang.freshema.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiyouliang.freshema.R;
import com.jiyouliang.freshema.model.HotBuyInfo;

import java.util.List;

/**
 * 必买清单
 * Created by JiYouLiang on 2018/11/09.
 */
public class HotBuyAdapter extends RecyclerView.Adapter {
    private List<HotBuyInfo> mData;

    public HotBuyAdapter(List<HotBuyInfo> data) {
        this.mData = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hot_buy_product, parent, false);
        return new HotBuyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HotBuyInfo info = mData.get(position);
        HotBuyViewHolder h = (HotBuyViewHolder) holder;
        h.ivHotBuy.setBackgroundResource(info.getResId());
        h.tvHotTitle.setText(info.getTitle());
        h.tvHotSubTitle.setText(info.getSubTitle());
        h.tvHotAttr1.setText(info.getAtt1());
        h.tvHotSubAttr1.setText(info.getSubAtt1());
        h.tvHotAttr2.setText(info.getAtt2());
        h.tvHotSubAttr2.setText(info.getSubAtt2());
        h.tvHotPrice.setText(Html.fromHtml("<font color=\"#FF0000\">" + info.getPrice() + "</font><fontcolor=\"#7A7A7A\"> /盒</font>"));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class HotBuyViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivHotBuy;
        private TextView tvHotTitle;
        private TextView tvHotSubTitle;
        private TextView tvHotAttr1;
        private TextView tvHotSubAttr1;
        private TextView tvHotAttr2;
        private TextView tvHotSubAttr2;
        private TextView tvHotPrice;

        public HotBuyViewHolder(View item) {
            super(item);
            ivHotBuy = item.findViewById(R.id.iv_hot_prod);
            tvHotTitle = item.findViewById(R.id.tv_hot_title);
            tvHotSubTitle = item.findViewById(R.id.tv_hot_subtitle);
            tvHotAttr1 = item.findViewById(R.id.tv_hot_attr1);
            tvHotSubAttr1 = item.findViewById(R.id.tv_hot_subattr1);
            tvHotAttr2 = item.findViewById(R.id.tv_hot_attr2);
            tvHotSubAttr2 = item.findViewById(R.id.tv_hot_subattr2);
            tvHotPrice = item.findViewById(R.id.tv_hot_price);
        }
    }
}
