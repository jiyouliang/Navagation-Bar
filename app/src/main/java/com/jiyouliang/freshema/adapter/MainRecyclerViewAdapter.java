package com.jiyouliang.freshema.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiyouliang.freshema.R;
import com.jiyouliang.freshema.model.HotBuyInfo;
import com.jiyouliang.freshema.util.ViewUtil;
import com.jyl.view.CircleIndicatorViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by JiYouLiang on 2018/10/28.
 */

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NEW_USER_REC = 1;//新人专享
    private static final int TYPE_HOT_BUY = 2;//必买
    private static final int TYPE_CHAO_HE_SUAN = 3;//超合算
    private static final int TYPE_OTHER = 100;//其他类型

    private AppCompatActivity mActivity;
    private int mWidth;

    public MainRecyclerViewAdapter(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        switch (viewType) {
            case TYPE_HEADER:
                View itemView = LayoutInflater.from(context).inflate(R.layout.fragment_main_header, parent, false);
                int padding = ViewUtil.dp2Pix(context, 20);
                mWidth = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth() - padding;
                HeaderViewHolder headerViewHolder = new HeaderViewHolder(itemView);
                return headerViewHolder;
            case TYPE_NEW_USER_REC:
                //新人专享
                View iv = LayoutInflater.from(context).inflate(R.layout.item_new_user_rec, parent, false);
                return new NewUserViewHolder(iv);
            case TYPE_CHAO_HE_SUAN:
                View itemView2 = LayoutInflater.from(context).inflate(R.layout.item_chao_he_suan, parent, false);
                return new ChaoHeSuanViewHolder(itemView2);
            case TYPE_HOT_BUY:
                View itemView3 = LayoutInflater.from(context).inflate(R.layout.item_hot_buy, parent, false);
                return new HotBudViewHolder(itemView3);
            default:
                return null;
        }
    }


    //绑定viewHolder,该方法可以设置view数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) {
            bindHeader((HeaderViewHolder) holder);
        } else if (getItemViewType(position) == TYPE_NEW_USER_REC) {
            //新人专享
            NewUserViewHolder newUserViewHolder = (NewUserViewHolder) holder;
            newUserViewHolder.getImageView().setBackgroundResource(R.drawable.new_user_rec);
        } else if (getItemViewType(position) == TYPE_CHAO_HE_SUAN) {
            bindChaoHesuan((ChaoHeSuanViewHolder) holder);
        } else if (getItemViewType(position) == TYPE_HOT_BUY) {
            //必买清单
            bindHotBuy((HotBudViewHolder) holder);
        }
    }

    private void bindHotBuy(HotBudViewHolder holder) {
        List<HotBuyInfo> data = new ArrayList<HotBuyInfo>();
        RecyclerView recyclerView = holder.getHotBuyRecyclerView();
        String[] hotBuyTitles = mActivity.getResources().getStringArray(R.array.hotBuyTitles);
        String[] hotBuySubTitles = mActivity.getResources().getStringArray(R.array.hotBuySubTitles);
        String[] hotBuyAttr1 = mActivity.getResources().getStringArray(R.array.hotBuyAttr1);
        String[] hotBuySubAttr1 = mActivity.getResources().getStringArray(R.array.hotBuySubAttr1);
        String[] hotBuyAttr2 = mActivity.getResources().getStringArray(R.array.hotBuyAttr2);
        String[] hotBuySubAttr2 = mActivity.getResources().getStringArray(R.array.hotBuySubAttr2);
        String[] hotBurPrices = mActivity.getResources().getStringArray(R.array.hotBurPrices);

        int[] hostButList = new int[]{R.drawable.hot_buy_item1, R.drawable.hot_buy_item2, R.drawable.hot_buy_item3, R.drawable.hot_buy_item4, R.drawable.hot_buy_item5};
        for (int i = 0; i < hostButList.length; i++) {
            HotBuyInfo info = new HotBuyInfo();
            info.setResId(hostButList[i]);
            info.setTitle(hotBuyTitles[i]);
            info.setSubTitle(hotBuySubTitles[i]);
            info.setAtt1(hotBuyAttr1[i]);
            info.setSubAtt1(hotBuySubAttr1[i]);
            info.setAtt2(hotBuyAttr2[i]);
            info.setSubAtt2(hotBuySubAttr2[i]);
            info.setPrice(hotBurPrices[i]);
            data.add(info);
        }

        LinearLayoutManager lm = new LinearLayoutManager(mActivity);
        lm.setOrientation(LinearLayoutManager.HORIZONTAL);
        //divicer
        RecyclerView.ItemDecoration decor = new DividerItemDecoration(mActivity, lm.getOrientation());
        ((DividerItemDecoration) decor).setDrawable(mActivity.getResources().getDrawable(R.drawable.hot_buy_item_divider));
        recyclerView.addItemDecoration(decor);
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(new HotBuyAdapter(data));
    }

    /**
     * 超合算
     *
     * @param holder
     */
    private void bindChaoHesuan(final ChaoHeSuanViewHolder holder) {
        Timer timer = new Timer();
        final Date date = new Date();
        date.setHours(12);
        date.setMinutes(1);
        date.setSeconds(8);


        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        date.setSeconds(date.getSeconds() - 1);
                        holder.getTvSec().setText("" + date.getSeconds());
                        holder.getTvMin().setText("" + date.getMinutes());
                        holder.getTvHour().setText("" + date.getHours());
                    }
                });
            }
        }, 1000, 1000);
    }

    private void bindHeader(HeaderViewHolder holder) {
        HeaderViewHolder headerHolder = holder;
        //ToolBar
        mActivity.setSupportActionBar(headerHolder.getToolbar());
        mActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        //ViewPager Banner
        initViewPagerData(mActivity, headerHolder.getViewPager());
        setListener(headerHolder);
        initFlipperAnim(mActivity, headerHolder);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else if (position == 1) {
            return TYPE_NEW_USER_REC;
        } else if (position == 2) {
            return TYPE_HOT_BUY;
        } else if (position == 3) {
            return TYPE_CHAO_HE_SUAN;
        } else {
            return TYPE_OTHER;
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
            holder.getViewFlipper().addView(view);
        }
        a.recycle();
    }
}
