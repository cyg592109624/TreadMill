package com.sunrise.treadmill.fragments.home;

import android.content.Intent;
import android.view.View;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.activity.workoutrunning.HillRunningActivity;
import com.sunrise.treadmill.activity.workoutrunning.HillRunningActivity_zh;
import com.sunrise.treadmill.activity.workoutrunning.QuickStartRunningActivity;
import com.sunrise.treadmill.activity.workoutrunning.QuickStartRunningActivity_zh;
import com.sunrise.treadmill.activity.workoutsetting.FitnessTestActivity;
import com.sunrise.treadmill.activity.workoutsetting.GoalActivity;
import com.sunrise.treadmill.activity.workoutsetting.HRCActivity;
import com.sunrise.treadmill.activity.workoutsetting.HillActivity;
import com.sunrise.treadmill.activity.workoutsetting.IntervalActivity;
import com.sunrise.treadmill.activity.workoutsetting.UserProgramActivity;
import com.sunrise.treadmill.activity.workoutsetting.VirtualRealityActivity;
import com.sunrise.treadmill.base.BaseFragment;
import com.sunrise.treadmill.utils.LanguageUtils;

import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/12.
 */

public class HomeFragmentPage1 extends BaseFragment {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_page_1;
    }

    @OnClick({R.id.workout_mode_hill, R.id.workout_mode_interval, R.id.workout_mode_goal,
            R.id.workout_mode_fitness_test, R.id.workout_mode_hrc, R.id.workout_mode_user_program,
            R.id.workout_mode_vr, R.id.workout_mode_quick_start})
    void selectWorkOutMode(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.workout_mode_hill:
                intent.setClass(getActivity(), HillActivity.class);
                break;
            case R.id.workout_mode_interval:
                intent.setClass(getActivity(), IntervalActivity.class);
                break;
            case R.id.workout_mode_goal:
                intent.setClass(getActivity(), GoalActivity.class);
                break;
            case R.id.workout_mode_fitness_test:
                intent.setClass(getActivity(), FitnessTestActivity.class);
                break;
            case R.id.workout_mode_hrc:
                intent.setClass(getActivity(), HRCActivity.class);
                break;
            case R.id.workout_mode_user_program:
                intent.setClass(getActivity(), UserProgramActivity.class);
                break;
            case R.id.workout_mode_vr:
                intent.setClass(getActivity(), VirtualRealityActivity.class);
                break;
            case R.id.workout_mode_quick_start:
                if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
                    intent.setClass(getActivity(), QuickStartRunningActivity_zh.class);
                } else {
                    intent.setClass(getActivity(), QuickStartRunningActivity.class);
                }
                break;
        }
        startActivity(intent);
    }

}
