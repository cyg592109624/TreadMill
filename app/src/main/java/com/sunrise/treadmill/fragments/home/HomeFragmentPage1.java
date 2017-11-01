package com.sunrise.treadmill.fragments.home;

import android.content.Intent;
import android.view.View;

import com.sunrise.treadmill.Constant;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragment;
import com.sunrise.treadmill.interfaces.home.OnModeSelectReturn;

import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/12.
 */

public class HomeFragmentPage1 extends BaseFragment {

    private OnModeSelectReturn selectReturn;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_page_1;
    }

    @Override
    protected void init() {
        selectReturn = (OnModeSelectReturn) getActivity();
    }

    @Override
    public void recycleObject() {
        selectReturn = null;
    }

    @OnClick({R.id.workout_mode_hill, R.id.workout_mode_interval, R.id.workout_mode_goal,
            R.id.workout_mode_fitness_test, R.id.workout_mode_hrc, R.id.workout_mode_user_program,
            R.id.workout_mode_vr, R.id.workout_mode_quick_start})
    public  void selectWorkOutMode(View view) {
        switch (view.getId()) {
            default:
                break;
            case R.id.workout_mode_hill:
                selectReturn.onWorkOutSetting(Constant.MODE_HILL);
                break;
            case R.id.workout_mode_interval:
                selectReturn.onWorkOutSetting(Constant.MODE_INTERVAL);
                break;
            case R.id.workout_mode_goal:
                selectReturn.onWorkOutSetting(Constant.MODE_GOAL);
                break;
            case R.id.workout_mode_fitness_test:
                selectReturn.onWorkOutSetting(Constant.MODE_FITNESS_TEST);
                break;
            case R.id.workout_mode_hrc:
                selectReturn.onWorkOutSetting(Constant.MODE_HRC);
                break;
            case R.id.workout_mode_user_program:
                selectReturn.onWorkOutSetting(Constant.MODE_USER_PROGRAM);
                break;
            case R.id.workout_mode_vr:
                selectReturn.onWorkOutSetting(Constant.MODE_VR);
                break;
            case R.id.workout_mode_quick_start:
                selectReturn.onWorkOutSetting(Constant.MODE_QUICK_START);
                break;
        }
    }

    public void setSelectReturn(OnModeSelectReturn selectReturn){
        this.selectReturn=selectReturn;
    }
}
