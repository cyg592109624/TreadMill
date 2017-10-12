package com.sunrise.treadmill.activity.workoutrunning;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragmentActivity;
import com.sunrise.treadmill.dialog.workoutrunning.CountDownDialog;
import com.sunrise.treadmill.dialog.workoutrunning.HillRunningPauseDialog;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;
import com.sunrise.treadmill.views.LevelView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/30.
 */

public class BaseRunningActivity_zh extends BaseRunningActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_workout_running_zh;
    }
    @Override
    protected void setTextStyle() {
        super.setTextStyle();
        List<TextView> txtList = new ArrayList<>();

        txtList.add((TextView) findViewById(R.id.workout_running_media_bai_name));
        txtList.add((TextView) findViewById(R.id.workout_running_media_weibo_name));
        txtList.add((TextView) findViewById(R.id.workout_running_media_i71_name));
        txtList.add((TextView) findViewById(R.id.workout_running_media_av_name));
        txtList.add((TextView) findViewById(R.id.workout_running_media_mp3_name));

        txtList.add((TextView) findViewById(R.id.workout_running_media_mp4_name));
        txtList.add((TextView) findViewById(R.id.workout_running_media_screen_mirroring_name));

        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList, TextUtils.Microsoft(this));
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.Arial(this));
        }
    }

    @OnClick({R.id.workout_running_media_bai, R.id.workout_running_media_weibo, R.id.workout_running_media_i71,
            R.id.workout_running_media_av, R.id.workout_running_media_mp3, R.id.workout_running_media_mp4,
            R.id.workout_running_media_screen_mirroring})
    public void mediaClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            default:
                break;
            case R.id.workout_running_media_mp3:
                intent = packageManager.getLaunchIntentForPackage("com.android.mediacenter");
                break;
        }
        if (intent != null) {
            startActivity(intent);
            floatWindowServer.showFloatWindow();
        }
        media2Pop();
    }
}
