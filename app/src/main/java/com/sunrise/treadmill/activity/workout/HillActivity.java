package com.sunrise.treadmill.activity.workout;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseActivity;
import com.sunrise.treadmill.interfaces.OnGenderReturn;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;
import com.sunrise.treadmill.views.MyGenderView;
import com.sunrise.treadmill.views.MyWorkOutHead;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/20.
 */

public class HillActivity extends BaseActivity implements OnGenderReturn {
    @BindView(R.id.workout_mode_hill_head)
    MyWorkOutHead headView;

    @BindView(R.id.workout_mode_gender_select)
    MyGenderView genderView;

    @BindView(R.id.workout_mode_hill_age_value)
    TextView ageValue;
    @BindView(R.id.workout_mode_hill_weight_value)
    TextView weightValue;
    @BindView(R.id.workout_mode_hill_time_value)
    TextView timeValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_hill;
    }

    @Override
    protected void setTextStyle() {
        List<TextView> txtList = new ArrayList<TextView>();
        txtList.add((TextView) headView.findViewById(R.id.workout_head_mode));
        txtList.add((TextView) headView.findViewById(R.id.workout_head_hint));
        txtList.add((TextView) findViewById(R.id.workout_mode_hill_age));
        txtList.add((TextView) findViewById(R.id.workout_mode_hill_weight));
        txtList.add((TextView) findViewById(R.id.workout_mode_hill_time));
        txtList.add((TextView) findViewById(R.id.workout_mode_hill_start_hint));

        txtList.add(ageValue);
        txtList.add(weightValue);
        txtList.add(timeValue);
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList, TextUtils.Microsoft(this));
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.Arial(this));
        }
    }

    @OnClick(R.id.bottom_logo_tab_home)
    public void onBackHome() {
        finishActivity();
    }

    @OnClick(R.id.workout_mode_hill_start)
    public void onStartWorkOut() {

    }

    @Override
    public void genderReturn(int gender) {

    }
    private void init() {
        headView.setHeadMsg(getResources().getString(R.string.workout_mode_hill), getResources().getString(R.string.workout_mode_hint_f), R.mipmap.img_program_hill_icon);
        genderView.setOnGenderReturn(this);
    }

}
