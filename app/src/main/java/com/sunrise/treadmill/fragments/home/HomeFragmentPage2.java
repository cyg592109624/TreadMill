package com.sunrise.treadmill.fragments.home;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragment;
import com.sunrise.treadmill.utils.LanguageUtils;

/**
 * Created by ChuHui on 2017/9/12.
 */

public class HomeFragmentPage2 extends BaseFragment {

    @Override
    public int getLayoutId() {
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            return R.layout.fragment_home_page_2_zh;
        }
        return R.layout.fragment_home_page_2;
    }
}
