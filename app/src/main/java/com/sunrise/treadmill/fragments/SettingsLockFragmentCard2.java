package com.sunrise.treadmill.fragments;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
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

public class SettingsLockFragmentCard2 extends BaseFragment {
    @BindView(R.id.settings_card4_2_customer_psw_value)
    TextView psw;

    @BindView(R.id.settings_card4_2_right)
    RelativeLayout keyBoard;

    @BindView(R.id.settings_card4_2_reset)
    ImageButton reSet;



    @Override
    public int getLayoutId() {
        return R.layout.settings_lock_fragment_card_2;
    }

    @Override
    protected void setTextStyle() {
        List<TextView> txtList=new ArrayList<TextView>();
        txtList.add((TextView) getParentView().findViewById(R.id.settings_card4_2_customer_psw));
        txtList.add((TextView) getParentView().findViewById(R.id.settings_card4_2_srs_psw));
        txtList.add((TextView) getParentView().findViewById(R.id.settings_card4_2_srs_psw_value));
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList, TextUtils.MicrosoftBold(getContext()));
            TextUtils.setTextTypeFace(psw, TextUtils.MicrosoftBold(getContext()));
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.ArialBold(getContext()));
            TextUtils.setTextTypeFace(psw, TextUtils.ArialBold(getContext()));
        }
    }

    @Override
    protected void init() {

        keyBoard.findViewById(R.id.key_board_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keyBoard.setVisibility(View.INVISIBLE);
                TextUtils.changeTextColor(psw, R.color.settings_white);
            }
        });
        ImageView keyBoardTitle = (ImageView)keyBoard.findViewById(R.id.key_board_title);
        keyBoardTitle.setImageResource(R.mipmap.tv_keybord_password);
    }

    @OnClick(R.id.settings_card4_2_reset)
    public void reSet(View view) {
        TextUtils.changeTextColor(psw, R.color.settings_tabs_on);
        keyBoard.setVisibility(View.VISIBLE);
    }

}
