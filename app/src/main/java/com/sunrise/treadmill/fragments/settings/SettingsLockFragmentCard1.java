package com.sunrise.treadmill.fragments.settings;

import android.view.View;
import android.widget.ImageButton;
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
    ImageView leftReset;

    @BindView(R.id.settings_card4_1_1_time_value)
    TextView timeValue;

    @BindView(R.id.settings_card4_1_1_remaining_time_value)
    TextView remainingTimeValue;


    @BindView(R.id.settings_lock_fragment_card_1_2)
    LinearLayout rightLayout;

    @BindView(R.id.settings_card4_1_2_reset)
    ImageView rightReset;


    @BindView(R.id.settings_card4_1_2_distance_value)
    TextView distanceValue;

    @BindView(R.id.settings_card4_1_2_remaining_distance_value)
    TextView remainingDistanceValue;


    @BindView(R.id.settings_fragment_card_4_1_2_editView)
    RelativeLayout rightKeyBoard;

    @BindView(R.id.settings_fragment_card_4_1_1_editView)
    RelativeLayout leftKeyBoard;

    private TextView leftKeyBoardTextView, rightKeyBoardTextView;


    private boolean isShowKeyBoard = false;
    private static int reSetTG = -1;
    private static int reSetNone = 0;
    private static int reSetTime = 1001;
    private static int reSetDistance = 1002;

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
        reSetTG = reSetNone;
        settingKeyBoard();
    }

    @OnClick({R.id.settings_card4_1_1_reset, R.id.settings_card4_1_2_reset})
    public void reSet(View view) {
        if (isShowKeyBoard) {
            return;
        }
        isShowKeyBoard = true;
        switch (view.getId()) {
            case R.id.settings_card4_1_1_reset:
                reSetTG = reSetTime;
                rightLayout.setVisibility(View.GONE);
                leftKeyBoard.setVisibility(View.VISIBLE);
                TextUtils.changeTextColor(timeValue, R.color.settings_tabs_on);
                break;
            case R.id.settings_card4_1_2_reset:
                reSetTG = reSetDistance;
                leftLayout.setVisibility(View.GONE);
                rightKeyBoard.setVisibility(View.VISIBLE);
                TextUtils.changeTextColor(distanceValue, R.color.settings_tabs_on);
                break;
            default:
                isShowKeyBoard = false;
                break;
        }

    }

    private ImageButton leftKeyBoardClose;

    private ImageButton rightKeyBoardClose;

    private void settingKeyBoard() {
        ImageView leftTitle = (ImageView) leftKeyBoard.findViewById(R.id.key_board_title);
        leftKeyBoardClose = (ImageButton) leftKeyBoard.findViewById(R.id.key_board_close);
        leftKeyBoardTextView = (TextView) leftKeyBoard.findViewById(R.id.key_board_edit_value);

        ImageView rightTitle = (ImageView) rightKeyBoard.findViewById(R.id.key_board_title);
        rightKeyBoardClose = (ImageButton) rightKeyBoard.findViewById(R.id.key_board_close);
        rightKeyBoardTextView = (TextView) rightKeyBoard.findViewById(R.id.key_board_edit_value);

        ImageUtils.changeImageView(leftTitle, R.mipmap.tv_keybord_time);
        ImageUtils.changeImageView(rightTitle, R.mipmap.tv_keybord_distance);

        leftKeyBoardClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reSetTG = reSetNone;
                isShowKeyBoard = false;
                leftKeyBoard.setVisibility(View.GONE);
                rightLayout.setVisibility(View.VISIBLE);
                TextUtils.changeTextColor(timeValue, R.color.settings_white);
            }
        });

        rightKeyBoardClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reSetTG = reSetNone;
                isShowKeyBoard = false;
                rightKeyBoard.setVisibility(View.GONE);
                leftLayout.setVisibility(View.VISIBLE);
                TextUtils.changeTextColor(distanceValue, R.color.settings_white);
            }
        });

        leftKeyBoard.findViewById(R.id.key_board_0).setOnClickListener(keyBoardClick);
        leftKeyBoard.findViewById(R.id.key_board_1).setOnClickListener(keyBoardClick);
        leftKeyBoard.findViewById(R.id.key_board_2).setOnClickListener(keyBoardClick);
        leftKeyBoard.findViewById(R.id.key_board_3).setOnClickListener(keyBoardClick);
        leftKeyBoard.findViewById(R.id.key_board_4).setOnClickListener(keyBoardClick);
        leftKeyBoard.findViewById(R.id.key_board_5).setOnClickListener(keyBoardClick);
        leftKeyBoard.findViewById(R.id.key_board_6).setOnClickListener(keyBoardClick);
        leftKeyBoard.findViewById(R.id.key_board_7).setOnClickListener(keyBoardClick);
        leftKeyBoard.findViewById(R.id.key_board_8).setOnClickListener(keyBoardClick);
        leftKeyBoard.findViewById(R.id.key_board_9).setOnClickListener(keyBoardClick);
        leftKeyBoard.findViewById(R.id.key_board_del).setOnClickListener(keyBoardClick);
        leftKeyBoard.findViewById(R.id.key_board_enter).setOnClickListener(keyBoardClick);


        rightKeyBoard.findViewById(R.id.key_board_0).setOnClickListener(keyBoardClick);
        rightKeyBoard.findViewById(R.id.key_board_1).setOnClickListener(keyBoardClick);
        rightKeyBoard.findViewById(R.id.key_board_2).setOnClickListener(keyBoardClick);
        rightKeyBoard.findViewById(R.id.key_board_3).setOnClickListener(keyBoardClick);
        rightKeyBoard.findViewById(R.id.key_board_4).setOnClickListener(keyBoardClick);
        rightKeyBoard.findViewById(R.id.key_board_5).setOnClickListener(keyBoardClick);
        rightKeyBoard.findViewById(R.id.key_board_6).setOnClickListener(keyBoardClick);
        rightKeyBoard.findViewById(R.id.key_board_7).setOnClickListener(keyBoardClick);
        rightKeyBoard.findViewById(R.id.key_board_8).setOnClickListener(keyBoardClick);
        rightKeyBoard.findViewById(R.id.key_board_9).setOnClickListener(keyBoardClick);
        rightKeyBoard.findViewById(R.id.key_board_del).setOnClickListener(keyBoardClick);
        rightKeyBoard.findViewById(R.id.key_board_enter).setOnClickListener(keyBoardClick);
    }

    private View.OnClickListener keyBoardClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (reSetTG == reSetNone) {
                return;
            }
            String val = "-1";
            TextView showText;
            if (reSetTG == reSetTime) {
                showText = leftKeyBoardTextView;
            } else {
                showText = rightKeyBoardTextView;
            }
            switch (view.getId()) {
                case R.id.key_board_0:
                    showText.setText(showText.getText() + "0");
                    break;
                case R.id.key_board_1:
                    showText.setText(showText.getText() + "1");
                    break;
                case R.id.key_board_2:
                    showText.setText(showText.getText() + "2");
                    break;
                case R.id.key_board_3:
                    showText.setText(showText.getText() + "3");
                    break;
                case R.id.key_board_4:
                    showText.setText(showText.getText() + "4");
                    break;
                case R.id.key_board_5:
                    showText.setText(showText.getText() + "5");
                    break;
                case R.id.key_board_6:
                    showText.setText(showText.getText() + "6");
                    break;
                case R.id.key_board_7:
                    showText.setText(showText.getText() + "7");
                    break;
                case R.id.key_board_8:
                    showText.setText(showText.getText() + "8");
                    break;
                case R.id.key_board_9:
                    showText.setText(showText.getText() + "9");
                    break;
                case R.id.key_board_del:
                    showText.setText(showText.getText().subSequence(0, showText.getText().length() - 1));
                    break;
                case R.id.key_board_enter:
                    if (reSetTG == reSetTime) {
                        timeValue.setText(showText.getText());
                        leftKeyBoardTextView.setText("");
                        leftKeyBoardClose.performClick();
                    } else {
                        distanceValue.setText(showText.getText());
                        rightKeyBoardTextView.setText("");
                        rightKeyBoardClose.performClick();
                    }
                    break;
            }
        }
    };

}
