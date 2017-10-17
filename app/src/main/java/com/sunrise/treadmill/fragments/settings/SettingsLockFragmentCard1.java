package com.sunrise.treadmill.fragments.settings;

import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragment;
import com.sunrise.treadmill.interfaces.OnKeyBoardReturn;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;
import com.sunrise.treadmill.views.MyKeyBoardView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/14.
 */

public class SettingsLockFragmentCard1 extends BaseFragment implements OnKeyBoardReturn {
    @BindView(R.id.settings_lock_fragment_card_1_1)
    ConstraintLayout leftLayout;

    @BindView(R.id.settings_card4_1_1_reset)
    ImageView leftReset;

    @BindView(R.id.settings_card4_1_1_time_value)
    TextView timeValue;

    @BindView(R.id.settings_card4_1_1_remaining_time_value)
    TextView remainingTimeValue;


    @BindView(R.id.settings_lock_fragment_card_1_2)
    ConstraintLayout rightLayout;

    @BindView(R.id.settings_card4_1_2_reset)
    ImageView rightReset;


    @BindView(R.id.settings_card4_1_2_distance_value)
    TextView distanceValue;

    @BindView(R.id.settings_card4_1_2_remaining_distance_value)
    TextView remainingDistanceValue;


    @BindView(R.id.settings_lock_fragment_card_1_2_keyboard)
    MyKeyBoardView rightKeyBoard;

    @BindView(R.id.settings_lock_fragment_card_1_1_keyboard)
    MyKeyBoardView leftKeyBoard;

    private static int reSetTG = -1;
    private static final int RE_SET_TIME = 1001;
    private static final int RE_SET_DISTANCE = 1002;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_settings_lock_card_1;
    }

    @Override
    protected void setTextStyle() {
        List<TextView> txtList = new ArrayList<>();

        txtList.add((TextView) getParentView().findViewById(R.id.settings_card4_1_1_time));
        txtList.add((TextView) getParentView().findViewById(R.id.settings_card4_1_1_time_unit));
        txtList.add((TextView) getParentView().findViewById(R.id.settings_card4_1_1_remaining_time));
        txtList.add((TextView) getParentView().findViewById(R.id.settings_card4_1_1_remaining_time_unit));

        txtList.add((TextView) getParentView().findViewById(R.id.settings_card4_1_2_distance));
        txtList.add((TextView) getParentView().findViewById(R.id.settings_card4_1_2_distance_unit));
        txtList.add((TextView) getParentView().findViewById(R.id.settings_card4_1_2_remaining_distance));
        txtList.add((TextView) getParentView().findViewById(R.id.settings_card4_1_2_remaining_distance_unit));

        txtList.add(timeValue);
        txtList.add(remainingTimeValue);
        txtList.add(distanceValue);
        txtList.add(remainingDistanceValue);

        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList, TextUtils.MicrosoftBold(getContext()));
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.ArialBold(getContext()));
        }
    }

    @Override
    protected void init() {
        leftKeyBoard.setTitleImage(R.mipmap.tv_keybord_time);
        rightKeyBoard.setTitleImage(R.mipmap.tv_keybord_distance);

        leftKeyBoard.setKeyBoardReturn(this);
        rightKeyBoard.setKeyBoardReturn(this);
    }

    @OnClick({R.id.settings_card4_1_1_reset, R.id.settings_card4_1_2_reset})
    public void reSet(View view) {
        switch (view.getId()) {
            case R.id.settings_card4_1_1_reset:
                reSetTG = RE_SET_TIME;
                rightLayout.setVisibility(View.GONE);
                leftKeyBoard.setVisibility(View.VISIBLE);
                TextUtils.changeTextColor(timeValue,  ContextCompat.getColor(getContext(),R.color.settings_tabs_on));
                break;
            case R.id.settings_card4_1_2_reset:
                reSetTG = RE_SET_DISTANCE;
                leftLayout.setVisibility(View.GONE);
                rightKeyBoard.setVisibility(View.VISIBLE);
                TextUtils.changeTextColor(distanceValue,  ContextCompat.getColor(getContext(),R.color.settings_tabs_on));
                break;
            default:
                break;
        }
    }

    @Override
    public void onKeyBoardEnter(String result) {
        switch (reSetTG) {
            case RE_SET_TIME:
                timeValue.setText(result);
                break;
            case RE_SET_DISTANCE:
                distanceValue.setText(result);
                break;
            default:
                break;
        }
    }

    @Override
    public void onKeyBoardClose() {
        switch (reSetTG) {
            case RE_SET_TIME:
                TextUtils.changeTextColor(timeValue, ContextCompat.getColor(getContext(),R.color.settings_white));
                rightLayout.setVisibility(View.VISIBLE);
                leftKeyBoard.setVisibility(View.GONE);
                break;
            case RE_SET_DISTANCE:
                TextUtils.changeTextColor(distanceValue, ContextCompat.getColor(getContext(),R.color.settings_white));
                leftLayout.setVisibility(View.VISIBLE);
                rightKeyBoard.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }
}
