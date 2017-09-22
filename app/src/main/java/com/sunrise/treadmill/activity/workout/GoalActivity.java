package com.sunrise.treadmill.activity.workout;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseActivity;
import com.sunrise.treadmill.base.BaseFragmentActivity;
import com.sunrise.treadmill.dialog.workout.GoalSetValueDialog;
import com.sunrise.treadmill.interfaces.OnGoalSetValueReturn;
import com.sunrise.treadmill.views.MyWorkOutHead;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/22.
 */

public class GoalActivity extends BaseFragmentActivity implements OnGoalSetValueReturn {
    public static final String SetTg = "SetTg";
    public static final String SetTgValue = "SetTgValue";

    public static final String SetTimeValue = "SetTimeValue";
    public static final String SetDistanceValue = "SetDistanceValue";
    public static final String SetCalValue = "SetCalValue";

    @BindView(R.id.workout_mode_head)
    MyWorkOutHead headView;

    @BindView(R.id.workout_mode_goal_time)
    TextView timeValue;
    @BindView(R.id.workout_mode_goal_distance)
    TextView distanceValue;
    @BindView(R.id.workout_mode_goal_calories)
    TextView calValue;


    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    public int getLayoutId() {
        return R.layout.activity_workout_goal;
    }

    @Override
    protected void init() {
        headView.setHeadMsg(getResources().getString(R.string.workout_mode_goal), getResources().getString(R.string.workout_mode_hint_g), R.mipmap.img_program_goal_icon);

    }

    private static int changeTg = -1;
    private static final int changeTime = 1001;
    private static final int changeDistance = 1002;
    private static final int changeCal = 1003;


    @OnClick({R.id.workout_mode_goal_time, R.id.workout_mode_goal_distance, R.id.workout_mode_goal_calories})
    public void changeGoalValue(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            default:
                break;
            case R.id.workout_mode_goal_time:
                changeTg = changeTime;
                bundle.putString(SetTgValue, timeValue.getText().toString());
                bundle.putString(SetTg, SetTimeValue);
                break;
            case R.id.workout_mode_goal_distance:
                changeTg = changeDistance;
                bundle.putString(SetTgValue, distanceValue.getText().toString());
                bundle.putString(SetTg, SetDistanceValue);
                break;
            case R.id.workout_mode_goal_calories:
                changeTg = changeCal;
                bundle.putString(SetTgValue, calValue.getText().toString());
                bundle.putString(SetTg, SetCalValue);
                break;
        }
        GoalSetValueDialog dialog = new GoalSetValueDialog();
        dialog.setArguments(bundle);
        dialog.show(fragmentManager, GoalSetValueDialog.TAG);
    }

    @Override
    public void onGoalSetValueResult(String result) {
        switch (changeTg) {
            default:
                break;
            case changeTime:
                timeValue.setText(result);
                break;
            case changeDistance:
                distanceValue.setText(result);
                break;
            case changeCal:
                calValue.setText(result);
                break;
        }
        changeTg = changeTg;
    }

    @OnClick({R.id.workout_mode_start})
    public void beginWorkOut() {

    }

    @OnClick(R.id.bottom_logo_tab_home)
    public void onBackHome() {
        finishActivity();
    }

}
