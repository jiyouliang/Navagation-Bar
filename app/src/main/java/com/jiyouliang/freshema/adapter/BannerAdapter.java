package com.jiyouliang.freshema.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by JiYouLiang on 2018/10/28.
 */

public class BannerAdapter extends PagerAdapter {


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
