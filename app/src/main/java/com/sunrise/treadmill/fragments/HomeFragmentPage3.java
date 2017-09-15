package com.sunrise.treadmill.fragments;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragment;
import com.sunrise.treadmill.utils.LanguageUtils;

/**
 * Created by ChuHui on 2017/9/12.
 */

public class HomeFragmentPage3 extends BaseFragment {
    @Override
    public int getLayoutId() {
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            return R.layout.home_fragment_page_3_zh;
        }
        return R.layout.home_fragment_page_3;
    }

    @Override
    protected void setTextStyle() {

    }

    @Override
    protected void init() {

    }
}
