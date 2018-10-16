package com.jiyouliang.freshema.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiyouliang.freshema.R;

/**
 * Created by JiYouLiang on 2018/10/16.
 */

public class NavigationView extends LinearLayout{
    private final static int DEFAULT_TABS_COUNT = 4;
    private int[] mSelectedResIds;
    private int[] mTextColors;

    public NavigationView(Context context) {
        this(context, null, 0);
    }

    public NavigationView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    /**
     * 初始化导航
     * @param normalResIds 图片资源id
     * @param titles 标题
     * @param textColors 文字颜色：选中和未选中
     */
    public void initNavigation(int[]normalResIds, int[]selectedResIds,String[] titles, int[] textColors){
        this.mSelectedResIds = selectedResIds;
        this.mTextColors = textColors;
        removeAllViews();
        int padding = dp2px(getContext(), 6);
        setPadding(0, padding, 0, 0);

        setOrientation(LinearLayout.HORIZONTAL);
        setBackgroundResource(R.drawable.nav_divider);
        LayoutParams p1 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        p1.setMargins(0, padding, 0, padding);
        setLayoutParams(p1);
        for (int i = 0; i < DEFAULT_TABS_COUNT; i++) {
            LinearLayout item = new LinearLayout(getContext());
            item.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f);
//            p.weight = 1;
            p.gravity = Gravity.CENTER;
            item.setLayoutParams(p);

            //add ImageView and TextView
            ImageView iv = new ImageView(getContext());
            LayoutParams ivP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            ivP.gravity = Gravity.CENTER;
            ivP.setMargins(0, padding, 0, 0);
            iv.setLayoutParams(ivP);
            iv.setBackgroundResource(i == 0 ? selectedResIds[0] : normalResIds[i]);

            TextView tv = new TextView(getContext());
            tv.setTextColor(getContext().getResources().getColor(textColors[i == 0 ? 1 : 0]));
            tv.setText(titles[i]);

            LayoutParams tvParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            tvParams.topMargin = dp2px(getContext(), 6);
            tvParams.gravity = Gravity.CENTER;
            tv.setLayoutParams(tvParams);

            item.addView(iv);
            item.addView(tv);

            addView(item);

        }
    }

    private int dp2px(Context context,float dpValue){
        float scale=context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale+0.5f);
    }
}
