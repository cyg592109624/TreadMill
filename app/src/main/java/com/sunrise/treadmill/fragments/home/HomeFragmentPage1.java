package com.sunrise.treadmill.fragments.home;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.activity.workout.FitnessTestActivity;
import com.sunrise.treadmill.activity.workout.GoalActivity;
import com.sunrise.treadmill.activity.workout.HRCActivity;
import com.sunrise.treadmill.activity.workout.HillActivity;
import com.sunrise.treadmill.activity.workout.IntervalActivity;
import com.sunrise.treadmill.base.BaseFragment;

import java.util.List;

import butterknife.BindViews;
import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/12.
 */

public class HomeFragmentPage1 extends BaseFragment {

    @BindViews({R.id.workout_mode_hill})
    List<ImageView> workOutList;

    @Override
    public int getLayoutId() {
        return R.layout.home_fragment_page_1;
    }

    @OnClick({R.id.workout_mode_hill, R.id.workout_mode_interval, R.id.workout_mode_goal, R.id.workout_mode_fitness_test, R.id.workout_mode_hrc})
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
        }
        startActivity(intent);
    }

}
