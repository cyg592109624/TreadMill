package com.sunrise.treadmill.fragments.workout;

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

public class VRFragmentPage1 extends BaseFragment {
    private OnVRSelectReturn onVRSelectReturn;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_workout_vr_page_1;
    }

    @Override
    protected void init() {
        onVRSelectReturn = (OnVRSelectReturn) getActivity();
    }

    @Override
    protected void setTextStyle() {
        List<TextView> txtList = new ArrayList<TextView>();
        txtList.add((TextView) getParentView().findViewById(R.id.workout_mode_vr_name_1_1));
        txtList.add((TextView) getParentView().findViewById(R.id.workout_mode_vr_name_1_2));
        txtList.add((TextView) getParentView().findViewById(R.id.workout_mode_vr_name_2_1));
        txtList.add((TextView) getParentView().findViewById(R.id.workout_mode_vr_name_2_2));
        txtList.add((TextView) getParentView().findViewById(R.id.workout_mode_vr_name_3_1));
        txtList.add((TextView) getParentView().findViewById(R.id.workout_mode_vr_name_3_2));
        txtList.add((TextView) getParentView().findViewById(R.id.workout_mode_vr_name_4_1));
        txtList.add((TextView) getParentView().findViewById(R.id.workout_mode_vr_name_4_2));
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList, TextUtils.Microsoft(getContext()));
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.Arial(getContext()));
        }
    }

    @OnClick({R.id.workout_mode_vr_img_1, R.id.workout_mode_vr_img_2, R.id.workout_mode_vr_img_3, R.id.workout_mode_vr_img_4})
    public void onVRSelect(View view) {
        int selectVR;
        switch (view.getId()) {
            default:
                selectVR = VirtualRealityActivity.selectNothing;
                break;
            case R.id.workout_mode_vr_img_1:
                selectVR = VirtualRealityActivity.tgValue_1;
                break;
            case R.id.workout_mode_vr_img_2:
                selectVR = VirtualRealityActivity.tgValue_2;
                break;
            case R.id.workout_mode_vr_img_3:
                selectVR = VirtualRealityActivity.tgValue_3;
                break;
            case R.id.workout_mode_vr_img_4:
                selectVR = VirtualRealityActivity.tgValue_4;
                break;
        }
        if (selectVR != VirtualRealityActivity.selectNothing) {
            onVRSelectReturn.onVRSelect(selectVR);
        }
    }

}
