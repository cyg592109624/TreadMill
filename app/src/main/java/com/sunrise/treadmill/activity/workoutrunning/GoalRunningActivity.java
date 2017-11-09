package com.sunrise.treadmill.activity.workoutrunning;

import com.sunrise.treadmill.Constant;
import com.sunrise.treadmill.utils.DateUtil;
import com.sunrise.treadmill.views.workout.LevelView;

/**
 * Created by ChuHui on 2017/9/27.
 */

public class GoalRunningActivity extends BaseRunningActivity {


    @Override
    protected void setUpInfo() {
        runningTimeTarget = Integer.valueOf(workOutInfo.getTime()) * 60;
        runningTimeTotal = Integer.valueOf(workOutInfo.getRunningTime());
        runningTimeSurplus = runningTimeTarget - runningTimeTotal;

        runningDistanceTarget = Integer.valueOf(workOutInfo.getDistance());
        runningDistanceTotal = Integer.valueOf(workOutInfo.getRunningDistance());
        runningDistanceSurplus = runningDistanceTarget - runningDistanceTotal;

        runningCaloriesTarget = Integer.valueOf(workOutInfo.getCalories());
        runningCaloriesTotal = Integer.valueOf(workOutInfo.getRunningCalories());
        runningCaloriesSurplus = runningCaloriesTarget - runningCaloriesTotal;

        switch (workOutInfo.getGoalType()) {
            default:
                break;
            case Constant.MODE_GOAL_TIME:
                isCountDownTime = true;
                avgLevelTime = runningTimeTarget / LevelView.columnCount;

                tgLevel = (int) runningTimeTotal / (int) avgLevelTime;

                headView.setLevelValue(workOutInfo.getLevelList().get(tgLevel).getLevel());
                headView.setTimeValue(DateUtil.getFormatMMSS(runningTimeSurplus));

                headView.setDistanceValue(runningDistanceTotal + "");
                headView.setCaloriesValue(runningCaloriesTotal + "");

                break;
            case Constant.MODE_GOAL_DISTANCE:
                isCountDownTime = false;
                avgLevelTime = 60;

                timerMissionTimes = (int) runningTimeTotal / (int) avgLevelTime;
                tgLevel = timerMissionTimes % LevelView.columnCount;

                headView.setLevelValue(workOutInfo.getLevelList().get(timerMissionTimes).getLevel());
                headView.setTimeValue(DateUtil.getFormatMMSS(runningTimeTotal));

                headView.setDistanceValue(runningDistanceSurplus + "");

                headView.setCaloriesValue(runningCaloriesTotal + "");

                break;
            case Constant.MODE_GOAL_CALORIES:
                isCountDownTime = false;
                avgLevelTime = 60;

                timerMissionTimes = (int) runningTimeTotal / (int) avgLevelTime;
                tgLevel = timerMissionTimes % LevelView.columnCount;

                headView.setLevelValue(workOutInfo.getLevelList().get(timerMissionTimes).getLevel());
                headView.setTimeValue(DateUtil.getFormatMMSS(runningTimeTotal));

                headView.setDistanceValue(runningDistanceTotal + "");

                headView.setCaloriesValue(runningCaloriesSurplus+ "");

                break;
        }

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
