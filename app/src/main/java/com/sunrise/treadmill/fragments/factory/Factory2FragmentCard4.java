package com.sunrise.treadmill.fragments.factory;

import android.widget.ImageView;
import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragment;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/14.
 */

public class Factory2FragmentCard4 extends BaseFragment {
    @BindView(R.id.factory2_card4_update_logo)
    ImageView upData_logo;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_factory2_card_4;
    }

    @Override
    protected void setTextStyle() {
        TextView hint = (TextView) getParentView().findViewById(R.id.factory2_card4_hint);
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(hint, TextUtils.Microsoft(getContext()));
        } else {
            TextUtils.setTextTypeFace(hint, TextUtils.Arial(getContext()));
        }
    }

    @Override
    protected void init() {
        upData_logo.setEnabled(false);
    }

    @OnClick(R.id.factory2_card4_update_logo)
    public void upDataLogo() {

    }
}
