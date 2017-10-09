package com.sunrise.treadmill.activity.workoutsetting;

import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.adapter.WorkOutVRAdapter;
import com.sunrise.treadmill.base.BaseFragmentActivity;
import com.sunrise.treadmill.fragments.workoutsetting.VRFragmentPage1;
import com.sunrise.treadmill.fragments.workoutsetting.VRFragmentPage2;
import com.sunrise.treadmill.fragments.workoutsetting.VRFragmentPage3;
import com.sunrise.treadmill.interfaces.OnKeyBoardReturn;
import com.sunrise.treadmill.interfaces.OnVRSelectReturn;
import com.sunrise.treadmill.utils.ImageUtils;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;
import com.sunrise.treadmill.views.MyKeyBoardView;
import com.sunrise.treadmill.views.MyWorkOutHead;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/26.
 */

public class VirtualRealityActivity extends BaseFragmentActivity implements ViewPager.OnPageChangeListener, OnVRSelectReturn, OnKeyBoardReturn {
    public static final String SelectVRNum = "SelectVRNum";

    public static final int selectNothing = -1;
    public static final int tgValue_1 = 10001;
    public static final int tgValue_2 = 10002;
    public static final int tgValue_3 = 10003;
    public static final int tgValue_4 = 10004;
    public static final int tgValue_5 = 10005;
    public static final int tgValue_6 = 10006;
    public static final int tgValue_7 = 10007;
    public static final int tgValue_8 = 10008;
    public static final int tgValue_9 = 10009;
    public static final int tgValue_10 = 10010;
    public static final int tgValue_11 = 10011;

    @BindView(R.id.workout_mode_head)
    MyWorkOutHead headView;

    @BindView(R.id.workout_option_body)
    LinearLayout optionBody;

    @BindView(R.id.workout_option_body2)
    ConstraintLayout optionBody2;

    @BindView(R.id.workout_vr_view_page)
    ViewPager viewPage;

    @BindView(R.id.workout_vr_img)
    ImageView viewPageTg;


    @BindView(R.id.dialog_workout_vr_img)
    ImageView selectVRImage;

    @BindView(R.id.dialog_workout_vr_edit_time)
    TextView editValue;

    @BindView(R.id.dialog_workout_vr_keyboard)
    MyKeyBoardView keyBoardView;

    @BindView(R.id.workout_mode_start_4)
    ImageView startBtn;

    @BindView(R.id.workout_mode_back_4)
    ImageView backBtn;


    private WorkOutVRAdapter fragmentAdapter;

    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    public int getLayoutId() {
        return R.layout.activity_workout_setting_virtual_reality;
    }

    @Override
    protected void init() {
        headView.setHeadMsg(getResources().getString(R.string.workout_mode_virtual), getResources().getString(R.string.workout_mode_hint_h), R.mipmap.img_program_virtual_icon);
        optionBody2.setVisibility(View.GONE);

        keyBoardView.setKeyBoardReturn(this);
        keyBoardView.setTitleImage(R.mipmap.tv_keybord_time);

        List<Fragment> list = new ArrayList<Fragment>();
        list.add(new VRFragmentPage1());
        list.add(new VRFragmentPage2());
        list.add(new VRFragmentPage3());
        fragmentAdapter = new WorkOutVRAdapter(fragmentManager, list);
        viewPage.setAdapter(fragmentAdapter);
        viewPage.setCurrentItem(0);
        viewPage.setOnPageChangeListener(this);
    }

    @Override
    protected void setTextStyle() {
        List<TextView> txtList = new ArrayList<TextView>();
        txtList.add((TextView) headView.findViewById(R.id.workout_head_mode));
        txtList.add((TextView) headView.findViewById(R.id.workout_head_hint));
        txtList.add((TextView) findViewById(R.id.workout_edit_start_hint_4));
        txtList.add(editValue);
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList, TextUtils.Microsoft(this));
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.Arial(this));
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                ImageUtils.changeImageView(viewPageTg, R.mipmap.img_virtual_reality_page_1);
                break;
            case 1:
                ImageUtils.changeImageView(viewPageTg, R.mipmap.img_virtual_reality_page_2);
                break;
            case 2:
                ImageUtils.changeImageView(viewPageTg, R.mipmap.img_virtual_reality_page_3);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onVRSelect(int vrNum) {
        if (vrNum != selectNothing) {
            showOptionBody(false);
            changeSelectVRImage(vrNum);
        }
    }
    @Override
    public void onKeyBoardEnter(String result) {
        editValue.setText(result);
    }

    @Override
    public void onKeyBoardClose() {
        startBtn.setEnabled(true);
        backBtn.setEnabled(true);
        selectVRImage.setVisibility(View.VISIBLE);
        keyBoardView.setVisibility(View.GONE);
        TextUtils.changeTextColor(editValue, getResources().getColor(R.color.workout_mode_white));
        TextUtils.changeTextBackground(editValue, R.mipmap.btn_virtual_time_1);
    }

    @OnClick(R.id.bottom_logo_tab_home)
    public void onBackHome() {
        finishActivity();
    }

    @OnClick(R.id.dialog_workout_vr_edit_time)
    public void changeEditValue() {
        startBtn.setEnabled(false);
        backBtn.setEnabled(false);
        selectVRImage.setVisibility(View.GONE);
        keyBoardView.setVisibility(View.VISIBLE);

        TextUtils.changeTextColor(editValue, getResources().getColor(R.color.workout_mode_select));
        TextUtils.changeTextBackground(editValue, R.mipmap.btn_virtual_time_2);
    }

    @OnClick(R.id.workout_mode_back_4)
    public void back() {
        showOptionBody(true);
    }

    private void showOptionBody(boolean isShowOptionBody1){
        if(isShowOptionBody1){
            optionBody.setVisibility(View.VISIBLE);
            optionBody2.setVisibility(View.GONE);
        }else {
            optionBody.setVisibility(View.GONE);
            optionBody2.setVisibility(View.VISIBLE);
        }
    }

    private void changeSelectVRImage(int vrNum) {
        switch (vrNum) {
            default:
                break;
            case VirtualRealityActivity.tgValue_1:
                ImageUtils.changeImageView(selectVRImage, R.mipmap.img_program_virtual_01_4);
                break;
            case VirtualRealityActivity.tgValue_2:
                ImageUtils.changeImageView(selectVRImage, R.mipmap.img_program_virtual_02_4);
                break;
            case VirtualRealityActivity.tgValue_3:
                ImageUtils.changeImageView(selectVRImage, R.mipmap.img_program_virtual_03_4);
                break;
            case VirtualRealityActivity.tgValue_4:
                ImageUtils.changeImageView(selectVRImage, R.mipmap.img_program_virtual_04_4);
                break;
            case VirtualRealityActivity.tgValue_5:
                ImageUtils.changeImageView(selectVRImage, R.mipmap.img_program_virtual_05_4);
                break;
            case VirtualRealityActivity.tgValue_6:
                ImageUtils.changeImageView(selectVRImage, R.mipmap.img_program_virtual_06_4);
                break;
            case VirtualRealityActivity.tgValue_7:
                ImageUtils.changeImageView(selectVRImage, R.mipmap.img_program_virtual_07_4);
                break;
            case VirtualRealityActivity.tgValue_8:
                ImageUtils.changeImageView(selectVRImage, R.mipmap.img_program_virtual_08_4);
                break;
            case VirtualRealityActivity.tgValue_9:
                ImageUtils.changeImageView(selectVRImage, R.mipmap.img_program_virtual_09_4);
                break;
            case VirtualRealityActivity.tgValue_10:
                ImageUtils.changeImageView(selectVRImage, R.mipmap.img_program_virtual_10_4);
                break;
        }
    }

}
