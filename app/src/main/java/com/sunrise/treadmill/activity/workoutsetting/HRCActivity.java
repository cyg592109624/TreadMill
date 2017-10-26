package com.sunrise.treadmill.activity.workoutsetting;

import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
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
 * Created by ChuHui on 2017/9/22.
 */

public class HRCActivity extends BaseActivity implements OnGenderReturn, OnKeyBoardReturn {

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


    @BindView(R.id.workout_edit_hrc60_value)
    TextView hrc60Value;
    @BindView(R.id.workout_edit_hrc80_value)
    TextView hrc80Value;
    @BindView(R.id.workout_edit_target_hr_value)
    TextView hrcTgValue;

    @BindView(R.id.workout_setting_hint)
    TextView settingHint;


    @BindView(R.id.include2)
    ConstraintLayout infoType1;

    @BindView(R.id.include3)
    ConstraintLayout infoType3;


    @BindView(R.id.workout_setting_next)
    ImageView nextBtn;

    @BindView(R.id.workout_setting_back)
    ImageView backBtn;


    @BindView(R.id.workout_setting_start)
    ImageView startBtn;


    @Override
    public int getLayoutId() {
        return R.layout.activity_workout_setting_hrc;
    }

    @Override
    public void recycleObject() {
        genderView.recycle();
        genderView = null;

        keyBoardView.recycle();
        keyBoardView = null;

        ageValue = null;
        weightValue = null;
        timeValue = null;
        hrc60Value = null;
        hrc80Value = null;
        hrcTgValue = null;
        settingHint = null;

        infoType1 = null;
        infoType3 = null;

        nextBtn = null;
        backBtn = null;
        startBtn = null;
    }

    @Override
    protected void setTextStyle() {
        List<TextView> txtList = new ArrayList<TextView>();

        txtList.add((TextView) findViewById(R.id.workout_setting_head_name));
        txtList.add((TextView) findViewById(R.id.workout_setting_head_hint));
        txtList.add(settingHint);

        txtList.add((TextView) findViewById(R.id.workout_edit_age));

        txtList.add((TextView) findViewById(R.id.workout_edit_weight));
        txtList.add((TextView) findViewById(R.id.workout_edit_weight_unit));

        txtList.add((TextView) findViewById(R.id.workout_edit_time));
        txtList.add((TextView) findViewById(R.id.workout_edit_time_unit));

        txtList.add((TextView) findViewById(R.id.workout_edit_hrc60));
        txtList.add((TextView) findViewById(R.id.workout_edit_hrc60_unit));

        txtList.add((TextView) findViewById(R.id.workout_edit_hrc80));
        txtList.add((TextView) findViewById(R.id.workout_edit_hrc80_unit));

        txtList.add((TextView) findViewById(R.id.workout_edit_target_hr));
        txtList.add((TextView) findViewById(R.id.workout_edit_target_hr_unit));

        txtList.add(ageValue);
        txtList.add(weightValue);
        txtList.add(timeValue);

        txtList.add(hrc60Value);
        txtList.add(hrc80Value);
        txtList.add(hrcTgValue);



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
        genderView.setOnGenderReturn(this);
        keyBoardView.setKeyBoardReturn(this);
        infoType1.setVisibility(View.VISIBLE);
        infoType3.setVisibility(View.GONE);
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
            case WorkOutSettingCommon.CHANGE_HRC_60:
                hrc60Value.setText(result);
                break;
            case WorkOutSettingCommon.CHANGE_HRC_80:
                hrc80Value.setText(result);
                break;
            case WorkOutSettingCommon.CHANGE_TARGET_HR:
                hrcTgValue.setText(result);
                break;
        }
    }

    @Override
    public void onKeyBoardClose() {
        switch (WorkOutSettingCommon.changeTg) {
            default:
                break;
            case WorkOutSettingCommon.CHANGE_AGE:
                TextUtils.changeTextColor(ageValue, ContextCompat.getColor(activityContext, R.color.factory_white));
                TextUtils.changeTextBackground(ageValue, R.mipmap.img_number_frame_1);
                break;
            case WorkOutSettingCommon.CHANGE_WEIGHT:
                TextUtils.changeTextColor(weightValue, ContextCompat.getColor(activityContext, R.color.factory_white));
                TextUtils.changeTextBackground(weightValue, R.mipmap.img_number_frame_1);
                break;
            case WorkOutSettingCommon.CHANGE_TIME:
                TextUtils.changeTextColor(timeValue, ContextCompat.getColor(activityContext, R.color.factory_white));
                TextUtils.changeTextBackground(timeValue, R.mipmap.img_number_frame_1);
                break;
            case WorkOutSettingCommon.CHANGE_HRC_60:
                TextUtils.changeTextColor(hrc60Value, ContextCompat.getColor(getApplicationContext(), R.color.factory_white));
                TextUtils.changeTextBackground(hrc60Value, R.mipmap.img_number_frame_1);
                break;
            case WorkOutSettingCommon.CHANGE_HRC_80:
                TextUtils.changeTextColor(hrc80Value, ContextCompat.getColor(activityContext, R.color.factory_white));
                TextUtils.changeTextBackground(hrc80Value, R.mipmap.img_number_frame_1);
                break;
            case WorkOutSettingCommon.CHANGE_TARGET_HR:
                TextUtils.changeTextColor(hrcTgValue, ContextCompat.getColor(activityContext, R.color.factory_white));
                TextUtils.changeTextBackground(hrcTgValue, R.mipmap.img_number_frame_1);
                break;
        }
        WorkOutSettingCommon.changeTg = WorkOutSettingCommon.RE_SET;
        isShowingKeyBoard = false;
        keyBoardView.setVisibility(View.GONE);
        genderView.setVisibility(View.VISIBLE);
        nextBtn.setEnabled(true);
        backBtn.setEnabled(true);
        //这个按钮还需要进行判断 这里暂时不做处理
        startBtn.setEnabled(true);

    }

    private boolean isShowingKeyBoard = false;

    @OnClick({R.id.workout_edit_age_value, R.id.workout_edit_weight_value, R.id.workout_edit_time_value,
            R.id.workout_edit_hrc60_value, R.id.workout_edit_hrc80_value, R.id.workout_edit_target_hr_value})
    public void changValue(View view) {
        if (isShowingKeyBoard) {
            return;
        }
        isShowingKeyBoard = true;
        keyBoardView.setVisibility(View.VISIBLE);
        genderView.setVisibility(View.GONE);

        nextBtn.setEnabled(false);
        backBtn.setEnabled(false);
        startBtn.setEnabled(false);
        switch (view.getId()) {
            default:
                break;
            case R.id.workout_edit_age_value:
                WorkOutSettingCommon.changeTg = WorkOutSettingCommon.CHANGE_AGE;
                keyBoardView.setTitleImage(R.mipmap.tv_keybord_age);
                TextUtils.changeTextColor(ageValue, ContextCompat.getColor(activityContext, R.color.factory_tabs_on));
                TextUtils.changeTextBackground(ageValue, R.mipmap.img_number_frame_2);
                break;
            case R.id.workout_edit_weight_value:
                WorkOutSettingCommon.changeTg = WorkOutSettingCommon.CHANGE_WEIGHT;
                keyBoardView.setTitleImage(R.mipmap.tv_keybord_weight);
                TextUtils.changeTextColor(weightValue, ContextCompat.getColor(activityContext, R.color.factory_tabs_on));
                TextUtils.changeTextBackground(weightValue, R.mipmap.img_number_frame_2);
                break;
            case R.id.workout_edit_time_value:
                WorkOutSettingCommon.changeTg = WorkOutSettingCommon.CHANGE_TIME;
                keyBoardView.setTitleImage(R.mipmap.tv_keybord_time);
                TextUtils.changeTextColor(timeValue, ContextCompat.getColor(activityContext, R.color.factory_tabs_on));
                TextUtils.changeTextBackground(timeValue, R.mipmap.img_number_frame_2);
                break;
            case R.id.workout_edit_hrc60_value:
                WorkOutSettingCommon.changeTg = WorkOutSettingCommon.CHANGE_HRC_60;
                keyBoardView.setTitleImage(R.mipmap.tv_keybord_time);
                TextUtils.changeTextColor(hrc60Value, ContextCompat.getColor(activityContext, R.color.factory_tabs_on));
                TextUtils.changeTextBackground(hrc60Value, R.mipmap.img_number_frame_2);
                break;
            case R.id.workout_edit_hrc80_value:
                WorkOutSettingCommon.changeTg = WorkOutSettingCommon.CHANGE_HRC_80;
                keyBoardView.setTitleImage(R.mipmap.tv_keybord_time);
                TextUtils.changeTextColor(hrc80Value, ContextCompat.getColor(activityContext, R.color.factory_tabs_on));
                TextUtils.changeTextBackground(hrc80Value, R.mipmap.img_number_frame_2);
                break;
            case R.id.workout_edit_target_hr_value:
                WorkOutSettingCommon.changeTg = WorkOutSettingCommon.CHANGE_TARGET_HR;
                keyBoardView.setTitleImage(R.mipmap.tv_keybord_time);
                TextUtils.changeTextColor(hrcTgValue, ContextCompat.getColor(activityContext, R.color.factory_tabs_on));
                TextUtils.changeTextBackground(hrcTgValue, R.mipmap.img_number_frame_2);
                break;
        }
    }

    @OnClick({R.id.workout_setting_next})
    public void onNextEdit() {
        if (isShowingKeyBoard) {
            return;
        }
        settingHint.setText(R.string.workout_mode_hint_c);

        infoType1.setVisibility(View.GONE);
        infoType3.setVisibility(View.VISIBLE);

        nextBtn.setVisibility(View.GONE);
        backBtn.setVisibility(View.VISIBLE);

    }

    @OnClick({R.id.workout_setting_back})
    public void onBackEdit() {
        if (isShowingKeyBoard) {
            return;
        }
        settingHint.setText(R.string.workout_mode_hint_b);

        infoType1.setVisibility(View.VISIBLE);
        infoType3.setVisibility(View.GONE);

        nextBtn.setVisibility(View.VISIBLE);
        backBtn.setVisibility(View.GONE);

    }


    @OnClick({R.id.workout_setting_start})
    public void beginWorkOut() {

    }

    @OnClick(R.id.bottom_logo_tab_home)
    public void onBackHome() {
        finishActivity();
    }
}
