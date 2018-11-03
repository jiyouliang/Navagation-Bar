package com.jiyouliang.freshema.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.jiyouliang.freshema.R;
import com.jiyouliang.freshema.view.DotsView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by JiYouLiang on 2018/10/28.
 */

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_HEADER = 0;

    private AppCompatActivity mActivity;

    public MainRecyclerViewAdapter(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_HEADER) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_main_header, parent, false);
            HeaderViewHolder headerViewHolder = new HeaderViewHolder(itemView);
            return headerViewHolder;
        } else {
            return null;
        }
    }

    //绑定viewHolder,该方法可以设置view数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position == VIEW_TYPE_HEADER){
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
            //ToolBar
            mActivity.setSupportActionBar(headerHolder.mToolbar);
            mActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
            //ViewPager Banner
            initViewPagerData(mActivity, headerHolder.mViewPager);
            setListener(headerHolder);
            initFlipperAnim(mActivity, headerHolder);
        }else{
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_HEADER;
        } else {
            return super.getItemViewType(position);
        }
    }


    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        private ViewPager mViewPager;
        private int mCurItem = 0;
        private boolean canExecuteTimer = true;
        private DotsView mDotsView;
        private ViewFlipper mViewFlipper;
        private Toolbar mToolbar;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            //初始化view
            mToolbar = itemView.findViewById(R.id.toolbar);
            mViewPager = itemView.findViewById(R.id.vp_banner);
            mDotsView = itemView.findViewById(R.id.dotsView);
            mViewFlipper = itemView.findViewById(R.id.view_flipper);

//        mViewPager.setAdapter(new );

//            initFlipperAnim();
        }
    }

    private void initViewPagerData(Context context, ViewPager viewPager) {
        List<Integer> datas = Arrays.asList(R.drawable.banner1, R.drawable.banner2, R.drawable.banner3, R.drawable.banner4, R.drawable.banner5);
        BannerAdapter mAdapter = new BannerAdapter(context, datas);
        viewPager.setAdapter(mAdapter);
    }

    private void setListener(final HeaderViewHolder holder) {
        holder.mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                holder.mCurItem = position;
                System.out.println("onPageSelected,pos=" + position);
                holder.mDotsView.setCurItem(position % holder.mDotsView.getDotCount());

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                System.out.println("onPageScrollStateChanged");
                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE://停止滑动
                        holder.canExecuteTimer = true;
                        break;
                    case ViewPager.SCROLL_STATE_DRAGGING://正在滑动中
                        holder.canExecuteTimer = false;
                        break;
                }
            }
        });
    }

    private void initFlipperAnim(Context context, HeaderViewHolder holder) {
        String[] flipperTitlesTip = context.getResources().getStringArray(R.array.flipperTitlesTip);
        String[] flipperTitles = context.getResources().getStringArray(R.array.flipperTitles);
        TypedArray a = context.getResources().obtainTypedArray(R.array.flipperRes);
        for (int i = 0; i < 3; i++) {

            View view = LayoutInflater.from(context).inflate(R.layout.view_flipper_item, null);
            ((TextView)view.findViewById(R.id.tv_flip_tip)).setText(flipperTitlesTip[i]+" | ");
            ((TextView)view.findViewById(R.id.tv_flip_title)).setText(flipperTitles[i]);
            ((ImageView)view.findViewById(R.id.iv_flip_product)).setBackgroundResource(a.getResourceId(i, R.drawable.flipper1));
            holder.mViewFlipper.addView(view);
        }
        a.recycle();
//        mViewFlipper.setFlipInterval(200);
//        mViewFlipper.startFlipping();
    }
}
