package com.sunrise.treadmill.activity.workout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.adapter.HomeFragmentAdapter;
import com.sunrise.treadmill.adapter.WorkOutVRAdapter;
import com.sunrise.treadmill.base.BaseFragmentActivity;
import com.sunrise.treadmill.fragments.workout.VRFragmentPage1;
import com.sunrise.treadmill.fragments.workout.VRFragmentPage2;
import com.sunrise.treadmill.fragments.workout.VRFragmentPage3;
import com.sunrise.treadmill.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/26.
 */

public class VirtualRealityActivity extends BaseFragmentActivity implements ViewPager.OnPageChangeListener {
    @BindView(R.id.workout_option_body)
    LinearLayout optionBody;

    @BindView(R.id.workout_option_body2)
    LinearLayout optionBody2;

    @BindView(R.id.workout_vr_view_page)
    ViewPager viewPage;
    @BindView(R.id.workout_vr_img)
    ImageView selectTg;

    private WorkOutVRAdapter fragmentAdapter;

    private FragmentManager fragmentManager = getSupportFragmentManager();
    @Override
    public int getLayoutId() {
        return R.layout.activity_workout_virtual_reality;
    }

    @Override
    protected void init() {
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

    @OnClick(R.id.bottom_logo_tab_home)
    public void onBackHome() {
        finishActivity();
    }
}
