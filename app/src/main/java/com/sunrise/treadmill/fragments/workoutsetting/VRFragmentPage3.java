package com.sunrise.treadmill.fragments.workoutsetting;

import android.view.View;
import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.activity.workoutsetting.VirtualRealityActivity;
import com.sunrise.treadmill.base.BaseFragment;
import com.sunrise.treadmill.interfaces.OnVRSelectReturn;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/26.
 */

public class VRFragmentPage3 extends BaseFragment {
    private OnVRSelectReturn onVRSelectReturn;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_workout_vr_page_3;
    }

    @Override
    protected void init() {
        onVRSelectReturn = (OnVRSelectReturn) getActivity();
    }
    @Override
    protected void setTextStyle() {
        List<TextView> txtList = new ArrayList<TextView>();
        txtList.add((TextView) getParentView().findViewById(R.id.workout_mode_vr_name_9_1));
        txtList.add((TextView) getParentView().findViewById(R.id.workout_mode_vr_name_9_2));
        txtList.add((TextView) getParentView().findViewById(R.id.workout_mode_vr_name_10_1));
        txtList.add((TextView) getParentView().findViewById(R.id.workout_mode_vr_name_10_2));

        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList, TextUtils.Microsoft(getContext()));
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.Arial(getContext()));
        }
    }
    @OnClick({R.id.workout_mode_vr_img_9, R.id.workout_mode_vr_img_10})
    public void onVRSelect(View view) {
        int selectVR ;
        switch (view.getId()) {
            default:
                selectVR = VirtualRealityActivity.selectNothing;
                break;
            case R.id.workout_mode_vr_img_9:
                selectVR = VirtualRealityActivity.tgValue_9;
                break;
            case R.id.workout_mode_vr_img_10:
                selectVR = VirtualRealityActivity.tgValue_10;
                break;
        }
        if (selectVR != VirtualRealityActivity.selectNothing) {
            onVRSelectReturn.onVRSelect(selectVR);
        }
    }
}
