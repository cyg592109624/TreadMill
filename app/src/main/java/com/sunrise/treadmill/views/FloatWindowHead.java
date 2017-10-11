package com.sunrise.treadmill.views;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;

import com.sunrise.treadmill.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ChuHui on 2017/10/11.
 */

public class FloatWindowHead extends ConstraintLayout {
    private int curLevel = 0;

    @BindView(R.id.workout_running_head_level_value)
    TextView levelValue;

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
    }

    public void levelChange(int i) {
        curLevel += i;
        if (curLevel > 30 | curLevel < 0) {
            return;
        }
        levelValue.setText(curLevel + "");
    }
}
