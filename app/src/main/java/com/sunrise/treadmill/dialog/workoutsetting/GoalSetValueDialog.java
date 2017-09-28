package com.sunrise.treadmill.dialog.workoutsetting;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import com.sunrise.treadmill.Constant;
import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.activity.workoutsetting.GoalActivity;
import com.sunrise.treadmill.base.BaseDialogFragment;
import com.sunrise.treadmill.interfaces.OnGoalSetValueReturn;
import com.sunrise.treadmill.interfaces.OnKeyBoardReturn;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;
import com.sunrise.treadmill.views.MyKeyBoardView;

import butterknife.BindView;

/**
 * Created by ChuHui on 2017/9/22.
 */

public class GoalSetValueDialog extends BaseDialogFragment implements OnKeyBoardReturn {
    public static final String TAG = "GoalSetValueDialog";
    @BindView(R.id.dialog_workout_goal_value)
    TextView editValue;
    @BindView(R.id.dialog_workout_goal_keyboard)
    MyKeyBoardView keyBoardView;

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
    protected void init() {
        keyBoardView.setKeyBoardReturn(this);
        onGoalSetValueReturn = (OnGoalSetValueReturn) getActivity();
        Bundle bundle = getArguments();
        String setType = "";
        String oldValue = "";
        if (bundle != null) {
            setType = (String) bundle.get(GoalActivity.SetTg);
            oldValue = (String) bundle.get(GoalActivity.SetTgValue);
        }
        editValue.setText(oldValue);
        if (!setType.equals("")) {
            if (setType.equals(GoalActivity.SetTimeValue)) {
                editValue.setBackground(getResources().getDrawable(R.drawable.btn_goal_time_3));
            } else if (setType.equals(GoalActivity.SetDistanceValue)) {
                //这还需判断单位
                if (GlobalSetting.UnitType.equals(Constant.unitType_MILE)) {
                    editValue.setBackground(getResources().getDrawable(R.drawable.btn_goal_distance_mile_3));
                } else if(GlobalSetting.UnitType.equals(Constant.unitType_KM)) {
                    editValue.setBackground(getResources().getDrawable(R.drawable.btn_goal_distance_km_3));
                }
            } else if (setType.equals(GoalActivity.SetCalValue)) {
                editValue.setBackground(getResources().getDrawable(R.drawable.btn_goal_calories_3));
            }
        }
        ((TextView) keyBoardView.findViewById(R.id.key_board_edit_value)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                editValue.setText(editable.toString());
            }
        });
    }

    @Override
    protected void setTextStyle() {
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(editValue, TextUtils.Microsoft(getContext()));
        } else {
            TextUtils.setTextTypeFace(editValue, TextUtils.Arial(getContext()));
        }
    }

    @Override
    public void onKeyBoardEnter(String result) {
        onGoalSetValueReturn.onGoalSetValueResult(result);
    }

    @Override
    public void onKeyBoardClose() {
        dismiss();
    }
}
