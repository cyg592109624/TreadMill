package com.sunrise.treadmill.activity.workout;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseActivity;
import com.sunrise.treadmill.interfaces.OnGenderReturn;
import com.sunrise.treadmill.interfaces.OnKeyBoardReturn;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;
import com.sunrise.treadmill.views.LevelView;
import com.sunrise.treadmill.views.MyGenderView;
import com.sunrise.treadmill.views.MyKeyBoardView;
import com.sunrise.treadmill.views.MyWorkOutHead;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/22.
 */

public class UserProgramActivity extends BaseActivity implements OnGenderReturn, OnKeyBoardReturn {


    @BindView(R.id.workout_mode_head)
    MyWorkOutHead headView;

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
        return R.layout.activity_workout_user_program;
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

        txtList.add((TextView) findViewById(R.id.workout_edit_level_view_hint));

        txtList.add((TextView) findViewById(R.id.workout_edit_start_hint_2));

        txtList.add((TextView) findViewById(R.id.workout_edit_start_hint_3));

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
        headView.setHeadMsg(getResources().getString(R.string.workout_mode_user), getResources().getString(R.string.workout_mode_hint_f), R.mipmap.img_program_user_program_icon);
        genderView.setOnGenderReturn(this);
        keyBoardView.setKeyBoardReturn(this);
    }

    @Override
    public void genderReturn(int gender) {

    }


    @Override
    public void onEnter(String result) {
        switch (reSetTG) {
            case reSetAge:
                ageValue.setText(result);
                break;
            case reSetWeight:
                weightValue.setText(result);
                break;
            case reSetTime:
                timeValue.setText(result);
                break;
        }
    }

    @Override
    public void onKeyBoardHide() {
        switch (reSetTG) {
            case reSetAge:
                TextUtils.changeTextColor(ageValue, getResources().getColor(R.color.factory_white));
                TextUtils.changeTextBackground(ageValue, R.mipmap.img_number_frame_1);
                break;
            case reSetWeight:
                TextUtils.changeTextColor(weightValue, getResources().getColor(R.color.factory_white));
                TextUtils.changeTextBackground(weightValue, R.mipmap.img_number_frame_1);
                break;
            case reSetTime:
                TextUtils.changeTextColor(timeValue, getResources().getColor(R.color.factory_white));
                TextUtils.changeTextBackground(timeValue, R.mipmap.img_number_frame_1);
                break;
        }

        reSetTG = reSetTG;
        isShowingKeyBoard = false;
        keyBoardView.setVisibility(View.GONE);
        genderView.setVisibility(View.VISIBLE);
        nextImage.setEnabled(true);
        backImage.setEnabled(true);
        //这个按钮还需要进行判断 这里暂时不做处理
        startImage.setEnabled(true);
    }


    private boolean isShowingKeyBoard = false;
    private static int reSetTG = -1;
    private static final int reSetAge = 1001;
    private static final int reSetWeight = 1002;
    private static final int reSetTime = 1003;

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
                reSetTG = reSetAge;
                keyBoardView.setTitleImage(R.mipmap.tv_keybord_age);
                TextUtils.changeTextColor(ageValue, getResources().getColor(R.color.factory_tabs_on));
                TextUtils.changeTextBackground(ageValue, R.mipmap.img_number_frame_2);
                break;
            case R.id.workout_edit_weight_value:
                reSetTG = reSetWeight;
                keyBoardView.setTitleImage(R.mipmap.tv_keybord_weight);
                TextUtils.changeTextColor(weightValue, getResources().getColor(R.color.factory_tabs_on));
                TextUtils.changeTextBackground(weightValue, R.mipmap.img_number_frame_2);
                break;
            case R.id.workout_edit_time_value:
                reSetTG = reSetTime;
                keyBoardView.setTitleImage(R.mipmap.tv_keybord_time);
                TextUtils.changeTextColor(timeValue, getResources().getColor(R.color.factory_tabs_on));
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


    @OnClick({R.id.workout_mode_start_2,R.id.workout_mode_start_3})
    public void beginWorkOut() {

    }

    @OnClick(R.id.bottom_logo_tab_home)
    public void onBackHome() {
        finishActivity();
    }
}
