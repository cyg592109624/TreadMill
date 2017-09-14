package com.sunrise.treadmill.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sunrise.treadmill.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChuHui on 2017/9/14.
 */

public class SettingsFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> tabsFragmentList;

    public SettingsFragmentAdapter(FragmentManager fm) {
        super(fm);
        tabsFragmentList = new ArrayList<Fragment>();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            default:
                fragment = null;
                break;
            case 0:
                fragment = tabsFragmentList.get(0);
                break;
            case 1:
                fragment = tabsFragmentList.get(1);
                break;
            case 2:
                fragment = tabsFragmentList.get(2);
                break;
            case 3:
                fragment = tabsFragmentList.get(3);
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    public  void addTabs(Fragment tab){
        tabsFragmentList.add(tab);
    }
    public void setTabsFragmentList(List<Fragment> data) {
        tabsFragmentList.clear();
        tabsFragmentList = data;
    }
}
