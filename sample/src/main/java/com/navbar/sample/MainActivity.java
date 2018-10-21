package com.navbar.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jyl.navbar.NavigationBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewpager;
    private NavigationBar mNavView;
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        setListener();
    }

    private void initView() {
        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        mNavView = (NavigationBar) findViewById(R.id.nav_view);
    }

    private void initData() {
        String[] titles = getResources().getStringArray(R.array.nav_titles);
        for (String title : titles) {
            Fragment fragment = TabFragment.getInstance(title);
            mFragmentList.add(fragment);
        }

        mViewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        });
    }

    private void setListener(){
        mViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //ViewPager改变，选中对应NavigationBar item
            @Override
            public void onPageSelected(int position) {
                System.out.println("onPageSelected,position="+position);
                mNavView.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //点击切换fragment
        mNavView.setOnNavTabClickListener(new NavigationBar.OnNavBarClickListener() {
            @Override
            public void onNavBarClick(View view, int position) {
                mViewpager.setCurrentItem(position, false);
            }
        });
    }
}
