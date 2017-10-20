package com.sunrise.treadmill;

import android.app.Application;
import android.content.Context;

import com.sunrise.treadmill.utils.LanguageUtils;

/**
 * Created by ChuHui on 2017/9/4.
 */

public class TreadApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        GlobalSetting.AppLanguage = LanguageUtils.getAppLanguage(getResources());
    }

}
