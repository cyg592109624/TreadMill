package com.sunrise.treadmill.fragments.summary;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragment;

/**
 * Created by ChuHui on 2017/9/12.
 */

public class SummaryFragmentPage1 extends BaseFragment {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_summary_page_1;
    }

    @Override
    public void clearObj() {
        parentView=null;
    }
}
