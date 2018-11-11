package com.jiyouliang.freshema.fragment;


import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;

import com.jiyouliang.freshema.R;
import com.jiyouliang.freshema.util.ViewUtil;
import com.jyl.view.CircleIndicatorViewPager;
import com.jyl.view.HorizontalViewPager;

/**
 * 分类
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {


    private static final int[] resList = new int[]{R.drawable.cate_gv_item2, R.drawable.cate_gv_item1, R.drawable.cate_gv_item3};
    private GridView mGridView;
    private static final int[] gridViewRes =
            new int[]{R.drawable.cate_fruit, R.drawable.cate_vegetable, R.drawable.cate_meat,
                    R.drawable.cate_bread, R.drawable.cate_seafood, R.drawable.cate_noddle,
                    R.drawable.cate_hot_juice, R.drawable.cate_snack, R.drawable.cate_oil,
                    R.drawable.cate_bear, R.drawable.cate_skin_care, R.drawable.cate_part};
    private RecyclerView mRecyclerView;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.rv_cate);
        GridLayoutManager lm = new GridLayoutManager(getContext(), 3, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(lm);
    }


    private void initData() {
        CategoryAdapter mAdapter = new CategoryAdapter(mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
    }

    private static class CategoryAdapter extends RecyclerView.Adapter {
        private static final int TYPE_HEADER = 0;
        private static final int TYPE_NORMAL = 1;
        private Context mContext;
        private RecyclerView mRecyclerView;

        public CategoryAdapter(RecyclerView recyclerView) {
            this.mRecyclerView = recyclerView;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            this.mContext = parent.getContext();
            if (viewType == TYPE_HEADER) {
//                setHeaderLayoutManager();
                View itemHeader = LayoutInflater.from(parent.getContext()).inflate(R.layout.cate_vp_banner, parent, false);
                return new HeaderHolder(itemHeader);
            } else {
//                setNormalLayoutManager();
                View itemNormal = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cate_gridview, parent, false);
                return new NormalHolder(itemNormal, mContext);
            }
        }

        private void setHeaderLayoutManager() {
            LinearLayoutManager lm = new LinearLayoutManager(mContext);
            lm.setOrientation(LinearLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(lm);
        }

        private void setNormalLayoutManager() {
            GridLayoutManager gm = new GridLayoutManager(mContext, 3, LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(gm);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            int type = getItemViewType(position);
            if (type == TYPE_HEADER) {
                bindHeader(holder);
            } else {
                bindNormal(holder, position);
            }
        }

        private void bindHeader(RecyclerView.ViewHolder holder) {
            HeaderHolder viewHolder = (HeaderHolder) holder;
            WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            final Point point = new Point();
            wm.getDefaultDisplay().getSize(point);
            viewHolder.civp.setAdapter(new HorizontalViewPager.ViewPagerAdapter() {
                @Override
                public int getCount() {
                    return resList.length;
                }

                @Override
                public Integer getItem(int position) {
                    return resList[position];
                }

                @Override
                public View getView(int position) {
                    ImageView iv = new ImageView(mContext);
                    int width = point.x;
                    int height = mContext.getResources().getDimensionPixelSize(R.dimen.banner_height);
                    FrameLayout.LayoutParams fl = new FrameLayout.LayoutParams(width, height);
                    iv.setLayoutParams(fl);

                    iv.setBackgroundResource(getItem(position));
                    return iv;
                }
            });
        }

        private void bindNormal(RecyclerView.ViewHolder holder, int position) {
            NormalHolder viewHolder = (NormalHolder) holder;
            viewHolder.iv.setBackgroundResource(gridViewRes[position]);
        }

        @Override
        public int getItemCount() {
            return 12;
        }

        @Override
        public int getItemViewType(int position) {
            return TYPE_NORMAL;
            /*if (position == 0) {
                return TYPE_HEADER;
            } else {
                return TYPE_NORMAL;
            }*/
        }
    }

    public static class HeaderHolder extends RecyclerView.ViewHolder {
        private CircleIndicatorViewPager civp;

        public HeaderHolder(View itemView) {
            super(itemView);
            this.civp = (CircleIndicatorViewPager) itemView.findViewById(R.id.civp);
        }
    }

    public static class NormalHolder extends RecyclerView.ViewHolder {
        private ImageView iv;

        public NormalHolder(View itemView, Context context) {
            super(itemView);
            this.iv = (ImageView) itemView.findViewById(R.id.iv);
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Point point = new Point();
            wm.getDefaultDisplay().getSize(point);
            // 获取原来的LayoutParams，并修改高度，避免重新创建
            GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) this.iv.getLayoutParams();
            int height = point.x / 3 - ViewUtil.dp2Pix(context, 10) * 3;
            int margin = ViewUtil.dp2Pix(context, 10);
            lp.height = height;
//            lp.width = height;
//            lp.setMargins(margin, margin, 0, 0);

            this.iv.setLayoutParams(lp);
        }
    }

    /*public static class ViewPagerAdapter extends HorizontalViewPager.ViewPagerAdapter<Integer> {
        private Context mContext;
        private int[] mResList;
        private final Point point;

        public ViewPagerAdapter(Context context, int[] resList) {
            this.mContext = context;
            this.mResList = resList;
            WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            point = new Point();
            wm.getDefaultDisplay().getSize(point);
        }

        @Override
        public int getCount() {
            return mResList.length;
        }

        @Override
        public Integer getItem(int position) {
            return mResList[position];
        }

        @Override
        public View getView(int position) {


            ImageView iv = new ImageView(mContext);
//            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            int padding = mContext.getResources().getDimensionPixelSize(R.dimen.padding_size);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(point.x - padding * 2, ViewUtil.dp2Pix(mContext, 140));
            iv.setLayoutParams(lp);
            iv.setBackgroundResource(mResList[position]);
            return iv;
        }
    }

    public static class CateGridViewAdapter extends ArrayAdapter<Integer> {

        private int[] mData;
        private Context mContext;

        public CateGridViewAdapter(@NonNull Context context, int[] objects) {
            super(context, 0);
            this.mData = objects;
            this.mContext = context;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable android.view.View convertView, @NonNull ViewGroup parent) {
            CateViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_cate_gridview, parent, false);
                holder = new CateViewHolder();
                holder.iv = (ImageView) convertView.findViewById(R.id.iv);
                convertView.setTag(holder);
            } else {
                holder = (CateViewHolder) convertView.getTag();
            }

            //bind data
            holder.iv.setBackgroundResource(getItem(position));

            return convertView;
        }

        @Override
        public int getCount() {
            return mData.length;
        }

    }

    static class CateViewHolder {
        private ImageView iv;
    }*/


}
