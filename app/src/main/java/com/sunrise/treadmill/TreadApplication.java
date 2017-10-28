package com.sunrise.treadmill;

import android.app.Application;

import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.SharedPreferencesUtils;
import com.sunrise.treadmill.utils.ThreadPoolUtils;

/**
 * Created by ChuHui on 2017/9/4.
 */

public class TreadApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        GlobalSetting.AppLanguage = LanguageUtils.getAppLanguage(getResources());

        ThreadPoolUtils.runTaskOnThread(new Runnable() {
            @Override
            public void run() {

                //所以数据保存都是以国际标准保存的 使用时 按实际情况转换
                GlobalSetting.CustomerPassWord = (String) SharedPreferencesUtils.get(getApplicationContext(), Constant.CUSTOMER_PASS_WORD, GlobalSetting.CustomerPassWord);

                GlobalSetting.Setting_Time = (String) SharedPreferencesUtils.get(getApplicationContext(), Constant.SETTING_TIME, GlobalSetting.Setting_Time);
                GlobalSetting.Setting_RemainingTime = (String) SharedPreferencesUtils.get(getApplicationContext(), Constant.SETTING_REMAINING_TIME, GlobalSetting.Setting_RemainingTime);

                GlobalSetting.Setting_Distance = (String) SharedPreferencesUtils.get(getApplicationContext(), Constant.SETTING_DISTANCE, GlobalSetting.Setting_Distance);
                GlobalSetting.Setting_RemainingDistance = (String) SharedPreferencesUtils.get(getApplicationContext(), Constant.SETTING_REMAINING_DISTANCE, GlobalSetting.Setting_RemainingDistance);

                GlobalSetting.MachineType = (String) SharedPreferencesUtils.get(getApplicationContext(), Constant.MACHINE_TYPE, Constant.MACHINE_BIKE);
                GlobalSetting.UnitType = (String) SharedPreferencesUtils.get(getApplicationContext(), Constant.UNIT_TYPE, Constant.UNIT_TYPE_METRIC);

                GlobalSetting.Factory2Mode_Display = (boolean) SharedPreferencesUtils.get(getApplicationContext(), Constant.FACTORY2_MODE_DISPLAY, true);
                GlobalSetting.Factory2Mode_Pause = (boolean) SharedPreferencesUtils.get(getApplicationContext(), Constant.FACTORY2_MODE_PAUSE, true);
                GlobalSetting.Factory2Mode_KeyTone = (boolean) SharedPreferencesUtils.get(getApplicationContext(), Constant.FACTORY2_MODE_KEY_TONE, true);
                GlobalSetting.Factory2Mode_Buzzer = (boolean) SharedPreferencesUtils.get(getApplicationContext(), Constant.FACTORY2_MODE_BUZZER, true);
                GlobalSetting.Factory2Mode_ChildLock = (boolean) SharedPreferencesUtils.get(getApplicationContext(), Constant.FACTORY2_MODE_CHILD_LOCK, true);
                GlobalSetting.Factory2Mode_CtrlPage = (boolean) SharedPreferencesUtils.get(getApplicationContext(), Constant.FACTORY2_MODE_CTRL_PAGE, false);

                GlobalSetting.Factory2_Level = (String) SharedPreferencesUtils.get(getApplicationContext(), Constant.FACTORY2_LEVEL, "6");
                GlobalSetting.Factory2_PWM = (String) SharedPreferencesUtils.get(getApplicationContext(), Constant.FACTORY2_PWM, "9");

                GlobalSetting.Factory2_TotalTime = (String) SharedPreferencesUtils.get(getApplicationContext(), Constant.FACTORY2_TOTAL_TIME, "0");
                GlobalSetting.Factory2_TotalDistant = (String) SharedPreferencesUtils.get(getApplicationContext(), Constant.FACTORY2_TOTAL_DISTANT, "0");
            }
        });
    }

}
