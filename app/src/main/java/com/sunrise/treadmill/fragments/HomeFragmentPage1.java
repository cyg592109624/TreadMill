package com.sunrise.treadmill.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragment;

/**
 * Created by ChuHui on 2017/9/12.
 */

public class HomeFragmentPage1 extends BaseFragment {
    private View rootVew;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootVew = inflater.inflate(R.layout.home_fragment_page_1, container, false);
        return rootVew;
    }

    @Override
    protected void loadData() {

    }
}
