package com.sunrise.treadmill.activity.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.sunrise.treadmill.Constant;
import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.activity.factory.FactoriesActivity;
import com.sunrise.treadmill.activity.settings.SettingsActivity;
import com.sunrise.treadmill.activity.workoutrunning.QuickStartRunningActivity;
import com.sunrise.treadmill.activity.workoutrunning.QuickStartRunningActivityZh;
import com.sunrise.treadmill.activity.workoutsetting.FitnessTestActivity;
import com.sunrise.treadmill.activity.workoutsetting.GoalActivity;
import com.sunrise.treadmill.activity.workoutsetting.HRCActivity;
import com.sunrise.treadmill.activity.workoutsetting.HillActivity;
import com.sunrise.treadmill.activity.workoutsetting.IntervalActivity;
import com.sunrise.treadmill.activity.workoutsetting.UserProgramActivity;
import com.sunrise.treadmill.activity.workoutsetting.VirtualRealityActivity;
import com.sunrise.treadmill.adapter.home.HomeViewPageAdapter;
import com.sunrise.treadmill.base.BaseFragmentActivity;
import com.sunrise.treadmill.dialog.home.InitialiteDialog;
import com.sunrise.treadmill.dialog.home.LanguageDialog;
import com.sunrise.treadmill.fragments.home.HomeFragmentPage1;
import com.sunrise.treadmill.fragments.home.HomeFragmentPage2;
import com.sunrise.treadmill.fragments.home.HomeFragmentPage2Zh;
import com.sunrise.treadmill.fragments.home.HomeFragmentPage3;
import com.sunrise.treadmill.fragments.home.HomeFragmentPage3Zh;
import com.sunrise.treadmill.interfaces.home.HomeLanguageDialogReturn;
import com.sunrise.treadmill.interfaces.home.OnInitialReturn;
import com.sunrise.treadmill.interfaces.home.OnModeSelectReturn;
import com.sunrise.treadmill.utils.BitMapUtils;
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
public class HomeActivity extends BaseFragmentActivity implements HomeLanguageDialogReturn, OnInitialReturn, ViewPager.OnPageChangeListener, OnModeSelectReturn {
    @BindView(R.id.home_img_vp)
    ImageView selectTg;

    @BindView(R.id.home_viewPage)
    ViewPager viewPager;

    private HomeViewPageAdapter fragmentAdapter;

    private Bitmap selectTgBitmap;

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void recycleObject() {
        selectTg = null;
        if (selectTgBitmap != null) {
            selectTgBitmap.recycle();
            selectTgBitmap = null;
        }
        viewPager.removeAllViews();
        viewPager = null;
        fragmentAdapter.recycle();
        fragmentAdapter = null;
        fragmentManager = null;
    }

    @Override
    protected void init() {
        List<Fragment> list = new ArrayList<Fragment>();
        HomeFragmentPage1 page1 = new HomeFragmentPage1();
        page1.setSelectReturn(HomeActivity.this);
        list.add(page1);
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            HomeFragmentPage2Zh page2 = new HomeFragmentPage2Zh();
            HomeFragmentPage3Zh page3 = new HomeFragmentPage3Zh();
            page2.setSelectReturn(HomeActivity.this);
            page3.setSelectReturn(HomeActivity.this);
            list.add(page2);
            list.add(page3);
        } else {
            HomeFragmentPage2 page2 = new HomeFragmentPage2();
            HomeFragmentPage3 page3 = new HomeFragmentPage3();
            page2.setSelectReturn(HomeActivity.this);
            page3.setSelectReturn(HomeActivity.this);
            list.add(page2);
            list.add(page3);
        }
        fragmentAdapter = new HomeViewPageAdapter(fragmentManager, list);
        viewPager.setAdapter(fragmentAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(HomeActivity.this);
        viewPager.setOffscreenPageLimit(3);

        LogoImageView logo = findViewById(R.id.bottom_logo);
        logo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(activityContext, FactoriesActivity.class);
                startActivity(intent);
                return true;
            }
        });
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (Constant.initialFinish == -1) {
            showInitialDialog();
            Constant.initialFinish = 1;
        }
    }

    @Override
    public void onPageSelected(int position) {
        if (selectTgBitmap != null) {
            selectTgBitmap.recycle();
        }
        switch (position) {
            case 0:
                selectTgBitmap = BitMapUtils.loadMipMapResource(getResources(), R.mipmap.img_virtual_reality_page_1);
                ImageUtils.changeImageView(selectTg, selectTgBitmap);
                break;
            case 1:
                selectTgBitmap = BitMapUtils.loadMipMapResource(getResources(), R.mipmap.img_virtual_reality_page_2);
                ImageUtils.changeImageView(selectTg, selectTgBitmap);
                break;
            case 2:
                selectTgBitmap = BitMapUtils.loadMipMapResource(getResources(), R.mipmap.img_virtual_reality_page_3);
                ImageUtils.changeImageView(selectTg, selectTgBitmap);
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

    }

    @Override
    public void onLanguageReturn(boolean isChange) {
        if (isChange) {
            Intent intent = new Intent(activityContext, HomeActivity.class);
            finishActivity();
            startActivity(intent);
        }
    }

    @Override
    public void onSelectResult(int result) {
        Intent intent = null;
//        if (result == Constant.MODE_QUICK_START) {
//            if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
//                intent = new Intent(HomeActivity.this, QuickStartRunningActivityZh.class);
//            } else {
//                intent = new Intent(HomeActivity.this, QuickStartRunningActivity.class);
//            }
//            finishActivity();
//            startActivity(intent);
//        } else {
//
//        }
        switch (result) {
            default:
                break;
            case Constant.MODE_HILL:
                intent = new Intent(HomeActivity.this, HillActivity.class);
                break;
            case Constant.MODE_INTERVAL:
                intent = new Intent(HomeActivity.this, IntervalActivity.class);
                break;
            case Constant.MODE_GOAL:
                intent = new Intent(HomeActivity.this, GoalActivity.class);
                break;
            case Constant.MODE_FITNESS_TEST:
                intent = new Intent(HomeActivity.this, FitnessTestActivity.class);
                break;
            case Constant.MODE_HRC:
                intent = new Intent(HomeActivity.this, HRCActivity.class);
                break;
            case Constant.MODE_VR:
                intent = new Intent(HomeActivity.this, VirtualRealityActivity.class);
                break;
            case Constant.MODE_USER_PROGRAM:
                intent = new Intent(HomeActivity.this, UserProgramActivity.class);
                break;
            case Constant.MODE_QUICK_START:
                if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
                    intent = new Intent(HomeActivity.this, QuickStartRunningActivityZh.class);
                } else {
                    intent = new Intent(HomeActivity.this, QuickStartRunningActivity.class);
                }
                break;
        }
        if (intent != null) {
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
        Intent intent = new Intent(activityContext, SettingsActivity.class);
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
