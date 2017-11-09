package com.sunrise.treadmill.activity.workoutrunning;

import android.widget.ImageView;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.bean.Level;
import com.sunrise.treadmill.utils.DateUtil;
import com.sunrise.treadmill.utils.ImageUtils;
import com.sunrise.treadmill.views.workout.LevelView;

import java.util.List;

/**
 * Created by ChuHui on 2017/9/27.
 */

public class HRCRunningActivity extends BaseRunningActivity {

    @Override
    public void init() {
        super.init();
        ImageUtils.changeImageView((ImageView) bottomView.findViewById(R.id.workout_running_level_up), R.drawable.btn_sportmode_up_3);
        ImageUtils.changeImageView((ImageView) bottomView.findViewById(R.id.workout_running_level_down), R.drawable.btn_sportmode_down_3);
    }

    @Override
    protected void setUpInfo() {
        super.setUpInfo();

        runningDistanceTarget = Integer.valueOf(workOutInfo.getDistance());
        runningDistanceTotal = Integer.valueOf(workOutInfo.getRunningDistance());
        runningDistanceSurplus = runningDistanceTarget - runningDistanceTotal;
        headView.setDistanceValue(runningDistanceTotal + "");

        runningCaloriesTarget = Integer.valueOf(workOutInfo.getCalories());
        runningCaloriesTotal = Integer.valueOf(workOutInfo.getRunningCalories());
        runningCaloriesSurplus = runningCaloriesTarget - runningCaloriesTotal;
        headView.setCaloriesValue(runningCaloriesTotal + "");


        headView.setPulseValue(runningPulseTarget + "");

        headView.setWattValue(valueWatt + "");

        headView.setSpeedValue(valueSpeed + "");
    }

    @Override
    public void onStartTypeA() {
        drawLevelView();
        bindServer();
        showCountDownDialog();
    }

    @Override
    public void onStartTypeB() {
        drawLevelView();
        bindServer();
        runningTimer.start();
    }

    @Override
    public void onStartTypeC() {

    }

    @Override
    public void onLevelUp() {

    }

    @Override
    public void onLevelDown() {

    }
}
