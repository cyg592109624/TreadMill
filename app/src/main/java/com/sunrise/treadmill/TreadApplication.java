package com.sunrise.treadmill;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.media.AudioManager;

import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.SPUtils;
import com.sunrise.treadmill.utils.ScreenUtils;
import com.sunrise.treadmill.utils.SoundsUtils;

/**
 * Created by ChuHui on 2017/9/4.
 */

public class TreadApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        GlobalSetting.AppLanguage = LanguageUtils.getAppLanguage(getResources());
    }

    public static Context getContext() {
        return context;
    }
}
