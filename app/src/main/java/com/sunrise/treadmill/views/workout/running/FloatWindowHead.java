package com.sunrise.treadmill.views.workout.running;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ChuHui on 2017/10/11.
 */

public class FloatWindowHead extends ConstraintLayout {
    public int curLevel = 0;
    private static final int MAX_LEVEL = 30;
    private static final int MIN_LEVEL = 0;

    @BindView(R.id.workout_running_head_level_value)
    TextView levelValue;
    @BindView(R.id.workout_running_head_time_value)
    TextView timeValue;
    @BindView(R.id.workout_running_head_distance_value)
    TextView distanceValue;
    @BindView(R.id.workout_running_head_calories_value)
    TextView caloriesValue;
    @BindView(R.id.workout_running_head_pulse_value)
    TextView pulseValue;
    @BindView(R.id.workout_running_head_watt_value)
    TextView wattValue;
    @BindView(R.id.workout_running_head_speed_value)
    TextView speedValue;

    public FloatWindowHead(Context context) {
        this(context, null);
    }

    public FloatWindowHead(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FloatWindowHead(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.float_window_workout_running_head, this, true);
        ButterKnife.bind(this);
        levelValue.setText("" + curLevel);
        setTextStyle();
    }

    private void setTextStyle() {
        List<TextView> txtList = new ArrayList<>();
        txtList.add((TextView) findViewById(R.id.workout_running_head_level));
        txtList.add((TextView) findViewById(R.id.workout_running_head_time));
        txtList.add((TextView) findViewById(R.id.workout_running_head_distance));
        txtList.add((TextView) findViewById(R.id.workout_running_head_calories));
        txtList.add((TextView) findViewById(R.id.workout_running_head_pulse));
        txtList.add((TextView) findViewById(R.id.workout_running_head_watt));
        txtList.add((TextView) findViewById(R.id.workout_running_head_speed));

        txtList.add(levelValue);
        txtList.add(timeValue);
        txtList.add(distanceValue);
        txtList.add(caloriesValue);
        txtList.add(pulseValue);
        txtList.add(wattValue);
        txtList.add(speedValue);
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList, TextUtils.Microsoft(getContext()));
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.Arial(getContext()));
        }
    }

    public void levelChange(int i) {
        curLevel += i;
        if (curLevel > MAX_LEVEL) {
            curLevel = MAX_LEVEL;
            return;
        }
        if (curLevel < MIN_LEVEL) {
            curLevel = MIN_LEVEL;
            return;
        }
        levelValue.setText(curLevel + "");
    }

    public void setLevelValue(int i) {
        curLevel = i;
        levelValue.setText(curLevel + "");
    }

}
