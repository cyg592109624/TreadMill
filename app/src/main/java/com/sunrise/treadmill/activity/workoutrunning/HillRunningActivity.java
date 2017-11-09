package com.sunrise.treadmill.activity.workoutrunning;

/**
 * Created by ChuHui on 2017/9/27.
 */

public class HillRunningActivity extends BaseRunningActivity {


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

}
