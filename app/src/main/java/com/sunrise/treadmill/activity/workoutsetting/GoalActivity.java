package com.sunrise.treadmill.activity.workoutsetting;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

    @BindView(R.id.workout_mode_head)
    WorkOutSettingHead headView;

    @BindView(R.id.workout_mode_goal_time)
    TextView timeValue;

    @BindView(R.id.workout_mode_goal_distance)
    TextView distanceValue;

    @BindView(R.id.workout_mode_goal_calories)
    TextView calValue;

    private GoalSetValueDialog dialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_workout_setting_goal;
    }

    @Override
    public void recycleObject() {
        headView.recycle();
        headView = null;

        timeValue = null;
        distanceValue = null;
        calValue = null;

//        dialog.onDestroyView();
        dialog = null;
    }

    @Override
    protected void init() {
        headView.setHeadMsg(getResources().getString(R.string.workout_mode_goal), getResources().getString(R.string.workout_mode_hint_g), R.mipmap.img_program_goal_icon);
    }

    @Override
    protected void setTextStyle() {
        List<TextView> txtList = new ArrayList<TextView>();

        txtList.add((TextView) findViewById(R.id.workout_mode_goal_hint));

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
            if (dialog == null) {
                dialog = new GoalSetValueDialog();
            }
            dialog.setArguments(bundle);
            dialog.show(fragmentManager, GoalSetValueDialog.TAG);
        }
    }

    @Override
    public void onGoalSetValueResult(String result) {
        switch (WorkOutSettingCommon.changeTg) {
            default:
                break;
            case WorkOutSettingCommon.CHANGE_TIME:
                timeValue.setText(result);
                break;
            case WorkOutSettingCommon.CHANGE_DISTANCE:
                distanceValue.setText(result);
                break;
            case WorkOutSettingCommon.CHANGE_CALORIES:
                calValue.setText(result);
                break;
        }
        WorkOutSettingCommon.changeTg = WorkOutSettingCommon.RE_SET;
    }

    @OnClick({R.id.workout_mode_goal_start})
    public void beginWorkOut() {

    }

    @OnClick(R.id.bottom_logo_tab_home)
    public void onBackHome() {
        finishActivity();
    }

}
