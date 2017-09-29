package com.sunrise.treadmill.activity.workoutrunning;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragment;
import com.sunrise.treadmill.base.BaseFragmentActivity;
import com.sunrise.treadmill.dialog.workoutrunning.HillRunningPauseDialog;
import com.sunrise.treadmill.utils.ImageUtils;
import com.sunrise.treadmill.views.LevelView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/27.
 */

public class HillRunningActivity extends BaseFragmentActivity {

    @BindView(R.id.workout_running_left)
    LinearLayout animView;

    @BindView(R.id.workout_running_level_view)
    LevelView levelView;

    @BindView(R.id.workout_running_stop)
    ImageView stopImg;


    private int parentWidth;
    private int parentHeight;

    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    public int getLayoutId() {
        return R.layout.activity_workout_running;
    }

    @Override
    protected void init() {
        parentWidth = getWindowManager().getDefaultDisplay().getWidth();
        parentHeight = getWindowManager().getDefaultDisplay().getHeight();

        levelView.setColumnMargin(1f);
        levelView.calcLength();
        levelView.setBuoyBitmap(15, 11);
        levelView.setHintText(getResources().getString(R.string.workout_mode_hill));
        levelView.setSlideEnable(false);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        levelView.setTgLevel(4);
        levelView.setColumn(4, 11);
        levelView.reFlashView();
    }

    private boolean isChangeLefView = false;

    @OnClick(R.id.workout_running_left)
    public void showAnim() {
        if (!isChangeLefView) {
            isChangeLefView = true;
            anim(true);
        } else {
            isChangeLefView = false;
            anim(false);
        }
    }

    private void anim(boolean isScale) {
        PropertyValuesHolder scaleX = null;
        PropertyValuesHolder scaleY = null;
        PropertyValuesHolder translationX = null;
        PropertyValuesHolder translationY = null;
        if (isScale) {
            scaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, 1.4f);
            scaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, 1.4f);
            translationX = PropertyValuesHolder.ofFloat("translationX", 0, (parentWidth - animView.getWidth()) / 2);
            translationY = PropertyValuesHolder.ofFloat("translationY", 0, -(parentHeight - animView.getHeight()) / 5);
        } else {
            scaleX = PropertyValuesHolder.ofFloat("scaleX", 1.4f, 1f);
            scaleY = PropertyValuesHolder.ofFloat("scaleY", 1.4f, 1f);
            translationX = PropertyValuesHolder.ofFloat("translationX", (parentWidth - animView.getWidth()) / 2, 0);
            translationY = PropertyValuesHolder.ofFloat("translationY", -(parentHeight - animView.getHeight()) / 5, 0);
        }
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(animView, scaleX, scaleY, translationX, translationY);
        animator.setDuration(1000);
        animator.start();

    }

    @OnClick(R.id.workout_running_stop)
    public void stopSport() {
        HillRunningPauseDialog pauseDialog = new HillRunningPauseDialog();
        pauseDialog.show(fragmentManager, HillRunningPauseDialog.TAG);
    }

    @OnClick({R.id.workout_running_level_up, R.id.workout_running_level_down})
    public void changeLevel(View view) {
        switch (view.getId()) {
            default:
                break;
            case R.id.workout_running_level_up:
                break;
            case R.id.workout_running_level_down:
                break;
        }
    }

}
