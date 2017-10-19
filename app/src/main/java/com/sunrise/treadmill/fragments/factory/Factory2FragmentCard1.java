package com.sunrise.treadmill.fragments.factory;

import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragment;
import com.sunrise.treadmill.interfaces.workout.setting.OnKeyBoardReturn;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;
import com.sunrise.treadmill.views.workout.setting.MyKeyBoardView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/14.
 */

public class Factory2FragmentCard1 extends BaseFragment implements OnKeyBoardReturn {
    @BindView(R.id.factory2_card1_1)
    ConstraintLayout leftLayout;

    @BindView(R.id.factory2_card1_2_ctrl_page_toggle)
    ToggleButton ctrlPageToggle;

    @BindView(R.id.factory2_card1_2_level_value)
    TextView levelValue;

    @BindView(R.id.factory2_card1_2_pwm_value)
    TextView pwmValue;

    @BindView(R.id.factory2_card1_1_keyboard)
    MyKeyBoardView keyBoardView;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_factory2_card_1;
    }

    @Override
    public void clearObj() {
        leftLayout=null;
        ctrlPageToggle=null;
        levelValue=null;
        pwmValue=null;
        keyBoardView=null;
        parentView = null;
    }

    @Override
    protected void setTextStyle() {
        List<TextView> txtList = new ArrayList<TextView>();
        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card1_1_display_mode));
        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card1_1_pause_mode));
        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card1_1_key_tone));
        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card1_1_buzzer));
        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card1_1_child_lock));

        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card1_2_ctrl_page));
        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card1_2_key_test));
        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card1_2_brake_test));

        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card1_2_level));
        txtList.add(levelValue);

        txtList.add((TextView) getParentView().findViewById(R.id.factory2_card1_2_pwm));
        txtList.add(pwmValue);

        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList, TextUtils.MicrosoftBold(getContext()));
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.ArialBold(getContext()));
        }
    }

    @Override
    protected void init() {
        ctrlPageToggle.setEnabled(false);
        ctrlPageToggle.setChecked(false);
        keyBoardView.setKeyBoardReturn(this);
    }

    @Override
    public void onKeyBoardEnter(String result) {
        switch (reSetTG) {
            case RE_SET_LEVEL:
                levelValue.setText(result);
                break;
            case RE_SET_PWM:
                pwmValue.setText(result);
                break;
            default:
                break;
        }

    }

    @Override
    public void onKeyBoardClose() {
        switch (reSetTG) {
            default:
                break;
            case RE_SET_LEVEL:
                TextUtils.changeTextColor(levelValue, ContextCompat.getColor(getContext(),R.color.factory_white));
                TextUtils.changeTextBackground(levelValue, R.mipmap.img_number_frame_1);
                break;
            case RE_SET_PWM:
                TextUtils.changeTextColor(pwmValue, ContextCompat.getColor(getContext(),R.color.factory_white));
                TextUtils.changeTextBackground(pwmValue, R.mipmap.img_number_frame_1);
                break;
        }
        reSetTG = reSetTG;
        isShowingKeyBoard = false;
        keyBoardView.setVisibility(View.GONE);
        leftLayout.setVisibility(View.VISIBLE);
    }


    @OnClick({R.id.factory2_card1_1_display_mode_toggle, R.id.factory2_card1_1_pause_mode_toggle, R.id.factory2_card1_1_key_tone_toggle,
            R.id.factory2_card1_1_buzzer_toggle, R.id.factory2_card1_1_child_lock_toggle, R.id.factory2_card1_2_ctrl_page_toggle
    })
    public void toggleButtonClick(View view) {
        ToggleButton tb = (ToggleButton) view;
        boolean isCheck = tb.isChecked();
        switch (view.getId()) {
            default:
                break;
        }
    }

    private boolean isShowingKeyBoard = false;
    private static int reSetTG = -1;
    private static final int RE_SET_LEVEL = 1001;
    private static final int RE_SET_PWM = 1002;

    @OnClick({R.id.factory2_card1_2_pwm_value, R.id.factory2_card1_2_level_value})
    public void changeValue(View view) {
        if (isShowingKeyBoard) {
            return;
        }
        isShowingKeyBoard = true;
        keyBoardView.setVisibility(View.VISIBLE);
        leftLayout.setVisibility(View.GONE);
        switch (view.getId()) {
            default:
                break;
            case R.id.factory2_card1_2_level_value:
                reSetTG = RE_SET_LEVEL;
                keyBoardView.setTitleImage(R.mipmap.tv_keybord_level);
                TextUtils.changeTextColor(levelValue, ContextCompat.getColor(getContext(),R.color.factory_tabs_on));
                TextUtils.changeTextBackground(levelValue, R.mipmap.img_number_frame_2);
                break;
            case R.id.factory2_card1_2_pwm_value:
                reSetTG = RE_SET_PWM;
                keyBoardView.setTitleImage(R.mipmap.tv_keybord_pwm);
                TextUtils.changeTextColor(pwmValue, ContextCompat.getColor(getContext(),R.color.factory_tabs_on));
                TextUtils.changeTextBackground(pwmValue, R.mipmap.img_number_frame_2);
                break;
        }
    }


    @OnClick(R.id.factory2_card1_2_key_test_start)
    public void keyTestStart() {

    }

}
