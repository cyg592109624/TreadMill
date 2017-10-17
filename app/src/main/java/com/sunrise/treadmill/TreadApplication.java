package com.sunrise.treadmill;

import android.app.Application;
import android.content.Context;

import com.sunrise.treadmill.utils.LanguageUtils;

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
