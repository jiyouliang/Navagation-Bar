package com.jiyouliang.freshema;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.jiyouliang.freshema.view.NavigationView;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private NavigationView mNavView;
    private final int[] normalResIds = new int[]{R.drawable.nav_main_normal, R.drawable.nav_cart_normal, R.drawable.nav_cart_normal, R.drawable.nav_mine_normal};
    private final int[] selectedResIds = new int[]{R.drawable.nav_main_selected, R.drawable.nav_cart_selected, R.drawable.nav_cart_selected, R.drawable.nav_mine_selected};
    private final String[] titls = new String[]{"首页", "分类", "购物车", "我的"};
    private int[] textColors = new int[]{R.color.colorNavNormal, R.color.colorNavSelected};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mNavView = (NavigationView) findViewById(R.id.nav_view);
    }

    private void initData() {
        mNavView.initNavigation(normalResIds, selectedResIds, titls, textColors);
    }
}
