package com.sunrise.treadmill.activity.workoutsetting;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunrise.treadmill.Constant;
import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragmentActivity;
import com.sunrise.treadmill.dialog.workoutsetting.GoalSetValueDialog;
import com.sunrise.treadmill.interfaces.workout.setting.OnGoalSetValueReturn;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;
import com.sunrise.treadmill.views.workout.setting.WorkOutSettingHead;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/22.
 */

public class GoalActivity extends BaseFragmentActivity implements OnGoalSetValueReturn {
    public static final String CHANGE_TG = "CHANGE_TG";
    public static final String CHANGE_TG_VALUE = "CHANGE_TG_VALUE";

    @BindView(R.id.workout_mode_goal_time)
    TextView timeValue;

    @BindView(R.id.workout_mode_goal_distance)
    TextView distanceValue;

    @BindView(R.id.workout_mode_goal_calories)
    TextView calValue;

    @BindView(R.id.include2)
    ConstraintLayout optionBody;

    @BindView(R.id.workout_setting_start)
    ImageView startBtn;

    private GoalSetValueDialog dialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_workout_setting_goal;
    }

    @Override
    public void recycleObject() {
        timeValue = null;
        distanceValue = null;
        calValue = null;

        dialog = null;
        startBtn = null;
    }

    @Override
    protected void setTextStyle() {
        List<TextView> txtList = new ArrayList<TextView>();

        txtList.add((TextView) findViewById(R.id.workout_setting_head_name));
        txtList.add((TextView) findViewById(R.id.workout_setting_head_hint));
        txtList.add((TextView) findViewById(R.id.workout_setting_hint));


        txtList.add(timeValue);
        txtList.add(distanceValue);
        txtList.add(calValue);
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList, TextUtils.Microsoft(activityContext));
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.Arial(activityContext));
        }
        txtList.clear();
        txtList = null;
    }

    @Override
    protected void init() {
        Drawable drawable = null;
        if (GlobalSetting.UnitType.equals(Constant.UNIT_TYPE_METRIC)) {
            drawable = ContextCompat.getDrawable(activityContext, R.drawable.btn_workout_goal_distance_km);
        } else {
            drawable = ContextCompat.getDrawable(activityContext, R.drawable.btn_workout_goal_distance_mile);
        }
        distanceValue.setBackground(drawable);
        timeValue.setText("20");
        distanceValue.setText("5");
        calValue.setText("20");
        startBtn.setEnabled(false);
    }


    @OnClick({R.id.workout_mode_goal_time, R.id.workout_mode_goal_distance, R.id.workout_mode_goal_calories})
    public void changeGoalValue(View view) {
        Bundle bundle = new Bundle();
        boolean hasSelect = true;
        switch (view.getId()) {
            default:
                hasSelect = false;
                break;
            case R.id.workout_mode_goal_time:
                WorkOutSettingCommon.changeTg = WorkOutSettingCommon.CHANGE_TIME;
                bundle.putString(CHANGE_TG_VALUE, timeValue.getText().toString());
                bundle.putInt(CHANGE_TG, WorkOutSettingCommon.CHANGE_TIME);
                break;
            case R.id.workout_mode_goal_distance:
                WorkOutSettingCommon.changeTg = WorkOutSettingCommon.CHANGE_DISTANCE;
                bundle.putString(CHANGE_TG_VALUE, distanceValue.getText().toString());
                bundle.putInt(CHANGE_TG, WorkOutSettingCommon.CHANGE_DISTANCE);
                break;
            case R.id.workout_mode_goal_calories:
                WorkOutSettingCommon.changeTg = WorkOutSettingCommon.CHANGE_CALORIES;
                bundle.putString(CHANGE_TG_VALUE, calValue.getText().toString());
                bundle.putInt(CHANGE_TG, WorkOutSettingCommon.CHANGE_CALORIES);
                break;
        }
        if (hasSelect) {
            optionBody.setVisibility(View.GONE);
            if (dialog == null) {
                dialog = new GoalSetValueDialog();
            }
            dialog.setArguments(bundle);
            dialog.show(fragmentManager, GoalSetValueDialog.TAG);
        }
    }

    @Override
    public void onGoalSetValueResult(String result) {
        if ("".equals(result)) {
            return;
        }
        clearState();
        startBtn.setEnabled(true);
        switch (WorkOutSettingCommon.changeTg) {
            default:
                break;
            case WorkOutSettingCommon.CHANGE_TIME:

                int re = Integer.valueOf(result);
                if (re < 5) {
                    timeValue.setText("0");
                    workOutInfo.setTime("0");
                } else {
                    timeValue.setText(result);
                    workOutInfo.setTime(result);
                }
                workOutInfo.setGoalType(Constant.MODE_GOAL_TIME);
                break;
            case WorkOutSettingCommon.CHANGE_DISTANCE:
                distanceValue.setText(result);
                workOutInfo.setDistance(result);
                workOutInfo.setGoalType(Constant.MODE_GOAL_DISTANCE);
                break;
            case WorkOutSettingCommon.CHANGE_CALORIES:
                calValue.setText(result);
                workOutInfo.setCalories(result);
                workOutInfo.setGoalType(Constant.MODE_GOAL_CALORIES);
                break;
        }

        WorkOutSettingCommon.changeTg = WorkOutSettingCommon.RE_SET;
        optionBody.setVisibility(View.VISIBLE);
    }

    private void clearState() {
        timeValue.setText("—");
        distanceValue.setText("—");
        calValue.setText("—");
    }


    @OnClick({R.id.workout_setting_start})
    public void beginWorkOut() {
        workOutInfo.setWorkOutMode(Constant.MODE_GOAL);
        workOutInfo.setWorkOutModeName(Constant.WORK_OUT_MODE_GOAL);
        Intent intent = new Intent();
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
        } else {
        }
    }

    @OnClick(R.id.bottom_logo_tab_home)
    public void onBackHome() {
        finishActivity();
    }

}
