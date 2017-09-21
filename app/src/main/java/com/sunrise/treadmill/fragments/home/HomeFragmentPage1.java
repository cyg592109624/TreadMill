package com.sunrise.treadmill.fragments.home;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.activity.workout.HillActivity;
import com.sunrise.treadmill.base.BaseFragment;

import java.util.List;

import butterknife.BindViews;
import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/12.
 */

public class HomeFragmentPage1 extends BaseFragment {

    @BindViews({R.id.workout_mode_hill})
    List<ImageView> workOutList;

    @Override
    public int getLayoutId() {
        return R.layout.home_fragment_page_1;
    }

    @OnClick({R.id.workout_mode_hill})
    void selectWorkOutMode(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.workout_mode_hill:
                intent.setClass(getActivity(), HillActivity.class);
                break;
        }
        startActivity(intent);
    }

}
