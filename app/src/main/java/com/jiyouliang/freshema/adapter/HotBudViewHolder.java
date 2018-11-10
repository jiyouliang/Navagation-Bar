package com.jiyouliang.freshema.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.HorizontalScrollView;

import com.jiyouliang.freshema.R;

/**
 * Created by JiYouLiang on 2018/11/09.
 */
public class HotBudViewHolder extends RecyclerView.ViewHolder {
    private RecyclerView rvHotBuy;

    public HotBudViewHolder(View itemView) {
        super(itemView);
        this.rvHotBuy = itemView.findViewById(R.id.rv_hot_buy);
    }

    public RecyclerView getHotBuyRecyclerView() {
        return rvHotBuy;
    }
}
