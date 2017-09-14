package com.sunrise.treadmill.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragment;

/**
 * Created by ChuHui on 2017/9/14.
 */

public class SettingsFragmentCard4 extends BaseFragment {
    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.settings_fragment_card_1, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public View getLayoutView() {
        return rootView;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void setTextStyle() {

    }
}
