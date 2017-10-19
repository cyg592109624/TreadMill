package com.sunrise.treadmill.fragments.home;

import android.widget.ImageView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragment;
import com.sunrise.treadmill.utils.LanguageUtils;

import butterknife.BindView;

/**
 * Created by ChuHui on 2017/9/12.
 */

public class HomeFragmentPage3 extends BaseFragment {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_page_3;
    }

    @Override
    public void clearObj() {
        parentView = null;
    }
}
