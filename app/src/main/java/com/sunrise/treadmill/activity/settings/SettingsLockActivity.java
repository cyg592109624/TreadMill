package com.sunrise.treadmill.activity.settings;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragmentActivity;
import com.sunrise.treadmill.fragments.settings.SettingsLockFragmentCard1;
import com.sunrise.treadmill.fragments.settings.SettingsLockFragmentCard2;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/15.
 */

public class SettingsLockActivity extends BaseFragmentActivity {
    @BindView(R.id.settings_view_bg)
    LinearLayout bgView;

    @BindView(R.id.bottom_logo_tab_back)
    ImageButton back;


    @BindViews({R.id.settings_card_lock, R.id.settings_card_psw, R.id.settings_title})
    List<TextView> txtList;

    private FragmentManager fragmentManager;
    private Fragment nowFragment;
    private SettingsLockFragmentCard1 card1;
    private SettingsLockFragmentCard2 card2;

    @Override
    public int getLayoutId() {
        return R.layout.activity_settings_lock;
    }

    @Override
    public void clearObj() {
        bgView=null;
        txtList=null;
        fragmentManager=null;
        nowFragment=null;
        card1=null;
        card2=null;
        back=null;
        setContentView(R.layout.view_null);
    }

    @Override
    protected void setTextStyle() {
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList, TextUtils.MicrosoftBold(this));
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.ArialBold(this));
        }
    }

    @OnClick({R.id.settings_card_lock, R.id.settings_card_psw})
    public void onSelectCardClick(View view) {
        int bgResource = -1;
        int tgCard = -1;
        Fragment tgFragment = null;
        switch (view.getId()) {
            case R.id.settings_card_lock:
                bgResource = R.mipmap.img_factory_2_1;
                tgCard = 0;
                tgFragment = card1;
                break;
            case R.id.settings_card_psw:
                tgCard = 1;
                bgResource = R.mipmap.img_factory_2_2;
                if(card2==null){
                    card2 = new SettingsLockFragmentCard2();
                }
                tgFragment = card2;
                break;
            default:
                bgResource = -1;
                tgCard = -1;
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
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (!tgFragment.isAdded()) {
                ft.hide(nowFragment).add(R.id.settings_lock_views, tgFragment).commit();
            } else {
                ft.hide(nowFragment).show(tgFragment).commit();
            }
            nowFragment = tgFragment;
        }
    }

    @OnClick(R.id.bottom_logo_tab_back)
    public void onBack() {
        finishActivity();
    }

    @Override
    protected void init() {
        fragmentManager = getSupportFragmentManager();
        card1 = new SettingsLockFragmentCard1();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.settings_lock_views, card1).commit();
        nowFragment = card1;
    }
}
