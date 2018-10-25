package com.jiyouliang.freshema.fragment;

import android.support.v4.app.Fragment;

import java.util.Arrays;
import java.util.List;

/**
 * Created by JiYouLiang on 2018/10/21.
 */

public class FragmentManager {
    private List<Class<? extends Fragment>> fragClassList = Arrays.asList(MainFragment.class, CategoryFragment.class,
            SocialFragment.class, CartFragment.class, MineFragment.class);

    private FragmentManager() {
    }

    private static FragmentManager instance;

    public static FragmentManager getInstance() {
        if (instance == null) {
            synchronized (FragmentManager.class) {
                if (instance == null) {
                    instance = new FragmentManager();
                }
            }
        }
        return instance;
    }

    public Fragment getFragment(int position) {
        Class<? extends Fragment> clazz = fragClassList.get(position);
        Fragment fragment = null;
        try {
            fragment = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return fragment;
    }

    public int getFragmentCount(){
        return fragClassList.size();
    }
}
