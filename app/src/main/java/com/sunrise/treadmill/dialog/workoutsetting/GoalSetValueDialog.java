package com.sunrise.treadmill.dialog.workoutsetting;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import com.sunrise.treadmill.Constant;
import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.activity.workoutsetting.GoalActivity;
import com.sunrise.treadmill.activity.workoutsetting.WorkOutSettingCommon;
import com.sunrise.treadmill.base.BaseDialogFragment;
import com.sunrise.treadmill.interfaces.workoutsetting.OnGoalSetValueReturn;
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
        int changeTg = 0;
        String oldValue = "";
        if (bundle != null) {
            changeTg = bundle.getInt(GoalActivity.CHANGE_TG, WorkOutSettingCommon.RE_SET);
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
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.btn_goal_distance_mile_3);
                break;
            case WorkOutSettingCommon.CHANGE_CALORIES:
                if (GlobalSetting.UnitType.equals(Constant.UNIT_TYPE_MILE)) {
                    drawable = ContextCompat.getDrawable(getContext(), R.drawable.btn_goal_distance_mile_3);
                } else if (GlobalSetting.UnitType.equals(Constant.UNIT_TYPE_KM)) {

                    drawable = ContextCompat.getDrawable(getContext(), R.drawable.btn_goal_distance_km_3);
                }
                break;
        }
        if (drawable != null) {
            editValue.setBackground(drawable);
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
