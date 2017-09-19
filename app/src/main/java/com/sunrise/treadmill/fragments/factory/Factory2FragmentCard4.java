package com.sunrise.treadmill.fragments.factory;

import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragment;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChuHui on 2017/9/14.
 */

public class Factory2FragmentCard4 extends BaseFragment {

    @Override
    public int getLayoutId() {
        return R.layout.factory2_fragment_card_4;
    }

    @Override
    protected void setTextStyle() {
        TextView hint = (TextView) getParentView().findViewById(R.id.factory2_card4_hint);
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(hint, TextUtils.MicrosoftBold(getContext()));
        } else {
            TextUtils.setTextTypeFace(hint, TextUtils.ArialBold(getContext()));
        }
    }

    @Override
    protected void init() {

    }
}
