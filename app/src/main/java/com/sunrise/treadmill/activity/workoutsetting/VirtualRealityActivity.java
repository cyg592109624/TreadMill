package com.sunrise.treadmill.activity.workoutsetting;

import android.os.Bundle;
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
import com.sunrise.treadmill.dialog.workoutsetting.VirtualSetValueDialog;
import com.sunrise.treadmill.fragments.workout.VRFragmentPage1;
import com.sunrise.treadmill.fragments.workout.VRFragmentPage2;
import com.sunrise.treadmill.fragments.workout.VRFragmentPage3;
import com.sunrise.treadmill.interfaces.OnVRSelectReturn;
import com.sunrise.treadmill.utils.ImageUtils;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;
import com.sunrise.treadmill.views.MyWorkOutHead;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/26.
 */

public class VirtualRealityActivity extends BaseFragmentActivity implements ViewPager.OnPageChangeListener, OnVRSelectReturn {
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

    @BindView(R.id.workout_vr_view_page)
    ViewPager viewPage;
    @BindView(R.id.workout_vr_img)
    ImageView selectTg;

    private WorkOutVRAdapter fragmentAdapter;

    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    public int getLayoutId() {
        return R.layout.activity_workout_setting_virtual_reality;
    }

    @Override
    protected void init() {
        headView.setHeadMsg(getResources().getString(R.string.workout_mode_virtual), getResources().getString(R.string.workout_mode_hint_h), R.mipmap.img_program_virtual_icon);

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
    public void onPageScrollStateChanged(int state) {

    }

    private VirtualSetValueDialog dialog;

    @Override
    public void onVRSelect(int vrNum) {
        if (vrNum != selectNothing) {
            Bundle bundle = new Bundle();
            bundle.putInt(SelectVRNum, vrNum);
            if (dialog == null) {
                dialog = new VirtualSetValueDialog();
            }
            dialog.setArguments(bundle);
            dialog.show(fragmentManager, VirtualSetValueDialog.TAG);
            setOptionBodyVisibility(View.INVISIBLE);
        }
    }

    public void setOptionBodyVisibility(int visibility) {
        optionBody.setVisibility(visibility);
    }


    @OnClick(R.id.bottom_logo_tab_home)
    public void onBackHome() {
        finishActivity();
    }

}
