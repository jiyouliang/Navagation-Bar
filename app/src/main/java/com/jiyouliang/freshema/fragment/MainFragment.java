package com.jiyouliang.freshema.fragment;


import android.content.Context;
import android.content.res.TypedArray;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.jiyouliang.freshema.R;
import com.jiyouliang.freshema.util.ViewUtil;
import com.jiyouliang.freshema.view.DotsView;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 首页
 */
public class MainFragment extends Fragment {


    private View mView;
    private ViewPager mViewPager;
    private int mCurItem = 0;
    private boolean canExecuteTimer = true;
    private DotsView mDotsView;
    private ViewFlipper mViewFlipper;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_main, container, false);


        initView(mView);
        initData();
        setListener();
        timeSchedule();

        return mView;
    }

    private void timeSchedule() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (canExecuteTimer) {
                            mViewPager.setCurrentItem(mCurItem++);
                        }
                    }
                });
            }
        }, 1000, 3000);
    }

    private void setListener() {
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurItem = position;
                System.out.println("onPageSelected,pos=" + position);
                mDotsView.setCurItem(position % mDotsView.getDotCount());

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                System.out.println("onPageScrollStateChanged");
                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE://停止滑动
                        canExecuteTimer = true;
                        break;
                    case ViewPager.SCROLL_STATE_DRAGGING://正在滑动中
                        canExecuteTimer = false;
                        break;
                }
            }
        });
    }

    private void initData() {
        int[] resIds = new int[]{R.drawable.banner1, R.drawable.banner2, R.drawable.banner3, R.drawable.banner4, R.drawable.banner5};
        List<Integer> datas = Arrays.asList(R.drawable.banner1, R.drawable.banner2, R.drawable.banner3, R.drawable.banner4, R.drawable.banner5);
        BannerAdapter mAdapter = new BannerAdapter(getContext(), datas);
        mViewPager.setAdapter(mAdapter);


    }

    private void initView(View mView) {
        mViewFlipper = mView.findViewById(R.id.view_flipper);
        Toolbar mToolbar = mView.findViewById(R.id.toolbar);
        mViewPager = mView.findViewById(R.id.vp_banner);
        mDotsView = mView.findViewById(R.id.dotsView);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
//        mViewPager.setAdapter(new );

        initFlipperAnim();
    }

    private void initFlipperAnim() {
        String[] flipperTitlesTip = getResources().getStringArray(R.array.flipperTitlesTip);
        String[] flipperTitles = getResources().getStringArray(R.array.flipperTitles);
        TypedArray a = getResources().obtainTypedArray(R.array.flipperRes);
        for (int i = 0; i < 3; i++) {

            View view = getLayoutInflater().inflate(R.layout.view_flipper_item, null);
            ((TextView)view.findViewById(R.id.tv_flip_tip)).setText(flipperTitlesTip[i]+" | ");
            ((TextView)view.findViewById(R.id.tv_flip_title)).setText(flipperTitles[i]);
            ((ImageView)view.findViewById(R.id.iv_flip_product)).setBackgroundResource(a.getResourceId(i, R.drawable.flipper1));
            mViewFlipper.addView(view);
        }
        a.recycle();
//        mViewFlipper.setFlipInterval(200);
//        mViewFlipper.startFlipping();
    }


    private static class BannerAdapter extends PagerAdapter {


        private final Context mContext;
        private final List<Integer> mData;

        public BannerAdapter(Context context, List<Integer> datas) {
            this.mContext = context;
            this.mData = datas;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
//            View view = LayoutInflater.from(mContext).inflate(R.layout.item_main_banner, null);
//            view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            ImageView ivBanner = new ImageView(container.getContext());
            ivBanner.setScaleType(ImageView.ScaleType.FIT_XY);

            ivBanner.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            ivBanner.setBackgroundResource(mData.get(position % mData.size()));

            container.addView(ivBanner);
            return ivBanner;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // super.destroyItem(container,position,object); 这一句要删除，否则报错
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}
