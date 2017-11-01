package com.sunrise.treadmill.activity.workoutrunning;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;
import com.sunrise.treadmill.utils.ThreadPoolUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/30.
 */

public class BaseRunningActivityZh extends BaseRunningActivity {
    private static final int OPEN_APP = 10001;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Intent intent = null;
            switch (msg.what) {
                default:
                    break;
                case OPEN_APP:
                    intent = msg.getData().getParcelable("Intent");
                    break;
            }
            if (intent != null) {
                startActivity(intent);
                floatWindowServer.toggleFloatWindow();
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_workout_running_zh;
    }

    @Override
    public void recycleObject() {
        super.recycleObject();
        mHandler = null;
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
            TextUtils.setTextTypeFace(txtList, TextUtils.Microsoft(activityContext));
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.Arial(activityContext));
        }
        txtList.clear();
        txtList=null;
    }

    @OnClick({R.id.workout_running_media_bai, R.id.workout_running_media_weibo, R.id.workout_running_media_i71,
            R.id.workout_running_media_av, R.id.workout_running_media_mp3, R.id.workout_running_media_mp4,
            R.id.workout_running_media_screen_mirroring})
    public void mediaClick(final View view) {
        final Message msg = Message.obtain();
        Bundle bundle = new Bundle();
        Intent intent = null;
        switch (view.getId()) {
            default:
                break;
            case R.id.workout_running_media_mp3:
                msg.what = OPEN_APP;
                intent = packageManager.getLaunchIntentForPackage("com.android.mediacenter");
                bundle.putParcelable("Intent", intent);
                msg.setData(bundle);
                break;
        }
        mediaPop();
        if (intent != null) {
            ThreadPoolUtils.runTaskOnThread(new Runnable() {
                @Override
                public void run() {
                    mHandler.sendMessage(msg);
                }
            });

        } else {
            bundle = null;
        }
    }
}
