package com.jiyouliang.freshema.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.content.res.TypedArrayUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiyouliang.freshema.R;
import com.jiyouliang.freshema.util.ViewUtil;

/**
 * 首页分类GridView Item
 * Created by JiYouLiang on 2018/10/25.
 */

public class CateItemView extends LinearLayout{

    private TextView mTv;
    private ImageView mIv;

    public CateItemView(Context context) {
        this(context, null);
    }

    public CateItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CateItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        removeAllViews();
        initLayout();
        //add ImageView
        addImageView();

        //add TextView
        addTextView();

    }

    private void addTextView() {
        mTv = new TextView(getContext());
        mTv.setText("时令水果");
        mTv.setTextColor(getResources().getColor(R.color.colorNavNormal));
        LayoutParams tvParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mTv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);
        mTv.setLayoutParams(tvParams);
        addView(mTv);
    }

    private void addImageView() {
        mIv = new ImageView(getContext());
        mIv.setBackgroundResource(R.drawable.grid_pro_cate1);
        LayoutParams ivParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        ivParams.gravity = Gravity.CENTER_HORIZONTAL;
        mIv.setLayoutParams(ivParams);
        addView(mIv);
    }

    private void initLayout() {
        setOrientation(LinearLayout.VERTICAL);
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//        lp.weight = 1;
        int margin = ViewUtil.dp2Pix(getContext(), 4);
        lp.setMargins(margin, 0, margin, 0);
        setLayoutParams(lp);
    }

    public TextView getTextView(){
        return mTv;
    }

    public ImageView getImageView(){
        return mIv;
    }
}

