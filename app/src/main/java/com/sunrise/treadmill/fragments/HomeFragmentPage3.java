package com.sunrise.treadmill.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragment;
import com.sunrise.treadmill.utils.LanguageUtils;

/**
 * Created by ChuHui on 2017/9/12.
 */

public class HomeFragmentPage3 extends BaseFragment {
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            rootView = inflater.inflate(R.layout.home_fragment_page_3_zh, container, false);
        } else {
            rootView = inflater.inflate(R.layout.home_fragment_page_3, container, false);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public View getLayoutView() {
        return rootView;
    }

    @Override
    protected void loadData() {

    }
}
