package com.jiyouliang.freshema;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.jiyouliang.freshema.fragment.CartFragment;
import com.jiyouliang.freshema.fragment.CategoryFragment;
import com.jiyouliang.freshema.fragment.FragmentManager;
import com.jiyouliang.freshema.fragment.MainFragment;
import com.jiyouliang.freshema.fragment.MineFragment;
import com.jyl.navbar.NavigationBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationBar.OnNavBarClickListener {

    private ViewPager mViewPager;
    private NavigationBar mNavView;
//    private final int[] normalResIds = new int[]{R.drawable.nav_main_normal, R.drawable.nav_cart_normal, R.drawable.nav_social_unselected, R.drawable.nav_cart_normal, R.drawable.nav_mine_normal};
//    private final int[] selectedResIds = new int[]{R.drawable.nav_main_selected, R.drawable.nav_cart_selected, R.drawable.nav_social_select, R.drawable.nav_cart_selected, R.drawable.nav_mine_selected};
//    private final String[] titls = new String[]{"首页", "分类", "盒社群", "购物车", "我的"};
//    private int[] textColors = new int[]{R.color.colorNavNormal, R.color.colorNavSelected};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mNavView = (NavigationBar) findViewById(R.id.nav_view);
        mNavView.setOnNavTabClickListener(this);

        //setListener
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mNavView.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
//        mNavView.initNavigation(normalResIds, selectedResIds, titls, textColors);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return FragmentManager.getInstance().getFragment(position);
            }

            @Override
            public int getCount() {
                return FragmentManager.getInstance().getFragmentCount();
            }
        });
    }

    @Override
    public void onNavBarClick(View view, int position) {
        mViewPager.setCurrentItem(position, false);
    }


}
