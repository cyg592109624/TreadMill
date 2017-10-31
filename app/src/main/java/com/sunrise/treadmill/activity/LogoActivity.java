package com.sunrise.treadmill.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.activity.home.HomeActivity;
import com.sunrise.treadmill.base.BaseActivity;
import com.sunrise.treadmill.Constant;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.SharedPreferencesUtils;
import com.sunrise.treadmill.utils.ThreadPoolUtils;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class LogoActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    private int permissionRequestCode = 1001;
    private int permissionRequestCode2 = 1002;
    private String[] lackOfPerms = {"android.permission.CHANGE_CONFIGURATION", "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.WRITE_SETTINGS",
            "android.permission.SYSTEM_ALERT_WINDOW", "android.permission.GET_TASKS",
            "android.permission.REORDER_TASKS"};
    private int permissionSize = lackOfPerms.length;
    private int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EasyPermissions.requestPermissions(this, "必要的权限", permissionRequestCode, lackOfPerms);
        ThreadPoolUtils.runTaskOnThread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        Thread.sleep(200);
                        System.out.println("size   -----》" + size);
                        if (size == permissionSize) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    syncLanguage();
                                }
                            });
                            return;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_logo;
    }

    @Override
    public void recycleObject() {
        lackOfPerms = null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == permissionRequestCode) {
            EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, activityContext);
        }else {

        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {//没有获取的权限
        String[] args = new String[perms.size()];
        for (int i = 0; i < perms.size(); i++) {
            args[i] = perms.get(i);
            System.out.println("被允许的权限 --> " + perms.get(i));
            size++;
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        //没有获取的权限
        String[] args = new String[perms.size()];
        for (int i = 0; i < perms.size(); i++) {
            args[i] = perms.get(i);
            System.out.println("没有被允许的权限 --> " + perms.get(i));
            size++;
        }
        lackOfPerms = args;
    }

    /**
     * 切换app语言
     */
    private void syncLanguage() {
        //启动app时 获取语言设置
        String appLanguage = (String) SharedPreferencesUtils.get(activityContext, Constant.APP_LANGUAGE, LanguageUtils.zh_CN);
        //获取当前设置
        String nowAppLanguage = LanguageUtils.getAppLanguage(activityContext.getResources());
        Intent intent = new Intent();
        try {
            if (appLanguage.equals(nowAppLanguage)) {
                intent.setClass(activityContext, HomeActivity.class);
            } else {
                GlobalSetting.AppLanguage = appLanguage;
                String[] he = appLanguage.split("_");
                LanguageUtils.updateLanguage(LanguageUtils.buildLocale(he[0], he[1]), activityContext.getResources());
                intent.setClass(activityContext, LogoActivity.class);
                Thread.sleep(300);
            }
            finishActivity();
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
