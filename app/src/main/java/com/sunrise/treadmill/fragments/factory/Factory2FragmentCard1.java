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

public class Factory2FragmentCard1 extends BaseFragment {

    @Override
    public int getLayoutId() {
        return R.layout.factory2_fragment_card_1;
    }

    @Override
    protected void setTextStyle() {
        List<TextView> txtList = new ArrayList<TextView>();
        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card1_1_display_mode));
        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card1_1_pause_mode));
        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card1_1_key_tone));
        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card1_1_buzzer));
        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card1_1_child_lock));

        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card1_2_ctrl_page));
        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card1_2_key_test));
        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card1_2_brake_test));

        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card1_2_level));
        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card1_2_level_value));

        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card1_2_pwm));
        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card1_2_pwm_value));

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
