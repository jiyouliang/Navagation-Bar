package com.jiyouliang.freshema.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jiyouliang.freshema.R;
import com.jiyouliang.freshema.util.ViewUtil;

/**
 * ViewPager圆点
 * Created by JiYouLiang on 2018/10/24.
 */

public class DotsView extends LinearLayout {
    private static final int VIEW_TYPE_UNSELECTED = 0;
    private static final int VIEW_TYPE_SELECTED = 1;
    private static final int DEFAULT_DOT_COUNT = 5;//默认圆点个数
    private int mDotCount;

    public DotsView(Context context) {
        this(context, null, 0);
    }

    public DotsView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DotsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        removeAllViews();
        setOrientation(LinearLayout.HORIZONTAL);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DotsView);
        mDotCount = a.getInt(R.styleable.DotsView_dotCount, DEFAULT_DOT_COUNT);
        a.recycle();

        for (int i = 0; i < mDotCount; i++) {
            ImageView itemView = createItemView();
            addView(itemView);
        }

        setCurItem(0);
    }

    public void setCurItem(int position){
        resetDotsBackground();
        ImageView iv = (ImageView) getChildAt(position);
        iv.setSelected(true);
    }

    public int getDotCount(){
        return mDotCount;
    }

    private void resetDotsBackground(){
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            ImageView iv = (ImageView) getChildAt(i);
            iv.setSelected(false);
        }
    }


    private ImageView createItemView() {
        ImageView iv = new ImageView(getContext());
        LayoutParams p = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        int margin = ViewUtil.dp2Pix(getContext(), 6);
        p.setMargins(margin, 0, margin, 0);
        iv.setLayoutParams(p);
        iv.setBackgroundResource(R.drawable.banner_dot_selector);
        return iv;
    }


}
