package com.jiyouliang.freshema.adapter;

/**
 * Created by JiYouLiang on 2018/11/08.
 */

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.jiyouliang.freshema.R;

/**
 * 新人专享
 */
public class NewUserViewHolder extends RecyclerView.ViewHolder {

    private ImageView iv;

    public NewUserViewHolder(View itemView) {
        super(itemView);
        this.iv = itemView.findViewById(R.id.iv_uer_rec);
    }

    public ImageView getImageView(){
        return iv;
    }
}