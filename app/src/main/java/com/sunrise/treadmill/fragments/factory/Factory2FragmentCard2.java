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

public class Factory2FragmentCard2 extends BaseFragment {

    @Override
    public int getLayoutId() {
        return R.layout.factory2_fragment_card_2;
    }

    @Override
    protected void setTextStyle() {
        List<TextView> txtList = new ArrayList<TextView>();

        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card2_1_total_time));
        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card2_1_total_time_value));

        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card2_1_total_distance));
        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card2_1_total_distance_value));

        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card2_2_ver_sdk));
        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card2_2_ver_sdk_value));

        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card2_2_ver_firmware));
        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card2_2_ver_firmware_value));

        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card2_2_ver_soft));
        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card2_2_ver_soft_value));

        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList, TextUtils.MicrosoftBold(getContext()));
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.ArialBold(getContext()));
        }
    }

    @Override
    protected void init() {

    }
}
