package com.sunrise.treadmill.dialog.workout.setting;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.sunrise.treadmill.Constant;
import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.activity.workout.setting.GoalActivity;
import com.sunrise.treadmill.activity.workout.WorkOutSettingCommon;
import com.sunrise.treadmill.base.BaseDialogFragment;
import com.sunrise.treadmill.interfaces.workout.setting.OnGoalSetValueReturn;
import com.sunrise.treadmill.interfaces.workout.setting.OnKeyBoardReturn;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;
import com.sunrise.treadmill.views.NumberKeyBoardView;

import butterknife.BindView;

/**
 * Created by ChuHui on 2017/9/22.
 */

public class GoalSetValueDialog extends BaseDialogFragment implements OnKeyBoardReturn {
    @BindView(R.id.dialog_workout_goal_value)
    TextView editValue;
    @BindView(R.id.dialog_workout_goal_keyboard)
    NumberKeyBoardView keyBoardView;

    private OnGoalSetValueReturn onGoalSetValueReturn;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getContext(), R.style.Dialog_No_BG);
        return dialog;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_workout_setting_goal;
    }

    @Override
    public void recycleObject() {
        editValue = null;
        keyBoardView.recycle();
        keyBoardView = null;
        onGoalSetValueReturn = null;
    }

    @Override
    protected void init() {
        keyBoardView.setKeyBoardReturn(GoalSetValueDialog.this);
        onGoalSetValueReturn = (OnGoalSetValueReturn) getActivity();
        Bundle bundle = getArguments();
        int changeTg = 0;
        String oldValue = "";
        if (bundle != null) {
            changeTg = bundle.getInt(GoalActivity.CHANGE_TG, WorkOutSettingCommon.RE_SET);
            keyBoardView.setChangeType(changeTg);
            oldValue = (String) bundle.get(GoalActivity.CHANGE_TG_VALUE);
        }
        editValue.setText(oldValue);
        Drawable drawable = null;
        switch (changeTg) {
            default:
                break;
            case WorkOutSettingCommon.CHANGE_TIME:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.btn_goal_time_3);
                break;
            case WorkOutSettingCommon.CHANGE_DISTANCE:
                if (GlobalSetting.UnitType.equals(Constant.UNIT_TYPE_METRIC)) {
                    drawable = ContextCompat.getDrawable(getContext(), R.drawable.btn_goal_distance_km_3);
                } else {
                    drawable = ContextCompat.getDrawable(getContext(), R.drawable.btn_goal_distance_mile_3);
                }
                break;
            case WorkOutSettingCommon.CHANGE_CALORIES:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.btn_goal_calories_3);
                break;
        }
        if (drawable != null) {
            editValue.setBackground(drawable);
        }
    }

    @Override
    protected void setTextStyle() {
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(editValue, TextUtils.Microsoft());
        } else {
            TextUtils.setTextTypeFace(editValue, TextUtils.Arial());
        }
    }

    @Override
    public void onKeyBoardEnter(String result) {
        onGoalSetValueReturn.onGoalSetValueResult(result);
    }

    @Override
    public void onKeyBoardClose() {
        dismiss();
        onGoalSetValueReturn.onGoalSetValueResult("");
    }
}
