package com.sunrise.treadmill.views.workout.running;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.interfaces.services.FloatWindowBottomCallBack;

/**
 * Created by ChuHui on 2017/10/11.
 */

public class FloatWindowBottom extends ConstraintLayout {
    private FloatWindowBottomCallBack windowBottomCallBack;

    public FloatWindowBottom(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FloatWindowBottom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.float_window_workout_running_bottom, this, true);
        findViewById(R.id.workout_running_level_up).setOnClickListener(bottomClick);
        findViewById(R.id.workout_running_level_down).setOnClickListener(bottomClick);
        findViewById(R.id.workout_running_windy).setOnClickListener(bottomClick);
        findViewById(R.id.workout_running_stop).setOnClickListener(bottomClick);
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
                        windowBottomCallBack.onWindyClick();
                        break;
                    case R.id.workout_running_stop:
                        windowBottomCallBack.onStopClick();
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
    }
}
