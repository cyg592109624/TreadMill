package com.sunrise.treadmill.fragments.settings;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

public class SettingsLockFragmentCard2 extends BaseFragment implements OnKeyBoardReturn {
    @BindView(R.id.settings_card4_2_customer_psw_value)
    TextView psw;

    @BindView(R.id.settings_card4_2_right)
    MyKeyBoardView keyBoard;

    @BindView(R.id.settings_card4_2_reset)
    ImageView reSet;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_settings_lock_card_2;
    }

    @Override
    public void clearObj() {
        psw = null;
        keyBoard = null;
        reSet = null;
        parentView = null;
    }

    @Override
    protected void setTextStyle() {
        List<TextView> txtList = new ArrayList<TextView>();
        txtList.add((TextView) getParentView().findViewById(R.id.settings_card4_2_customer_psw));
        txtList.add((TextView) getParentView().findViewById(R.id.settings_card4_2_srs_psw));
        txtList.add((TextView) getParentView().findViewById(R.id.settings_card4_2_srs_psw_value));
        txtList.add(psw);
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList, TextUtils.MicrosoftBold(getContext()));
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.ArialBold(getContext()));
        }
    }

    @Override
    protected void init() {
        keyBoard.setKeyBoardReturn(this);
        keyBoard.setTitleImage(R.mipmap.tv_keybord_password);
    }

    @OnClick(R.id.settings_card4_2_reset)
    public void reSet(View view) {
        keyBoard.setVisibility(View.VISIBLE);
        TextUtils.changeTextColor(psw, ContextCompat.getColor(getContext(), R.color.settings_tabs_on));
    }

    @Override
    public void onKeyBoardEnter(String result) {
        psw.setText(result);
    }

    @Override
    public void onKeyBoardClose() {
        keyBoard.setVisibility(View.INVISIBLE);
        TextUtils.changeTextColor(psw, ContextCompat.getColor(getContext(), R.color.settings_white));
    }
}
