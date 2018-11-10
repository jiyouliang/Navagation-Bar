package com.jiyouliang.freshema.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jiyouliang.freshema.R;

/**
 * Created by JiYouLiang on 2018/11/08.
 */
public class ChaoHeSuanViewHolder extends RecyclerView.ViewHolder {
    private TextView tvHour;
    private TextView tvMin;
    private TextView tvSec;
    public ChaoHeSuanViewHolder(View itemView) {
        super(itemView);
        tvHour = itemView.findViewById(R.id.tv_exp_hour);
        tvMin = itemView.findViewById(R.id.tv_exp_minute);
        tvSec = itemView.findViewById(R.id.tv_exp_second);
    }

    public TextView getTvHour() {
        return tvHour;
    }

    public TextView getTvMin() {
        return tvMin;
    }

    public TextView getTvSec() {
        return tvSec;
    }
}
