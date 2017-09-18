package com.sunrise.treadmill.activity.settings;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragmentActivity;
import com.sunrise.treadmill.fragments.SettingsFragmentCard1;
import com.sunrise.treadmill.fragments.SettingsFragmentCard2;
import com.sunrise.treadmill.fragments.SettingsFragmentCard3;
import com.sunrise.treadmill.fragments.SettingsFragmentCard4;
import com.sunrise.treadmill.utils.ImageUtils;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/13.
 */

public class SettingsActivity extends BaseFragmentActivity {

    @BindView(R.id.settings_view_bg)
    LinearLayout bgView;

    @BindView(R.id.settings_views)
    FrameLayout showView;

    @BindView(R.id.bottom_logo_tab_home)
    ImageButton backHome;

    @BindViews({R.id.settings_card_system, R.id.settings_card_bluetooth, R.id.settings_card_wifi, R.id.settings_card_lock, R.id.settings_title})
    List<TextView> txtList;

    private FragmentManager fragmentManager;
    private Fragment nowFragment;
    private SettingsFragmentCard1 card1;
    private SettingsFragmentCard2 card2;
    private SettingsFragmentCard3 card3;
    private SettingsFragmentCard4 card4;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_settings;
    }

    @Override
    protected void setTextStyle() {
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList, TextUtils.MicrosoftBold(this));
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.ArialBold(this));
        }
    }

    @OnClick({R.id.settings_card_system, R.id.settings_card_bluetooth, R.id.settings_card_wifi, R.id.settings_card_lock})
    public void onSelectCardClick(View view) {
        int bgResource = -1;
        int tgCard = -1;
        Fragment tgFragment = null;
        switch (view.getId()) {
            case R.id.settings_card_system:
                tgCard = 0;
                bgResource = R.mipmap.img_factory_3_1;
                tgFragment = card1;
                break;
            case R.id.settings_card_bluetooth:
                tgCard = 1;
                bgResource = R.mipmap.img_factory_3_2;
                if(card2==null){
                    card2 = new SettingsFragmentCard2();
                }
                tgFragment = card2;
                break;
            case R.id.settings_card_wifi:
                tgCard = 2;
                bgResource = R.mipmap.img_factory_3_3;
                if(card3==null){
                    card3 = new SettingsFragmentCard3();
                }
                tgFragment = card3;
                break;
            case R.id.settings_card_lock:
                tgCard = 3;
                bgResource = R.mipmap.img_factory_3_4;
                if(card4==null){
                    card4 = new SettingsFragmentCard4();
                }
                tgFragment = card4;
                break;
            default:
                bgResource = -1;
                tgCard = -1;
                tgFragment = null;
                break;
        }
        bgView.setBackgroundResource(bgResource);
        if (bgResource != -1 && tgFragment != null) {
            for (int i = 0; i < txtList.size() - 1; i++) {
                if (i == tgCard) {
                    TextUtils.changeTextColor(txtList.get(i), getColor(R.color.settings_tabs_on));
                    TextUtils.changeTextSize(txtList.get(i), 35f);
                } else {
                    TextUtils.changeTextColor(txtList.get(i), getColor(R.color.settings_tabs_off));
                    TextUtils.changeTextSize(txtList.get(i), 30f);
                }
            }
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (!tgFragment.isAdded()) {
                ft.hide(nowFragment).add(R.id.settings_views, tgFragment).commit();
            } else {
                ft.hide(nowFragment).show(tgFragment).commit();
            }
            nowFragment = tgFragment;
        }
        if (tgCard == 3) {
            Intent intent = new Intent(SettingsActivity.this, SettingsLockActivity.class);
            startActivity(intent);
        }
    }

    @OnClick(R.id.bottom_logo_tab_home)
    public void onBackHome() {
        finishActivity();
    }

    private void init() {
        fragmentManager = getSupportFragmentManager();
        card1 = new SettingsFragmentCard1();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.settings_views, card1).commit();
        nowFragment = card1;
    }
}
