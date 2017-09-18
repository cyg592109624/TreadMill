package com.sunrise.treadmill.fragments;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragment;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/14.
 */

public class SettingsLockFragmentCard1 extends BaseFragment {
    @BindView(R.id.settings_lock_fragment_card_1_1)
    LinearLayout leftLayout;

    @BindView(R.id.settings_card4_1_1_reset)
    ImageButton leftReset;

    @BindView(R.id.settings_card4_1_1_time_value)
    TextView timeValue;

    @BindView(R.id.settings_card4_1_1_remaining_time_value)
    TextView remainingTimeValue;


    @BindView(R.id.settings_lock_fragment_card_1_2)
    LinearLayout rightLayout;

    @BindView(R.id.settings_card4_1_2_reset)
    ImageButton rightReset;


    @BindView(R.id.settings_card4_1_2_distance_value)
    TextView distanceValue;

    @BindView(R.id.settings_card4_1_2_remaining_distance_value)
    TextView remainingDistanceValue;


    @BindView(R.id.settings_fragment_card_4_1_2_editView)
    RelativeLayout rightKeyBoard;

    @BindView(R.id.settings_fragment_card_4_1_1_editView)
    RelativeLayout leftKeyBoard;

    private boolean isShowKeyBoard = false;

    @Override
    public int getLayoutId() {
        return R.layout.settings_lock_fragment_card_1;
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


        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList, TextUtils.MicrosoftBold(getContext()));

            TextUtils.setTextTypeFace(timeValue, TextUtils.MicrosoftBold(getContext()));
            TextUtils.setTextTypeFace(remainingTimeValue, TextUtils.MicrosoftBold(getContext()));

            TextUtils.setTextTypeFace(distanceValue, TextUtils.MicrosoftBold(getContext()));
            TextUtils.setTextTypeFace(remainingDistanceValue, TextUtils.MicrosoftBold(getContext()));
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.ArialBold(getContext()));

            TextUtils.setTextTypeFace(timeValue, TextUtils.ArialBold(getContext()));
            TextUtils.setTextTypeFace(remainingTimeValue, TextUtils.ArialBold(getContext()));

            TextUtils.setTextTypeFace(distanceValue, TextUtils.ArialBold(getContext()));
            TextUtils.setTextTypeFace(remainingDistanceValue, TextUtils.ArialBold(getContext()));
        }
    }

    @Override
    protected void init() {
        ImageButton leftKeyBoardClose = (ImageButton) leftKeyBoard.findViewById(R.id.key_board_close);
        ImageButton rightKeyBoardClose = (ImageButton) rightKeyBoard.findViewById(R.id.key_board_close);

        ImageView leftTitle = (ImageView) leftKeyBoard.findViewById(R.id.key_board_title);
        ImageView rightTitle = (ImageView) rightKeyBoard.findViewById(R.id.key_board_title);

        leftTitle.setImageResource(R.mipmap.tv_keybord_time);
        rightTitle.setImageResource(R.mipmap.tv_keybord_distance);

        leftKeyBoardClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isShowKeyBoard = false;
                leftKeyBoard.setVisibility(View.GONE);
                rightLayout.setVisibility(View.VISIBLE);
                TextUtils.changeTextColor(timeValue, R.color.settings_white);
            }
        });

        rightKeyBoardClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rightKeyBoard.setVisibility(View.GONE);
                isShowKeyBoard = false;
                leftLayout.setVisibility(View.VISIBLE);
                TextUtils.changeTextColor(distanceValue, R.color.settings_white);
            }
        });
    }

    @OnClick({R.id.settings_card4_1_1_reset, R.id.settings_card4_1_2_reset})
    public void reSet(View view) {
        if (isShowKeyBoard) {
            return;
        }
        isShowKeyBoard = true;
        switch (view.getId()) {
            case R.id.settings_card4_1_1_reset:
                rightLayout.setVisibility(View.GONE);
                leftKeyBoard.setVisibility(View.VISIBLE);
                TextUtils.changeTextColor(timeValue, R.color.settings_tabs_on);
                break;
            case R.id.settings_card4_1_2_reset:
                leftLayout.setVisibility(View.GONE);
                rightKeyBoard.setVisibility(View.VISIBLE);
                TextUtils.changeTextColor(distanceValue, R.color.settings_tabs_on);
                break;
            default:
                isShowKeyBoard = false;
                break;
        }

    }


}
