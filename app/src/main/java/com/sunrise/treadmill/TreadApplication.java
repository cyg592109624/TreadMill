package com.sunrise.treadmill;

import android.app.Application;

import com.sunrise.treadmill.utils.Constant;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.SPUtils;

/**
 * Created by ChuHui on 2017/9/4.
 */

public class TreadApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        GlobalSetting.AppLanguage= LanguageUtils.getAppLanguage(getResources());
    }
}
