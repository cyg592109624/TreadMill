package com.sunrise.treadmill.activity.workoutsetting;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.activity.workoutrunning.HillRunningActivity;
import com.sunrise.treadmill.activity.workoutrunning.HillRunningActivityZh;
import com.sunrise.treadmill.base.BaseActivity;
import com.sunrise.treadmill.interfaces.workout.setting.OnGenderReturn;
import com.sunrise.treadmill.interfaces.workout.setting.OnKeyBoardReturn;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;
import com.sunrise.treadmill.views.workout.setting.MyGenderView;
import com.sunrise.treadmill.views.workout.setting.MyKeyBoardView;
import com.sunrise.treadmill.views.workout.setting.WorkOutSettingHead;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/20.
 */

public class HillActivity extends BaseActivity implements OnGenderReturn, OnKeyBoardReturn {
    @BindView(R.id.workout_mode_head)
    WorkOutSettingHead headView;

    @BindView(R.id.workout_mode_gender_select)
    MyGenderView genderView;

    @BindView(R.id.workout_mode_keyboard)
    MyKeyBoardView keyBoardView;

    @BindView(R.id.workout_edit_age_value)
    TextView ageValue;
    @BindView(R.id.workout_edit_weight_value)
    TextView weightValue;
    @BindView(R.id.workout_edit_time_value)
    TextView timeValue;

    @BindView(R.id.workout_mode_start_1)
    ImageView startBtn;


    @Override
    public int getLayoutId() {
        return R.layout.activity_workout_setting_hill;
    }

    @Override
    public void clearObj() {
        headView=null;
        genderView=null;
        keyBoardView=null;
        ageValue=null;
        headView=null;
        weightValue=null;
        timeValue=null;
        startBtn=null;
        setContentView(R.layout.view_null);
    }

    @Override
    protected void setTextStyle() {
        List<TextView> txtList = new ArrayList<TextView>();
        txtList.add((TextView) headView.findViewById(R.id.workout_head_mode));
        txtList.add((TextView) headView.findViewById(R.id.workout_head_hint));

        txtList.add((TextView) findViewById(R.id.workout_edit_age));
        txtList.add((TextView) findViewById(R.id.workout_edit_weight));
        txtList.add((TextView) findViewById(R.id.workout_edit_weight_unit));

        txtList.add((TextView) findViewById(R.id.workout_edit_time));
        txtList.add((TextView) findViewById(R.id.workout_edit_time_unit));

        txtList.add((TextView) findViewById(R.id.workout_edit_start_hint_1));

        txtList.add(ageValue);
        txtList.add(weightValue);
        txtList.add(timeValue);
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList, TextUtils.Microsoft(this));
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.Arial(this));
        }
    }

    @Override
    protected void init() {
        headView.setHeadMsg(getResources().getString(R.string.workout_mode_hill), getResources().getString(R.string.workout_mode_hint_f), R.mipmap.img_program_hill_icon);
        genderView.setOnGenderReturn(this);
        keyBoardView.setKeyBoardReturn(this);
    }

    @Override
    public void genderReturn(int gender) {

    }

    @Override
    public void onKeyBoardEnter(String result) {
        switch (WorkOutSettingCommon.changeTg) {
            default:
                break;
            case WorkOutSettingCommon.CHANGE_AGE:
                ageValue.setText(result);
                break;
            case WorkOutSettingCommon.CHANGE_WEIGHT:
                weightValue.setText(result);
                break;
            case WorkOutSettingCommon.CHANGE_TIME:
                timeValue.setText(result);
                break;
        }
    }

    @Override
    public void onKeyBoardClose() {
        switch (WorkOutSettingCommon.changeTg) {
            default:
                break;
            case WorkOutSettingCommon.CHANGE_AGE:
                TextUtils.changeTextColor(ageValue, ContextCompat.getColor(getApplicationContext(),R.color.factory_white));
                TextUtils.changeTextBackground(ageValue, R.mipmap.img_number_frame_1);
                break;
            case WorkOutSettingCommon.CHANGE_WEIGHT:
                TextUtils.changeTextColor(weightValue, ContextCompat.getColor(getApplicationContext(),R.color.factory_white));
                TextUtils.changeTextBackground(weightValue, R.mipmap.img_number_frame_1);
                break;
            case WorkOutSettingCommon.CHANGE_TIME:
                TextUtils.changeTextColor(timeValue, ContextCompat.getColor(getApplicationContext(),R.color.factory_white));
                TextUtils.changeTextBackground(timeValue, R.mipmap.img_number_frame_1);
                break;
        }
        WorkOutSettingCommon.changeTg = WorkOutSettingCommon.RE_SET;
        isShowingKeyBoard = false;
        keyBoardView.setVisibility(View.GONE);
        genderView.setVisibility(View.VISIBLE);
        startBtn.setEnabled(true);
    }

    private boolean isShowingKeyBoard = false;
    @OnClick({R.id.workout_edit_age_value, R.id.workout_edit_weight_value, R.id.workout_edit_time_value})
    public void changValue(View view) {
        if (isShowingKeyBoard) {
            return;
        }
        isShowingKeyBoard = true;
        keyBoardView.setVisibility(View.VISIBLE);
        genderView.setVisibility(View.GONE);
        startBtn.setEnabled(false);
        switch (view.getId()) {
            default:
                break;
            case R.id.workout_edit_age_value:
                WorkOutSettingCommon.changeTg = WorkOutSettingCommon.CHANGE_AGE;
                keyBoardView.setTitleImage(R.mipmap.tv_keybord_age);
                TextUtils.changeTextColor(ageValue, ContextCompat.getColor(getApplicationContext(),R.color.factory_tabs_on));
                TextUtils.changeTextBackground(ageValue, R.mipmap.img_number_frame_2);
                break;
            case R.id.workout_edit_weight_value:
                WorkOutSettingCommon.changeTg = WorkOutSettingCommon.CHANGE_WEIGHT;
                keyBoardView.setTitleImage(R.mipmap.tv_keybord_weight);
                TextUtils.changeTextColor(weightValue, ContextCompat.getColor(getApplicationContext(),R.color.factory_tabs_on));
                TextUtils.changeTextBackground(weightValue, R.mipmap.img_number_frame_2);
                break;
            case R.id.workout_edit_time_value:
                WorkOutSettingCommon.changeTg = WorkOutSettingCommon.CHANGE_TIME;
                keyBoardView.setTitleImage(R.mipmap.tv_keybord_time);
                TextUtils.changeTextColor(timeValue, ContextCompat.getColor(getApplicationContext(),R.color.factory_tabs_on));
                TextUtils.changeTextBackground(timeValue, R.mipmap.img_number_frame_2);
                break;
        }
    }

    @OnClick({R.id.workout_mode_start_1})
    public void beginWorkOut() {
        Intent intent = new Intent();
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            intent.setClass(HillActivity.this, HillRunningActivityZh.class);
        } else {
            intent.setClass(HillActivity.this, HillRunningActivity.class);
        }
        startActivity(intent);
    }

    @OnClick(R.id.bottom_logo_tab_home)
    public void onBackHome() {
        finishActivity();
    }
}
