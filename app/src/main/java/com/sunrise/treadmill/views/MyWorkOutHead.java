package com.sunrise.treadmill.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sunrise.treadmill.R;

/**
 * Created by ChuHui on 2017/9/20.
 */

public class MyWorkOutHead extends LinearLayout {
    private TextView workOutMode;

    private TextView workOutHint;
    private ImageView workOutIcon;

    public MyWorkOutHead(Context context) {
        this(context, null);
    }

    public MyWorkOutHead(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyWorkOutHead(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.workout_head, this, true);
        workOutMode = findViewById(R.id.workout_head_mode);
        workOutHint = findViewById(R.id.workout_head_hint);
        workOutIcon = findViewById(R.id.workout_head_icon);
    }

    public void setHeadMsg(String title, String hint, int imgResource) {
        workOutMode.setText(title);
        workOutHint.setText(hint);
        workOutIcon.setImageResource(imgResource);
    }

    public void setWorkOutModeName(String title) {
        workOutMode.setText(title);
    }

    public void setWorkOutHint(String hint) {
        workOutHint.setText(hint);
    }

    public void setWorkOutIcon(int imgResource) {
        workOutIcon.setImageResource(imgResource);
    }

}
