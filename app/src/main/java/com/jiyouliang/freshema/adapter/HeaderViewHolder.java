package com.jiyouliang.freshema.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ViewFlipper;

import com.jiyouliang.freshema.R;
import com.jyl.view.CircleIndicatorViewPager;

/**
 * Created by JiYouLiang on 2018/11/08.
 */
public class HeaderViewHolder extends RecyclerView.ViewHolder {
    private CircleIndicatorViewPager mViewPager;
    private int mCurItem = 0;
    private boolean canExecuteTimer = true;
    private ViewFlipper mViewFlipper;
    private Toolbar mToolbar;

    public HeaderViewHolder(View itemView) {
        super(itemView);
        //初始化view

        mToolbar = itemView.findViewById(R.id.toolbar);
        mViewPager = itemView.findViewById(R.id.civp_banner);
        mViewFlipper = itemView.findViewById(R.id.view_flipper);
    }

    public CircleIndicatorViewPager getViewPager() {
        return mViewPager;
    }

    public int getCurItem() {
        return mCurItem;
    }

    public boolean isCanExecuteTimer() {
        return canExecuteTimer;
    }

    public ViewFlipper getViewFlipper() {
        return mViewFlipper;
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }
}