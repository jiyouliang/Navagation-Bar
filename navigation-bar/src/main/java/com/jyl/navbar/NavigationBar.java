package com.jyl.navbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by JiYouLiang on 2018/10/17.
 */

public class NavigationBar extends LinearLayout {
    private int[] mSelectedResIds;
    private int[] mNormalResIds;
    private int[] mTextColors;
    private CharSequence[] mTitles;
    private OnNavBarClickListener mListener;
    private final float DEFAULT_TEXT_ICON_SPACE = 0;
    private final int DEFAULT_TEXT_SIZE = 16;//默认文字大小
    private int mTextIconSpace;
    private int mTextSize;
    private int mIconWidth;
    private int mIconHeight;

    public NavigationBar(Context context) {
        this(context, null, 0);
    }

    public NavigationBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NavigationBar);


        //标题
        CharSequence[] titles = a.getTextArray(R.styleable.NavigationBar_navTitles);
        mTitles = titles;


        //未选中图片资源
        int normalResId = a.getResourceId(R.styleable.NavigationBar_navNormalRes, 0);
        TypedArray normalTypeArray = a.getResources().obtainTypedArray(normalResId);
        int[] normalResIds = new int[normalTypeArray.length()];
        for (int i = 0; i < normalTypeArray.length(); i++) {
            int resId = normalTypeArray.getResourceId(i, 0);
            normalResIds[i] = resId;
        }
        mNormalResIds = normalResIds;


        //选中图片资源
        int selectedResId = a.getResourceId(R.styleable.NavigationBar_navSelectedRes, 0);
        TypedArray setdTypeArray = a.getResources().obtainTypedArray(selectedResId);
        int[] selectedResIds = new int[setdTypeArray.length()];
        for (int i = 0; i < setdTypeArray.length(); i++) {
            int resId = setdTypeArray.getResourceId(i, 0);
            selectedResIds[i] = resId;
        }
        mSelectedResIds = selectedResIds;

        //文本颜色
        int colorsResId = a.getResourceId(R.styleable.NavigationBar_navTextColors, 0);
        TypedArray colorsTypeArray = a.getResources().obtainTypedArray(colorsResId);
        int[] textColors = new int[colorsTypeArray.length()];
        for (int i = 0; i < colorsTypeArray.length(); i++) {
            int colorResId = colorsTypeArray.getResourceId(i, 0);
            textColors[i] = colorResId;
        }
        mTextColors = textColors;

        //文字和图片边距
        mTextIconSpace = a.getDimensionPixelSize(R.styleable.NavigationBar_navTextIconSpace,
                dp2px(getContext(), DEFAULT_TEXT_ICON_SPACE));
        //文字大小
        mTextSize = a.getDimensionPixelSize(R.styleable.NavigationBar_navTextSize, DEFAULT_TEXT_SIZE);

        //图片宽高
        mIconWidth = a.getDimensionPixelSize(R.styleable.NavigationBar_navIconWidth, LayoutParams.WRAP_CONTENT);
        mIconHeight = a.getDimensionPixelSize(R.styleable.NavigationBar_navIconHeight, LayoutParams.WRAP_CONTENT);

        colorsTypeArray.recycle();
        normalTypeArray.recycle();
        setdTypeArray.recycle();
        a.recycle();

        //init view
        initNavigation(mNormalResIds, mSelectedResIds, mTitles, mTextColors);
    }

    /**
     * 初始化导航
     *
     * @param normalResIds 图片资源id
     * @param titles       标题
     * @param textColors   文字颜色：选中和未选中
     */
    public void initNavigation(int[] normalResIds, int[] selectedResIds, CharSequence[] titles, int[] textColors) {
        this.mSelectedResIds = selectedResIds;
        this.mTextColors = textColors;
        removeAllViews();

        setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams p1 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        setLayoutParams(p1);
        for (int i = 0; i < normalResIds.length; i++) {
            //Container ImageView and TextView
            LinearLayout item = new LinearLayout(getContext());
            item.setOrientation(LinearLayout.VERTICAL);
            LayoutParams p = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f);
            p.gravity = Gravity.CENTER;
            item.setLayoutParams(p);

            //init ImageView and TextView
            ImageView iv = new ImageView(getContext());
            LayoutParams ivP = new LayoutParams(mIconWidth, mIconHeight);
            ivP.gravity = Gravity.CENTER;
            iv.setLayoutParams(ivP);
            iv.setBackgroundResource(i == 0 ? selectedResIds[0] : normalResIds[i]);

            TextView tv = new TextView(getContext());
            tv.setTextColor(getContext().getResources().getColor(textColors[i == 0 ? 1 : 0]));
            tv.setText(titles[i]);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);

            LayoutParams tvParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            tvParams.topMargin = mTextIconSpace;
            tvParams.gravity = Gravity.CENTER;
            tv.setLayoutParams(tvParams);

            //add ImageView and TextView
            item.addView(iv);
            item.addView(tv);

            //add to the NavigationBar
            addView(item);
        }

        setListener();
    }


    private int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 设置监听
     */
    private void setListener() {
        int count = getChildCount();
        if (count < 0) return;
        for (int i = 0; i < count; i++) {
            final int index = i;
            final View childView = getChildAt(i);
            if (!(childView instanceof LinearLayout)) return;
            //设置点击监听并修改tab背景与文字颜色
            childView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    resetNavbar();
                    int count2 = ((LinearLayout) childView).getChildCount();
                    if (count2 != 2) return;
                    ImageView iv = (ImageView) ((LinearLayout) childView).getChildAt(0);
                    TextView tv = (TextView) ((LinearLayout) childView).getChildAt(1);
                    iv.setBackgroundResource(mSelectedResIds[index]);
                    tv.setTextColor(getContext().getResources().getColor(mTextColors[1]));

                    //callback
                    if (mListener != null) {
                        mListener.onNavBarClick(v, index);
                    }
                }
            });
        }
    }

    /**
     * 重置tab背景图片与文字颜色状态
     */
    private void resetNavbar() {
        int count = getChildCount();
        if (count < 0) return;
        for (int i = 0; i < count; i++) {
            final View childView = getChildAt(i);
            if (!(childView instanceof LinearLayout)) return;
            int count2 = ((LinearLayout) childView).getChildCount();
            if (count2 != 2) return;
            ImageView iv = (ImageView) ((LinearLayout) childView).getChildAt(0);
            TextView tv = (TextView) ((LinearLayout) childView).getChildAt(1);
            iv.setBackgroundResource(mNormalResIds[i]);
            tv.setTextColor(mTextColors[0]);
        }
    }

    /**
     * @param listener
     */
    public void setOnNavTabClickListener(OnNavBarClickListener listener) {
        this.mListener = listener;
    }

    public interface OnNavBarClickListener {
        /**
         * @param view     tab view
         * @param position tab所在位置
         */
        void onNavBarClick(View view, int position);
    }
}
