package com.sunrise.treadmill.fragments;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragment;
import com.sunrise.treadmill.utils.ImageUtils;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/14.
 */

public class SettingsLockFragmentCard1 extends BaseFragment {
    @BindView(R.id.settings_fragment_card_4_1_1)
    RelativeLayout leftLayout;

    @BindView(R.id.settings_card4_1_1_reset)
    ImageView leftReset;

    @BindViews({R.id.settings_card4_1_1_time, R.id.settings_card4_1_1_time_unit, R.id.settings_card4_1_1_remaining_time, R.id.settings_card4_1_1_remaining_time_unit})
    List<TextView> leftTxt;

    @BindView(R.id.settings_card4_1_1_time_value)
    TextView leftTimeValue;

    @BindView(R.id.settings_card4_1_1_remaining_time_value)
    TextView leftRemainingTimeValue;


    @BindView(R.id.settings_fragment_card_4_1_2)
    RelativeLayout rightLayout;

    @BindView(R.id.settings_card4_1_2_reset)
    ImageView rightReset;

    @BindViews({R.id.settings_card4_1_2_distance, R.id.settings_card4_1_2_distance_unit, R.id.settings_card4_1_2_remaining_distance, R.id.settings_card4_1_2_remaining_distance_unit})
    List<TextView> rightTxt;

    @BindView(R.id.settings_card4_1_2_distance_value)
    TextView rightDistanceValue;

    @BindView(R.id.settings_card4_1_2_remaining_distance_value)
    TextView rightDistanceTimeValue;


    @BindView(R.id.settings_fragment_card_4_1_2_editView)
    LinearLayout rightKeyBoard;

    @BindView(R.id.settings_fragment_card_4_1_1_editView)
    LinearLayout leftKeyBoard;


    @Override
    public int getLayoutId() {
        return R.layout.settings_fragment_card_4_1;
    }

    @Override
    protected void setTextStyle() {
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(leftTxt, TextUtils.MicrosoftBold(getContext()));
            TextUtils.setTextTypeFace(leftTimeValue, TextUtils.MicrosoftBold(getContext()));
            TextUtils.setTextTypeFace(leftRemainingTimeValue, TextUtils.MicrosoftBold(getContext()));

            TextUtils.setTextTypeFace(rightTxt, TextUtils.MicrosoftBold(getContext()));
            TextUtils.setTextTypeFace(rightDistanceValue, TextUtils.MicrosoftBold(getContext()));
            TextUtils.setTextTypeFace(rightDistanceTimeValue, TextUtils.MicrosoftBold(getContext()));
        } else {
            TextUtils.setTextTypeFace(leftTxt, TextUtils.ArialBold(getContext()));
            TextUtils.setTextTypeFace(leftTimeValue, TextUtils.ArialBold(getContext()));
            TextUtils.setTextTypeFace(leftRemainingTimeValue, TextUtils.ArialBold(getContext()));

            TextUtils.setTextTypeFace(rightTxt, TextUtils.ArialBold(getContext()));
            TextUtils.setTextTypeFace(rightDistanceValue, TextUtils.ArialBold(getContext()));
            TextUtils.setTextTypeFace(rightDistanceTimeValue, TextUtils.ArialBold(getContext()));
        }
    }

    @Override
    protected void init() {
        ImageView leftKeyBoardClose = (ImageView) leftKeyBoard.findViewById(R.id.key_board_close);
        ImageView rightKeyBoardClose = (ImageView) rightKeyBoard.findViewById(R.id.key_board_close);

        TextView leftTitle = leftKeyBoard.findViewById(R.id.key_board_title);
        TextView rightTitle = rightKeyBoard.findViewById(R.id.key_board_title);

        leftKeyBoardClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leftKeyBoard.setVisibility(View.GONE);
                rightLayout.setVisibility(View.VISIBLE);

                ImageUtils.changeImageView(leftReset, R.mipmap.btn_factory_reset_1);
                TextUtils.changeTextColor(leftTimeValue, R.color.settings_tabs_off);
            }
        });

        rightKeyBoardClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rightKeyBoard.setVisibility(View.GONE);
                leftLayout.setVisibility(View.VISIBLE);

                ImageUtils.changeImageView(rightReset, R.mipmap.btn_factory_reset_1);
                TextUtils.changeTextColor(rightDistanceTimeValue, R.color.settings_tabs_off);
            }
        });

        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(leftTitle, TextUtils.MicrosoftBold(getContext()));
            TextUtils.setTextTypeFace(rightTitle, TextUtils.MicrosoftBold(getContext()));
        } else {
            TextUtils.setTextTypeFace(leftTitle, TextUtils.ArialBold(getContext()));
            TextUtils.setTextTypeFace(rightTitle, TextUtils.ArialBold(getContext()));
        }
        leftTitle.setText(R.string.time);
        rightTitle.setText(R.string.distance);
    }

    @OnClick({R.id.settings_card4_1_1_reset, R.id.settings_card4_1_2_reset})
    public void reSet(View view) {
        switch (view.getId()) {
            case R.id.settings_card4_1_1_reset:
                rightLayout.setVisibility(View.GONE);
                leftKeyBoard.setVisibility(View.VISIBLE);
                ImageUtils.changeImageView(leftReset, R.mipmap.btn_factory_reset_2);
                TextUtils.changeTextColor(leftTimeValue, R.color.settings_tabs_on);
                break;
            case R.id.settings_card4_1_2_reset:
                leftLayout.setVisibility(View.GONE);
                rightKeyBoard.setVisibility(View.VISIBLE);
                ImageUtils.changeImageView(rightReset, R.mipmap.btn_factory_reset_2);
                TextUtils.changeTextColor(rightDistanceTimeValue, R.color.settings_tabs_on);
                break;
            default:

                break;
        }

    }


}
