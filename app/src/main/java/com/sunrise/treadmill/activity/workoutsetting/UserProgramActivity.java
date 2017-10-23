package com.sunrise.treadmill.activity.workoutsetting;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseActivity;
import com.sunrise.treadmill.interfaces.workout.setting.OnGenderReturn;
import com.sunrise.treadmill.interfaces.workout.setting.OnKeyBoardReturn;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;
import com.sunrise.treadmill.views.workout.LevelView;
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

public class UserProgramActivity extends BaseActivity implements OnGenderReturn, OnKeyBoardReturn {

    @BindView(R.id.workout_mode_head)
    WorkOutSettingHead headView;

    @BindView(R.id.workout_option_body)
    LinearLayout optionBody;

    @BindView(R.id.workout_option_body2)
    LinearLayout optionBody2;

    @BindView(R.id.workout_mode_gender_select)
    MyGenderView genderView;

    @BindView(R.id.workout_mode_keyboard)
    MyKeyBoardView keyBoardView;

    @BindView(R.id.workout_edit_level_view)
    LevelView levelView;


    @BindView(R.id.workout_edit_age_value)
    TextView ageValue;
    @BindView(R.id.workout_edit_weight_value)
    TextView weightValue;
    @BindView(R.id.workout_edit_time_value)
    TextView timeValue;


    @BindView(R.id.workout_mode_next_2)
    ImageView nextImage;
    @BindView(R.id.workout_mode_back_2)
    ImageView backImage;
    @BindView(R.id.workout_mode_start_2)
    ImageView startImage;

    @Override
    public int getLayoutId() {
        return R.layout.activity_workout_setting_user_program;
    }

    @Override
    public void recycleObject() {
        headView.recycle();
        headView = null;

        genderView.recycle();
        genderView = null;

        keyBoardView.recycle();
        keyBoardView = null;

        optionBody = null;
        optionBody2 = null;

        levelView.recycle();
        levelView = null;

        ageValue = null;
        weightValue = null;
        timeValue = null;

        nextImage = null;
        backImage = null;
        startImage = null;
    }

    @Override
    protected void init() {
        headView.setHeadMsg(getResources().getString(R.string.workout_mode_user), getResources().getString(R.string.workout_mode_hint_f), R.mipmap.img_program_user_program_icon);
        genderView.setOnGenderReturn(UserProgramActivity.this);
        keyBoardView.setKeyBoardReturn(UserProgramActivity.this);
//        levelView.setColumnMargin(5f);
//        levelView.setHintText(getResources().getString(R.string.workout_mode_hint_e));
    }

    @Override
    protected void setTextStyle() {
        List<TextView> txtList = new ArrayList<TextView>();

        txtList.add((TextView) findViewById(R.id.workout_edit_age));

        txtList.add((TextView) findViewById(R.id.workout_edit_weight));
        txtList.add((TextView) findViewById(R.id.workout_edit_weight_unit));

        txtList.add((TextView) findViewById(R.id.workout_edit_time));
        txtList.add((TextView) findViewById(R.id.workout_edit_time_unit));

        txtList.add((TextView) findViewById(R.id.workout_edit_start_hint_2));

        txtList.add((TextView) findViewById(R.id.workout_edit_start_hint_3));

        txtList.add(ageValue);
        txtList.add(weightValue);
        txtList.add(timeValue);
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList, TextUtils.Microsoft(activityContext));
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.Arial(activityContext));
        }
        txtList.clear();
        txtList = null;
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
        }

        WorkOutSettingCommon.changeTg = WorkOutSettingCommon.RE_SET;
        isShowingKeyBoard = false;
        keyBoardView.setVisibility(View.GONE);
        genderView.setVisibility(View.VISIBLE);
        nextImage.setEnabled(true);
        backImage.setEnabled(true);
        //这个按钮还需要进行判断 这里暂时不做处理
        startImage.setEnabled(true);
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

        nextImage.setEnabled(false);
        backImage.setEnabled(false);
        startImage.setEnabled(false);
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
        }
    }

    @OnClick({R.id.workout_mode_next_2})
    public void onNextEdit() {
        if (isShowingKeyBoard) {
            return;
        }
        headView.setWorkOutHint(getResources().getString(R.string.workout_mode_hint_e));

        optionBody.setVisibility(View.GONE);
        optionBody2.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.workout_mode_back_3})
    public void onBackEdit() {
        if (isShowingKeyBoard) {
            return;
        }
        headView.setWorkOutHint(getResources().getString(R.string.workout_mode_hint_f));

        optionBody.setVisibility(View.VISIBLE);
        optionBody2.setVisibility(View.GONE);
    }


    @OnClick({R.id.workout_mode_start_2, R.id.workout_mode_start_3})
    public void beginWorkOut() {

    }

    @OnClick(R.id.bottom_logo_tab_home)
    public void onBackHome() {
        finishActivity();
    }
}
