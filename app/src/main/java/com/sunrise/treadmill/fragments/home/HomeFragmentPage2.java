package com.sunrise.treadmill.fragments.home;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragment;
import com.sunrise.treadmill.interfaces.home.OnModeSelectReturn;
import com.sunrise.treadmill.utils.LanguageUtils;

/**
 * Created by ChuHui on 2017/9/12.
 */

public class HomeFragmentPage2 extends BaseFragment {
    private OnModeSelectReturn selectReturn;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_page_2;
    }

    @Override
    protected void init() {
        selectReturn = (OnModeSelectReturn) getActivity();
    }

    @Override
    public void recycleObject() {
    }
    public void setSelectReturn(OnModeSelectReturn selectReturn){
        this.selectReturn=selectReturn;
    }
}
