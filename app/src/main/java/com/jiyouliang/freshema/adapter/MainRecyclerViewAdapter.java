package com.jiyouliang.freshema.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.jiyouliang.freshema.R;
import com.jiyouliang.freshema.util.ViewUtil;
import com.jyl.view.CircleIndicatorViewPager;

import java.util.Arrays;
import java.util.List;

/**
 * Created by JiYouLiang on 2018/10/28.
 */

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NEW_USER_REC = 1;//新人专享

    private AppCompatActivity mActivity;
    private int mWidth;

    public MainRecyclerViewAdapter(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        if (viewType == TYPE_HEADER) {
            View itemView = LayoutInflater.from(context).inflate(R.layout.fragment_main_header, parent, false);
            int padding = ViewUtil.dp2Pix(context, 20);
            mWidth = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth() - padding;
            HeaderViewHolder headerViewHolder = new HeaderViewHolder(itemView);
            return headerViewHolder;
        } else {
            //新人专享
            View iv = LayoutInflater.from(context).inflate(R.layout.item_new_user_rec, parent, false);
            return new NewUserViewHolder(iv);
        }
    }


    //绑定viewHolder,该方法可以设置view数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == TYPE_HEADER) {
            bindHeader((HeaderViewHolder) holder);
        } else {
            //新人专享
            NewUserViewHolder newUserViewHolder = (NewUserViewHolder) holder;
            newUserViewHolder.iv.setBackgroundResource(R.drawable.new_user_rec);
        }
    }

    private void bindHeader(HeaderViewHolder holder) {
        HeaderViewHolder headerHolder = holder;
        //ToolBar
        mActivity.setSupportActionBar(headerHolder.mToolbar);
        mActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        //ViewPager Banner
        initViewPagerData(mActivity, headerHolder.mViewPager);
        setListener(headerHolder);
        initFlipperAnim(mActivity, headerHolder);
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else {
            return TYPE_NEW_USER_REC;
        }
    }


    private void initViewPagerData(Context context, CircleIndicatorViewPager viewPager) {
        List<Integer> datas = Arrays.asList(R.drawable.banner1, R.drawable.banner2, R.drawable.banner3, R.drawable.banner4, R.drawable.banner5);
        BannerAdapter mAdapter = new BannerAdapter(context, datas, mWidth);
        viewPager.setAdapter(mAdapter);
    }

    private void setListener(final HeaderViewHolder holder) {

    }

    private void initFlipperAnim(Context context, HeaderViewHolder holder) {
        String[] flipperTitlesTip = context.getResources().getStringArray(R.array.flipperTitlesTip);
        String[] flipperTitles = context.getResources().getStringArray(R.array.flipperTitles);
        TypedArray a = context.getResources().obtainTypedArray(R.array.flipperRes);
        for (int i = 0; i < 3; i++) {

            View view = LayoutInflater.from(context).inflate(R.layout.view_flipper_item, null);
            ((TextView) view.findViewById(R.id.tv_flip_tip)).setText(flipperTitlesTip[i] + " | ");
            ((TextView) view.findViewById(R.id.tv_flip_title)).setText(flipperTitles[i]);
            ((ImageView) view.findViewById(R.id.iv_flip_product)).setBackgroundResource(a.getResourceId(i, R.drawable.flipper1));
            holder.mViewFlipper.addView(view);
        }
        a.recycle();
//        mViewFlipper.setFlipInterval(200);
//        mViewFlipper.startFlipping();
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
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

//        mViewPager.setAdapter(new );

//            initFlipperAnim();
        }
    }

    /**
     * 新人专享
     */
    public static class NewUserViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv;

        public NewUserViewHolder(View itemView) {
            super(itemView);
            this.iv = itemView.findViewById(R.id.iv_uer_rec);
        }
    }
}
