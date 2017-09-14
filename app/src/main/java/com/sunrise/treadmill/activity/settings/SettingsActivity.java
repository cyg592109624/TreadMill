package com.sunrise.treadmill.activity.settings;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.adapter.SettingsFragmentAdapter;
import com.sunrise.treadmill.base.BaseFragmentActivity;
import com.sunrise.treadmill.fragments.SettingsFragmentCard1;
import com.sunrise.treadmill.fragments.SettingsFragmentCard2;
import com.sunrise.treadmill.fragments.SettingsFragmentCard3;
import com.sunrise.treadmill.fragments.SettingsFragmentCard4;
import com.sunrise.treadmill.utils.ImageUtils;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;

import java.util.ArrayList;
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
    ImageView backHome;

    @BindViews({R.id.settings_card_system, R.id.settings_card_bluetooth, R.id.settings_card_wifi, R.id.settings_card_lock, R.id.settings_title})
    List<TextView> txtList;


    private  SettingsFragmentAdapter tabsAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_settings;
    }

    @OnClick({R.id.settings_card_system, R.id.settings_card_bluetooth, R.id.settings_card_wifi, R.id.settings_card_lock})
    public void onSelectCardClick(View view) {
        int bgResource = -1;
        int tgCard = -1;
        switch (view.getId()) {
            default:
                bgResource = -1;
                tgCard = -1;
                break;
            case R.id.settings_card_system:
                bgResource = R.mipmap.img_factory_3_1;
                tgCard = 0;
                break;
            case R.id.settings_card_bluetooth:
                bgResource = R.mipmap.img_factory_3_2;
                tgCard = 1;
                break;
            case R.id.settings_card_wifi:
                bgResource = R.mipmap.img_factory_3_3;
                tgCard = 2;
                break;
            case R.id.settings_card_lock:
                bgResource = R.mipmap.img_factory_3_4;
                tgCard = 3;
                break;
        }
        if (bgResource != -1 && tgCard != -1) {
            bgView.setBackgroundResource(bgResource);
            for (int i = 0; i < txtList.size() - 1; i++) {
                if (i == tgCard) {
                    TextUtils.changeTextColor(txtList.get(i), getColor(R.color.settings_tabs_on));
                    TextUtils.changeTextSize(txtList.get(i), 35f);
                } else {
                    TextUtils.changeTextColor(txtList.get(i), getColor(R.color.settings_tabs_off));
                    TextUtils.changeTextSize(txtList.get(i), 30f);
                }
            }
            Fragment fragment = (Fragment) tabsAdapter.instantiateItem(showView, tgCard);
            tabsAdapter.setPrimaryItem(showView, 0, fragment);
            tabsAdapter.finishUpdate(showView);
        }
    }

    @OnClick(R.id.bottom_logo_tab_home)
    public void onBackHome() {
        ImageUtils.changeImageView(backHome, R.mipmap.btn_home_2);
        finishActivity();
    }

    @Override
    protected void setTextStyle() {
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList, TextUtils.MicrosoftBold(this));

        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.ArialBold(this));
        }
    }

    private void init() {
        tabsAdapter=new SettingsFragmentAdapter(getSupportFragmentManager());
        SettingsFragmentCard1 card1=new SettingsFragmentCard1();
        SettingsFragmentCard2 card2=new SettingsFragmentCard2();
        SettingsFragmentCard3 card3=new SettingsFragmentCard3();
        SettingsFragmentCard4 card4=new SettingsFragmentCard4();
        tabsAdapter.addTabs(card1);
        tabsAdapter.addTabs(card2);
        tabsAdapter.addTabs(card3);
        tabsAdapter.addTabs(card4);
        Fragment fragment = (Fragment) tabsAdapter.instantiateItem(showView, 0);
        tabsAdapter.setPrimaryItem(showView,0,fragment);
        tabsAdapter.finishUpdate(showView);
    }
}
