package com.sunrise.treadmill.views.workout.running;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.interfaces.services.FloatWindowBottomCallBack;
import com.sunrise.treadmill.utils.ImageUtils;

/**
 * Created by ChuHui on 2017/10/11.
 */

public class FloatWindowBottom extends ConstraintLayout {
    private FloatWindowBottomCallBack windowBottomCallBack;

    private ImageView bottomStart, bottomStop, bottomWindy, bottomHome, bottomBack;
    private int windy = 0;

    public FloatWindowBottom(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FloatWindowBottom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.float_window_workout_running_bottom, this, true);
        findViewById(R.id.workout_running_level_up).setOnClickListener(bottomClick);
        findViewById(R.id.workout_running_level_down).setOnClickListener(bottomClick);

        bottomWindy = findViewById(R.id.workout_running_windy);
        bottomWindy.setOnClickListener(bottomClick);

        bottomStart = findViewById(R.id.workout_running_start);
        bottomStart.setOnClickListener(bottomClick);

        bottomStop = findViewById(R.id.workout_running_stop);
        bottomStop.setOnClickListener(bottomClick);

        bottomHome = findViewById(R.id.workout_running_home);
        bottomHome.setOnClickListener(bottomClick);

        bottomBack = findViewById(R.id.workout_running_back);
        bottomBack.setOnClickListener(bottomClick);

    }

    private View.OnClickListener bottomClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (windowBottomCallBack != null) {
                switch (view.getId()) {
                    default:
                        break;
                    case R.id.workout_running_level_up:
                        windowBottomCallBack.onLevelUp();
                        break;
                    case R.id.workout_running_level_down:
                        windowBottomCallBack.onLevelDown();
                        break;
                    case R.id.workout_running_windy:
                        switch (windy) {
                            case 0:
                                windy = 1;
                                ImageUtils.changeImageView(bottomWindy, R.mipmap.btn_fan_2_1);
                                break;
                            case 1:
                                windy = 2;
                                ImageUtils.changeImageView(bottomWindy, R.mipmap.btn_fan_3_1);
                                break;
                            case 2:
                                windy = 3;
                                ImageUtils.changeImageView(bottomWindy, R.mipmap.btn_fan_4_1);
                                break;
                            case 3:
                                windy = 0;
                                ImageUtils.changeImageView(bottomWindy, R.mipmap.btn_fan_1_1);
                                break;
                        }
                        windowBottomCallBack.onWindyClick();
                        break;
                    case R.id.workout_running_start:
                        windowBottomCallBack.onStartClick();
                        break;
                    case R.id.workout_running_stop:
                        windowBottomCallBack.onStopClick();
                        break;
                    case R.id.workout_running_home:
                        windowBottomCallBack.onHomeClick();
                        break;
                    case R.id.workout_running_back:
                        windowBottomCallBack.onBackClick();
                        break;
                }
            }
        }
    };


    public void setWindowBottomCallBack(FloatWindowBottomCallBack callBack) {
        this.windowBottomCallBack = callBack;
    }

    public void recycle() {
        windowBottomCallBack = null;
        bottomWindy = null;

        bottomStart = null;
        bottomStop = null;

        bottomHome = null;
        bottomBack = null;
    }

    public void showStartBtn() {
        bottomStart.setVisibility(View.VISIBLE);
    }

    public void hideStartBtn() {
        bottomStart.setVisibility(View.GONE);
    }

    public void showStopBtn() {
        bottomStop.setVisibility(View.VISIBLE);
    }

    public void hideStopBtn() {
        bottomStop.setVisibility(View.GONE);
    }

    public void showHomeBtn() {
        bottomHome.setVisibility(View.VISIBLE);
    }

    public void hideHomeBtn() {
        bottomHome.setVisibility(View.GONE);
    }

    public void showBackBtn() {
        bottomBack.setVisibility(View.VISIBLE);
    }

    public void hideBackBtn() {
        bottomBack.setVisibility(View.GONE);
    }
}
