package com.sunrise.treadmill.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.activity.home.HomeActivity;
import com.sunrise.treadmill.base.BaseActivity;
import com.sunrise.treadmill.Constant;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.SPUtils;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class LogoActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    private int permissionRequestCode = 1001;
    private String[] lackOfPerms = {"android.permission.CHANGE_CONFIGURATION", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.WRITE_SETTINGS"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EasyPermissions.requestPermissions(this, "必要的权限", permissionRequestCode, lackOfPerms);
        syncLanguage();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_logo;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        //没有获取的权限
        String curPerm[] = new String[perms.size()];
        for (int i = 0; i < perms.size(); i++) {
            curPerm[i] = perms.get(i);
            System.out.println("没有被允许的权限 --> " + perms.get(i));
        }
        lackOfPerms = curPerm;
    }

    /**
     * 切换app语言
     */
    private void syncLanguage() {
        //启动app时 获取语言设置
        String appLanguage = (String) SPUtils.get(getApplicationContext(), Constant.appLanguage, LanguageUtils.en_US);
        //获取当前设置
        String nowAppLanguage = LanguageUtils.getAppLanguage(getResources());
        Intent intent = new Intent();
        try {
            if (appLanguage.equals(nowAppLanguage)) {
                intent.setClass(LogoActivity.this, HomeActivity.class);
            } else {
                GlobalSetting.AppLanguage = appLanguage;
                String[] he = appLanguage.split("_");
                LanguageUtils.updateLanguage(LanguageUtils.buildLocale(he[0], he[1]), getResources());
                intent.setClass(LogoActivity.this, LogoActivity.class);
                Thread.sleep(300);
            }
            finishActivity();
            startActivity(intent);
            onDestroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
