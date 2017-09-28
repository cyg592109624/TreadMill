package com.sunrise.treadmill.activity.workoutsetting;

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

public class HRCActivity extends BaseActivity implements OnGenderReturn, OnKeyBoardReturn {
    @BindView(R.id.workout_mode_head)
    MyWorkOutHead headView;

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


    @BindView(R.id.workout_edit_info_1)
    LinearLayout infoType1;
    @BindView(R.id.workout_edit_info_3)
    LinearLayout infoType3;


    @BindView(R.id.workout_mode_next_2)
    ImageView nextImage;
    @BindView(R.id.workout_mode_back_2)
    ImageView backImage;
    @BindView(R.id.workout_mode_start_2)
    ImageView startImage;

    @Override
    public int getLayoutId() {
        return R.layout.activity_workout_setting_hrc;
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

        txtList.add((TextView) findViewById(R.id.workout_edit_hrc60));
        txtList.add((TextView) findViewById(R.id.workout_edit_hrc60_unit));

        txtList.add((TextView) findViewById(R.id.workout_edit_hrc80));
        txtList.add((TextView) findViewById(R.id.workout_edit_hrc80_unit));

        txtList.add((TextView) findViewById(R.id.workout_edit_target_hr));
        txtList.add((TextView) findViewById(R.id.workout_edit_target_hr_unit));

        txtList.add((TextView) findViewById(R.id.workout_edit_start_hint_2));

        txtList.add(ageValue);
        txtList.add(weightValue);
        txtList.add(timeValue);

        txtList.add(hrc60Value);
        txtList.add(hrc80Value);
        txtList.add(hrcTgValue);
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList, TextUtils.Microsoft(this));
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.Arial(this));
        }
    }

    @Override
    protected void init() {
        headView.setHeadMsg(getResources().getString(R.string.workout_mode_hrc), getResources().getString(R.string.workout_mode_hint_f), R.mipmap.img_program_hrc_icon);
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
        switch (reSetTG) {
            default:
                break;
            case reSetAge:
                ageValue.setText(result);
                break;
            case reSetWeight:
                weightValue.setText(result);
                break;
            case reSetTime:
                timeValue.setText(result);
                break;
            case reSetHRC60:
                hrc60Value.setText(result);
                break;
            case reSetHRC80:
                hrc80Value.setText(result);
                break;
            case reSetTargetHR:
                hrcTgValue.setText(result);
                break;
        }
    }

    @Override
    public void onKeyBoardClose() {
        switch (reSetTG) {
            default:
                break;
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
            case reSetHRC60:
                TextUtils.changeTextColor(hrc60Value, getResources().getColor(R.color.factory_white));
                TextUtils.changeTextBackground(hrc60Value, R.mipmap.img_number_frame_1);
                break;
            case reSetHRC80:
                TextUtils.changeTextColor(hrc80Value, getResources().getColor(R.color.factory_white));
                TextUtils.changeTextBackground(hrc80Value, R.mipmap.img_number_frame_1);
                break;
            case reSetTargetHR:
                TextUtils.changeTextColor(hrcTgValue, getResources().getColor(R.color.factory_white));
                TextUtils.changeTextBackground(hrcTgValue, R.mipmap.img_number_frame_1);
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

    private static final int reSetHRC60 = 1004;
    private static final int reSetHRC80 = 1005;
    private static final int reSetTargetHR = 1006;

    @OnClick({R.id.workout_edit_age_value, R.id.workout_edit_weight_value, R.id.workout_edit_time_value,
            R.id.workout_edit_hrc60_value, R.id.workout_edit_hrc80_value, R.id.workout_edit_target_hr_value})
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
            case R.id.workout_edit_hrc60_value:
                reSetTG = reSetHRC60;
                keyBoardView.setTitleImage(R.mipmap.tv_keybord_time);
                TextUtils.changeTextColor(hrc60Value, getResources().getColor(R.color.factory_tabs_on));
                TextUtils.changeTextBackground(hrc60Value, R.mipmap.img_number_frame_2);
                break;
            case R.id.workout_edit_hrc80_value:
                reSetTG = reSetHRC80;
                keyBoardView.setTitleImage(R.mipmap.tv_keybord_time);
                TextUtils.changeTextColor(hrc80Value, getResources().getColor(R.color.factory_tabs_on));
                TextUtils.changeTextBackground(hrc80Value, R.mipmap.img_number_frame_2);
                break;
            case R.id.workout_edit_target_hr_value:
                reSetTG = reSetTargetHR;
                keyBoardView.setTitleImage(R.mipmap.tv_keybord_time);
                TextUtils.changeTextColor(hrcTgValue, getResources().getColor(R.color.factory_tabs_on));
                TextUtils.changeTextBackground(hrcTgValue, R.mipmap.img_number_frame_2);
                break;
        }
    }

    @OnClick({R.id.workout_mode_next_2})
    public void onNextEdit() {
        if (isShowingKeyBoard) {
            return;
        }
        infoType1.setVisibility(View.GONE);
        infoType3.setVisibility(View.VISIBLE);
        nextImage.setVisibility(View.GONE);
        backImage.setVisibility(View.VISIBLE);

    }

    @OnClick({R.id.workout_mode_back_2})
    public void onBackEdit() {
        if (isShowingKeyBoard) {
            return;
        }
        infoType1.setVisibility(View.VISIBLE);
        infoType3.setVisibility(View.GONE);
        nextImage.setVisibility(View.VISIBLE);
        backImage.setVisibility(View.GONE);

    }


    @OnClick({R.id.workout_mode_start_2})
    public void beginWorkOut() {

    }

    @OnClick(R.id.bottom_logo_tab_home)
    public void onBackHome() {
        finishActivity();
    }
}
