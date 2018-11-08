package com.jiyouliang.freshema.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jiyouliang.freshema.R;
import com.jyl.view.CircleIndicatorViewPager;
import com.jyl.view.HorizontalViewPager;

import java.util.List;

/**
 * Created by JiYouLiang on 2018/10/28.
 */

public class BannerAdapter extends HorizontalViewPager.ViewPagerAdapter<Integer> {


    private final Context mContext;
    private final List<Integer> mData;
    private final int mWidth;

    public BannerAdapter(Context context, List<Integer> datas, int width) {
        this.mContext = context;
        this.mData = datas;
        this.mWidth = width;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Integer getItem(int position) {
        return mData.get(position);
    }


    @Override
    public View getView(int position) {
        ImageView ivBanner = new ImageView(mContext);
        ivBanner.setScaleType(ImageView.ScaleType.FIT_XY);
        int height = mContext.getResources().getDimensionPixelSize(R.dimen.banner_height);
        ivBanner.setLayoutParams(new FrameLayout.LayoutParams(mWidth, height));
        ivBanner.setBackgroundResource(mData.get(position));

        return ivBanner;
    }

   /* @Override
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
    }*/
}
