package com.sunrise.treadmill;

import android.app.Application;
import android.app.Service;
import android.media.AudioManager;

import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.SPUtils;
import com.sunrise.treadmill.utils.ScreenUtils;
import com.sunrise.treadmill.utils.SoundsUtils;

/**
 * Created by ChuHui on 2017/9/4.
 */

public class TreadApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        GlobalSetting.AppLanguage = LanguageUtils.getAppLanguage(getResources());

//        GlobalSetting.AppBrightness = (int) SPUtils.get(getApplicationContext(), Constant.appBrightness, -1);
//        GlobalSetting.AppSounds = (int) SPUtils.get(getApplicationContext(), Constant.appSounds, -1);
//        if (GlobalSetting.AppBrightness == -1) {
//            GlobalSetting.AppBrightness = ScreenUtils.getScreenBrightness(getContentResolver());
//            SPUtils.put(getApplicationContext(), Constant.appBrightness, GlobalSetting.AppBrightness);
//        }
//        if (GlobalSetting.AppSounds == -1) {
//            SoundsUtils.setAudioManager((AudioManager) getSystemService(Service.AUDIO_SERVICE));
//            GlobalSetting.AppSounds = SoundsUtils.getVoiceSystem();
//            SPUtils.put(getApplicationContext(), Constant.appSounds, GlobalSetting.AppSounds);
//        }
    }
}
