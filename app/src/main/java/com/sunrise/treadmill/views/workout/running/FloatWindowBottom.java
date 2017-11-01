package com.sunrise.treadmill.views.workout.running;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.interfaces.services.FloatWindowBottomCallBack;
import com.sunrise.treadmill.utils.ImageUtils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by ChuHui on 2017/10/11.
 */

public class FloatWindowBottom extends ConstraintLayout {
    private FloatWindowBottomCallBack windowBottomCallBack;

    private ImageView bottomLevelUp, bottomLevelDown, bottomStart, bottomStop, bottomWindy, bottomHome, bottomBack;


    private int windy = 0;

    private ScheduledExecutorService scheduledExecutor;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int viewId = msg.what;
            switch (viewId) {
                default:
                    break;
                case R.id.workout_running_level_up:
                    windowBottomCallBack.onLevelUp();
                    break;
                case R.id.workout_running_level_down:
                    windowBottomCallBack.onLevelDown();
                    break;
            }
        }
    };

    public FloatWindowBottom(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FloatWindowBottom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.float_window_workout_running_bottom, this, true);


        bottomLevelUp = findViewById(R.id.workout_running_level_up);
        bottomLevelUp.setOnTouchListener(touchListener);
        bottomLevelUp.setOnClickListener(bottomClick);

        bottomLevelDown = findViewById(R.id.workout_running_level_down);
        bottomLevelDown.setOnTouchListener(touchListener);
        bottomLevelDown.setOnClickListener(bottomClick);


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

    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                default:
                    break;
                case MotionEvent.ACTION_DOWN:
                    updateAddOrSubtract(view.getId());
                    break;
                case MotionEvent.ACTION_UP:
                    stopAddOrSubtract();
                    break;
            }
            return true;
        }
    };

    private View.OnClickListener bottomClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (windowBottomCallBack != null) {
                switch (view.getId()) {
                    default:
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

    private void setLevelEnable() {
        if (FloatWindowHead.curLevel == FloatWindowHead.MAX_LEVEL) {
            bottomLevelUp.setEnabled(false);
            bottomLevelDown.setEnabled(true);
            stopAddOrSubtract();
        } else if (FloatWindowHead.curLevel == FloatWindowHead.MIN_LEVEL) {
            bottomLevelUp.setEnabled(true);
            bottomLevelDown.setEnabled(false);
            stopAddOrSubtract();
        } else {
            bottomLevelUp.setEnabled(true);
            bottomLevelDown.setEnabled(true);
        }
    }

    private void updateAddOrSubtract(int viewId) {
        final int vid = viewId;
        scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                Message msg = Message.obtain();
                msg.what = vid;
                handler.sendMessage(msg);
            }
        }, 0, 100, TimeUnit.MILLISECONDS);    //每间隔100ms发送Message
    }

    private void stopAddOrSubtract() {
        if (scheduledExecutor != null) {
            scheduledExecutor.shutdownNow();
            scheduledExecutor = null;
        }
    }


    public void setWindowBottomCallBack(FloatWindowBottomCallBack callBack) {
        this.windowBottomCallBack = callBack;
    }

    public void recycle() {
        bottomLevelUp = null;
        bottomLevelDown = null;

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
