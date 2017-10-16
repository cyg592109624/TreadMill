package com.sunrise.treadmill.activity.home;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.activity.factory.FactoriesActivity;
import com.sunrise.treadmill.activity.settings.SettingsActivity;
import com.sunrise.treadmill.adapter.HomeFragmentAdapter;
import com.sunrise.treadmill.base.BaseFragmentActivity;
import com.sunrise.treadmill.dialog.home.InitialiteDialog;
import com.sunrise.treadmill.dialog.home.LanguageDialog;
import com.sunrise.treadmill.fragments.home.HomeFragmentPage1;
import com.sunrise.treadmill.fragments.home.HomeFragmentPage2;
import com.sunrise.treadmill.fragments.home.HomeFragmentPage2_zh;
import com.sunrise.treadmill.fragments.home.HomeFragmentPage3;
import com.sunrise.treadmill.fragments.home.HomeFragmentPage3_zh;
import com.sunrise.treadmill.interfaces.HomeLanguageDialogReturn;
import com.sunrise.treadmill.interfaces.OnInitialReturn;
import com.sunrise.treadmill.utils.ImageUtils;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.ThreadPoolUtils;
import com.sunrise.treadmill.views.LogoImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/6.
 */

public class HomeActivity extends BaseFragmentActivity implements HomeLanguageDialogReturn, OnInitialReturn, ViewPager.OnPageChangeListener {
    @BindView(R.id.home_img_vp)
    ImageView selectTg;

    @BindView(R.id.bottom_logo)
    LogoImageView logo;

    @BindView(R.id.home_viewPage)
    ViewPager viewPager;

    private HomeFragmentAdapter fragmentAdapter;

    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void init() {
        ThreadPoolUtils.runTaskOnThread(new Runnable() {
            @Override
            public void run() {
                List<Fragment> list = new ArrayList<Fragment>();
                list.add(new HomeFragmentPage1());
                if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
                    list.add(new HomeFragmentPage2_zh());
                    list.add(new HomeFragmentPage3_zh());
                } else {
                    list.add(new HomeFragmentPage2());
                    list.add(new HomeFragmentPage3());
                }
                fragmentAdapter = new HomeFragmentAdapter(fragmentManager, list);
                viewPager.setAdapter(fragmentAdapter);
                viewPager.setCurrentItem(0);
                viewPager.setOnPageChangeListener(HomeActivity.this);
            }
        });

        logo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(HomeActivity.this, FactoriesActivity.class);
                startActivity(intent);
                return true;
            }
        });
    }

    private int loadAtOnce = 1;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (loadAtOnce == 1) {
            showInitialDialog();
            loadAtOnce += 1;
        }
    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                ImageUtils.changeImageView(selectTg, R.mipmap.img_virtual_reality_page_1);
                break;
            case 1:
                ImageUtils.changeImageView(selectTg, R.mipmap.img_virtual_reality_page_2);
                break;
            case 2:
                ImageUtils.changeImageView(selectTg, R.mipmap.img_virtual_reality_page_3);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onInitialResult(String result) {
//        Intent intent = new Intent(HomeActivity_zh.this, NfcActivity.class);
//        startActivity(intent);
        System.gc();
    }

    @Override
    public void onLanguageReturn(boolean isChange) {
        if (isChange) {
            Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
            finishActivity();
            startActivity(intent);
        }
    }

    @OnClick(R.id.home_btn_language)
    public void changeLanguage() {
        LanguageDialog languageDialog = new LanguageDialog();
        languageDialog.show(fragmentManager, LanguageDialog.TAG);
    }

    @OnClick(R.id.home_btn_setting)
    public void toSettings() {
        Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    private void showInitialDialog() {
        ThreadPoolUtils.runTaskOnThread(new Runnable() {
            @Override
            public void run() {
                InitialiteDialog initialiteDialog = new InitialiteDialog();
                initialiteDialog.show(fragmentManager, InitialiteDialog.TAG);
            }
        });
    }

}
