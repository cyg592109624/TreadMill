package com.sunrise.treadmill.fragments.summary;

import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragment;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;
import com.sunrise.treadmill.views.summary.LineChat;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ChuHui on 2017/9/12.
 */

public class SummaryFragmentPage3 extends BaseFragment {

    @BindView(R.id.summary_fragment3_lineChat)
    LineChat lineChat;

    @BindView(R.id.summary_fragment3_avg_value)
    TextView avgValue;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_summary_page_3;
    }

    @Override
    public void recycleObject() {
        avgValue = null;
        lineChat.recycle();
        lineChat = null;
    }


    @Override
    protected void setTextStyle() {
        List<TextView> txtList = new ArrayList<>();
        txtList.add((TextView) parentView.findViewById(R.id.summary_fragment3_hint));
        txtList.add((TextView) parentView.findViewById(R.id.summary_fragment3_avg));
        txtList.add((TextView) parentView.findViewById(R.id.summary_fragment3_avg_unit));
        txtList.add(avgValue);

        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList, TextUtils.Microsoft(getContext()));
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.Arial(getContext()));
        }
        txtList.clear();
        txtList = null;
    }
}
