package com.sunrise.treadmill.activity.workoutrunning;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragmentActivity;
import com.sunrise.treadmill.dialog.workoutrunning.CountDownDialog;
import com.sunrise.treadmill.dialog.workoutrunning.HillRunningPauseDialog;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;
import com.sunrise.treadmill.views.LevelView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/30.
 */

public class BaseRunningActivity extends BaseFragmentActivity {

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


    @BindView(R.id.workout_running_left)
    LinearLayout leftView;

    @BindView(R.id.workout_running_media_layout)
    FrameLayout rightLayout;

    @BindView(R.id.workout_running_level_view)
    LevelView levelView;

    @BindView(R.id.workout_running_media_1)
    ConstraintLayout media1;

    @BindView(R.id.workout_running_media_2)
    ImageView media2;

    private int parentWidth;
    private int parentHeight;

    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    public int getLayoutId() {
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            return R.layout.activity_workout_running_zh;
        } else {
            return R.layout.activity_workout_running;
        }
    }

    @Override
    protected void init() {
        showCountDownDialog();

        parentWidth = getWindowManager().getDefaultDisplay().getWidth();
        parentHeight = getWindowManager().getDefaultDisplay().getHeight();

        levelView.setColumnMargin(1f);
        levelView.calcLength();
        levelView.setBuoyBitmap(15, 11);
        levelView.setHintText(getResources().getString(R.string.workout_mode_hill));
        levelView.setSlideEnable(false);
    }

    @Override
    protected void setTextStyle() {
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
            TextUtils.setTextTypeFace(txtList, TextUtils.Microsoft(this));
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.Arial(this));
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        setUpLevelView();
    }

    private boolean isShowView = false;
    private boolean isAnimLeftView = false;
    private boolean isAnimRightView = false;

    @OnClick(R.id.workout_running_left)
    public void showLeftView() {
        if (isShowView) {
            if (isAnimLeftView) {
                isShowView = false;
                isAnimLeftView = false;
                animLeftView(false);
            }
        } else {
            if (!isAnimLeftView) {
                isShowView = true;
                isAnimLeftView = true;
                animLeftView(true);
            }
        }
    }

    @OnClick(R.id.workout_running_media_2)
    public void media2Pop() {
        if (!isShowView) {
            if (!isAnimLeftView) {
                isShowView = true;
                isAnimRightView = true;
                animRightView(true);
            }
        }
    }

    @OnClick(R.id.workout_running_media_1)
    public void media1Pop() {
        if (isShowView) {
            if (isAnimRightView) {
                isShowView = false;
                isAnimRightView = false;
                animRightView(false);
            }
        }
    }

    @OnClick(R.id.workout_running_stop)
    public void stopSport() {
        HillRunningPauseDialog pauseDialog = new HillRunningPauseDialog();
        pauseDialog.show(fragmentManager, HillRunningPauseDialog.TAG);
    }

    @OnClick({R.id.workout_running_level_up, R.id.workout_running_level_down})
    public void changeLevel(View view) {

    }

    private void animLeftView(boolean isScale) {
        PropertyValuesHolder scaleX = null;
        PropertyValuesHolder scaleY = null;
        PropertyValuesHolder translationX = null;
        PropertyValuesHolder translationY = null;
        if (isScale) {
            scaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, 1.4f);
            scaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, 1.4f);
            translationX = PropertyValuesHolder.ofFloat("translationX", 0, (parentWidth - leftView.getWidth()) / 2);
            translationY = PropertyValuesHolder.ofFloat("translationY", 0, -(parentHeight - leftView.getHeight()) / 5);
        } else {
            scaleX = PropertyValuesHolder.ofFloat("scaleX", 1.4f, 1f);
            scaleY = PropertyValuesHolder.ofFloat("scaleY", 1.4f, 1f);
            translationX = PropertyValuesHolder.ofFloat("translationX", (parentWidth - leftView.getWidth()) / 2, 0);
            translationY = PropertyValuesHolder.ofFloat("translationY", -(parentHeight - leftView.getHeight()) / 5, 0);
        }
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(leftView, scaleX, scaleY, translationX, translationY);
        animator.setDuration(1000);
        animator.start();

    }

    private void animRightView(final boolean translation) {
        PropertyValuesHolder translationX = null;
        if (translation) {
            translationX = PropertyValuesHolder.ofFloat("translationX", 0, -media1.getWidth());
        } else {
            translationX = PropertyValuesHolder.ofFloat("translationX", -media1.getWidth(), 0);
        }
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(rightLayout, translationX);
        animator.setDuration(1000);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                if (translation) {
                    media1.setVisibility(View.VISIBLE);
                    media2.setVisibility(View.GONE);
                }
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (!translation) {
                    media1.setVisibility(View.INVISIBLE);
                    media2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator.start();
    }

    private void setUpLevelView() {
        levelView.setTgLevel(4);
        levelView.setColumn(4, 11);
        levelView.setTgLevel(4);
        levelView.setColumn(4, 11);
        levelView.reFlashView();
        levelView.setTgLevel(4);
        levelView.setColumn(4, 11);
    }

    private void showCountDownDialog() {
        CountDownDialog downDialog = new CountDownDialog();
        downDialog.show(fragmentManager, CountDownDialog.TAG);
    }
}
