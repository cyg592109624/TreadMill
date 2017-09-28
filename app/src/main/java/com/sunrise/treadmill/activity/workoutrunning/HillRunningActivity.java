package com.sunrise.treadmill.activity.workoutrunning;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragment;
import com.sunrise.treadmill.base.BaseFragmentActivity;
import com.sunrise.treadmill.utils.ImageUtils;
import com.sunrise.treadmill.views.LevelView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/27.
 */

public class HillRunningActivity extends BaseFragmentActivity {

    @BindView(R.id.workout_running_left)
    LinearLayout leftView;

    @BindView(R.id.workout_running_level_view)
    LevelView levelView;


    @BindView(R.id.workout_running_stop)
    ImageView stopImg;


    private static final int ANIMAL_TIME = 5000;
    private static final int STOP_ANIMAL = 20001;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                default:
                    break;
                case STOP_ANIMAL:
                    isChangeLefView = false;
                    levelView.clearAnimation();
                    break;
                case 2:
                    break;
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_workout_running;
    }

    @Override
    protected void init() {
        levelView.setSpace(49f, 27f, 39f, 1f);
        levelView.setColumnMargin(1);
        levelView.setColumnWidth(19);
        levelView.calcLength();
        levelView.setBuoyBitmap(15, 11);
        levelView.setTgLevel(9);
        levelView.reFlashView();
        levelView.setSlideEnable(false);
    }

    private boolean isChangeLefView = false;

    @OnClick(R.id.workout_running_left)
    public void showAnim() {
        if (isChangeLefView) {
            return;
        }
        isChangeLefView = true;
        leftView.startAnimation(AnimationUtils.loadAnimation(HillRunningActivity.this, R.anim.workout_running_lef));

        mHandler.sendEmptyMessageDelayed(STOP_ANIMAL, ANIMAL_TIME);
    }


    @OnClick(R.id.workout_running_stop)
    public void stopSport() {
//        ImageUtils.changeImageView(stopImg, R.drawable.btn_workout_running_start);
        finishActivity();
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
