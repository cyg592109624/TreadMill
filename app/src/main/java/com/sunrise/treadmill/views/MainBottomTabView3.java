package com.sunrise.treadmill.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sunrise.treadmill.R;

/**
 * Created by ChuHui on 2017/9/20.
 */

public class MainBottomTabView3 extends LinearLayout {
    private ImageView icon;

    public MainBottomTabView3(Context context) {
        this(context, null);
    }

    public MainBottomTabView3(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MainBottomTabView3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.bottom_logo_tab3, this, true);
        icon = (ImageView) findViewById(R.id.bottom_logo);
        //这里应该有一个判断 来决定当前icon的外观
    }

    public void setIcon(Bitmap bitmap) {
        icon.setImageBitmap(bitmap);
    }
}
