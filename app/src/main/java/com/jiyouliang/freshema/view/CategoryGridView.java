package com.jiyouliang.freshema.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiyouliang.freshema.R;
import com.jiyouliang.freshema.model.CateInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页分类
 * Created by JiYouLiang on 2018/10/25.
 */

public class CategoryGridView extends GridView {
    private static final int NUM_COLUMNS = 5;
    private CharSequence[] mTitles;

    public CategoryGridView(Context context) {
        this(context, null);
    }

    public CategoryGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CategoryGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setGravity(Gravity.CENTER);

        setNumColumns(NUM_COLUMNS);//列数
        setSelector(new ColorDrawable(Color.TRANSPARENT));

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CategoryGridView);
        List<CateInfo> data = new ArrayList<CateInfo>();

        //titles
        mTitles = a.getTextArray(R.styleable.CategoryGridView_cgvTitles);
        for (int i = 0; i < mTitles.length; i++) {
            CateInfo cate = new CateInfo();
            cate.setTitle(mTitles[i].toString());
            data.add(cate);
        }

        //res
        int resStyleableId = a.getResourceId(R.styleable.CategoryGridView_cgvRes, 0);
        TypedArray a2 = getResources().obtainTypedArray(resStyleableId);
        for (int i = 0; i < a2.length(); i++) {
            int resId = a2.getResourceId(i, 0);
            data.get(i).setResId(resId);
        }

        a.recycle();
        a2.recycle();

        //init adapter
        CateAdapter mAdapter = new CateAdapter(context, data);
        setAdapter(mAdapter);
    }

    static class CateAdapter extends ArrayAdapter<CateInfo> {

        private List<CateInfo> mData;
        private final Context mContext;

        public CateAdapter(@NonNull Context context) {
            super(context, 0);
            this.mContext = context;
        }


        public CateAdapter(@NonNull Context context, @NonNull List<CateInfo> data) {
            super(context, 0, data);
            this.mData = data;
            this.mContext = context;
        }

        @Override
        public int getCount() {
//            return super.getCount();
            return mData.size();
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = new CateItemView(mContext);
                holder = new ViewHolder();
                holder.ivIcon = ((CateItemView) convertView).getImageView();
                holder.tvTitle = ((CateItemView) convertView).getTextView();
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tvTitle.setText(mData.get(position).getTitle());
            holder.ivIcon.setBackgroundResource(mData.get(position).getResId());

            return convertView;
        }

        static class ViewHolder {
            ImageView ivIcon;
            TextView tvTitle;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }

    //RecyclerView嵌套GridView只显示一行
    @Override
    public int getNumColumns() {
        return 2;
    }

}
