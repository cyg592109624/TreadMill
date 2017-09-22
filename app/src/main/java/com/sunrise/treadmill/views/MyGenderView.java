package com.sunrise.treadmill.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.interfaces.OnGenderReturn;

/**
 * Created by ChuHui on 2017/9/20.
 */

public class MyGenderView extends LinearLayout {
    private RadioButton male, female;
    private OnGenderReturn onGenderReturn;
    private ImageView genderImg;
    private final int MALE = 0;
    private final int FEMALE = 1;

    public MyGenderView(Context context) {
        this(context, null);
    }

    public MyGenderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyGenderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.workout_gender, this, true);
        genderImg = findViewById(R.id.gender_img);

        male = findViewById(R.id.gender_male);
        female = findViewById(R.id.gender_female);


        male.setOnClickListener(radioClick);
        female.setOnClickListener(radioClick);
    }

    public void setOnGenderReturn(OnGenderReturn genderReturn) {
        onGenderReturn = genderReturn;
    }

    private View.OnClickListener radioClick = new OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.gender_male:
                    male.setChecked(true);
                    female.setChecked(false);
                    onGenderReturn.genderReturn(MALE);
                    genderImg.setImageResource(R.mipmap.img_gender_draw_1);
                    break;
                case R.id.gender_female:
                    male.setChecked(false);
                    female.setChecked(true);
                    onGenderReturn.genderReturn(FEMALE);
                    genderImg.setImageResource(R.mipmap.img_gender_draw_2);
                    break;
            }
        }
    };
}
