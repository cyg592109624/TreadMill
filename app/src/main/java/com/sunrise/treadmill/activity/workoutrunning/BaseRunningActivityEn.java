package com.sunrise.treadmill.activity.workoutrunning;

import android.view.View;
import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/30.
 */

public class BaseRunningActivityEn extends BaseRunningActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_workout_running;
    }

    @Override
    protected void setTextStyle() {
        List<TextView> txtList = new ArrayList<>();
        txtList.add((TextView) findViewById(R.id.workout_running_media_youtube_name));
        txtList.add((TextView) findViewById(R.id.workout_running_media_chrome_name));
        txtList.add((TextView) findViewById(R.id.workout_running_media_facebook_name));
        txtList.add((TextView) findViewById(R.id.workout_running_media_flikr_name));
        txtList.add((TextView) findViewById(R.id.workout_running_media_instagram_name));

        txtList.add((TextView) findViewById(R.id.workout_running_media_mp3_name));
        txtList.add((TextView) findViewById(R.id.workout_running_media_mp4_name));
        txtList.add((TextView) findViewById(R.id.workout_running_media_av_name));
        txtList.add((TextView) findViewById(R.id.workout_running_media_twitter_name));
        txtList.add((TextView) findViewById(R.id.workout_running_media_screen_mirroring_name));

        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList, TextUtils.Microsoft(activityContext));
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.Arial(activityContext));
        }
        txtList.clear();
        txtList=null;
    }

    @OnClick({R.id.workout_running_media_youtube, R.id.workout_running_media_chrome, R.id.workout_running_media_facebook,
            R.id.workout_running_media_flikr, R.id.workout_running_media_instagram, R.id.workout_running_media_mp3,
            R.id.workout_running_media_mp4, R.id.workout_running_media_av, R.id.workout_running_media_twitter,
            R.id.workout_running_media_screen_mirroring})
    public void mediaClick(View view) {
        switch (view.getId()) {
            default:
                break;
        }
        media2Pop();
    }
}
