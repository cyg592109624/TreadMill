package com.sunrise.treadmill.views.workout.setting;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.interfaces.workout.setting.OnGenderReturn;
import com.sunrise.treadmill.utils.ImageUtils;

/**
 * Created by ChuHui on 2017/9/20.
 */

public class MyGenderView extends ConstraintLayout {
    private RadioButton male, female;
    private OnGenderReturn onGenderReturn;
    private ImageView genderImg;
    private ConstraintLayout bg;

    private final int MALE = 0;
    private final int FEMALE = 1;
    private int hasClick = 0;

    public MyGenderView(Context context) {
        this(context, null);
    }

    public MyGenderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyGenderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.workout_gender, this, true);

        bg = findViewById(R.id.gender_bg);
        genderImg = findViewById(R.id.gender_img);

        male = findViewById(R.id.gender_male);
        female = findViewById(R.id.gender_female);


        male.setOnClickListener(radioClick);
        female.setOnClickListener(radioClick);
    }

    public void setOnGenderReturn(OnGenderReturn genderReturn) {
        onGenderReturn = genderReturn;
    }

    public void recycle() {
        male = null;
        female = null;
        onGenderReturn = null;
        genderImg = null;
        bg = null;
    }

    private View.OnClickListener radioClick = new OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                default:
                    break;
                case R.id.gender_male:
                    male.setChecked(true);
                    female.setChecked(false);
                    onGenderReturn.genderReturn(MALE);
                    ImageUtils.changeImageView(genderImg, R.mipmap.img_gender_draw_1);
                    if (hasClick == 0) {
                        hasClick = 1;
                    }
                    break;
                case R.id.gender_female:
                    male.setChecked(false);
                    female.setChecked(true);
                    onGenderReturn.genderReturn(FEMALE);
                    ImageUtils.changeImageView(genderImg, R.mipmap.img_gender_draw_2);
                    if (hasClick == 0) {
                        hasClick = 1;
                    }
                    break;
            }
            if (hasClick == 1) {
                hasClick = -1;
                bg.setBackgroundResource(R.mipmap.img_gender_frame_2);
            }
        }
    };
}
