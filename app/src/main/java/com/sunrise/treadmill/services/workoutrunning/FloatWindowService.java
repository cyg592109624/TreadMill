package com.sunrise.treadmill.services.workoutrunning;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.PixelFormat;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.dialog.workoutrunning.CountDownDialog;
import com.sunrise.treadmill.interfaces.FloatWindowBottomCallBack;
import com.sunrise.treadmill.views.FloatWindowBottom;
import com.sunrise.treadmill.views.FloatWindowHead;


/**
 * Created by ChuHui on 2017/10/14.
 */

public class FloatWindowService extends Service implements FloatWindowBottomCallBack {

    private final int FloatWindowNotification = 62111;
    private String runningActivityName = "";

    private WindowManager mWindowManager;

    private WindowManager.LayoutParams headParams;
    private WindowManager.LayoutParams bottomParams;
    private WindowManager.LayoutParams dialogParams;

    private FloatWindowHead floatWindowHead;
    private FloatWindowBottom floatWindowBottom;
    private RelativeLayout pauseDialog;

    private final IBinder floatBinder = new FloatBinder();

    public class FloatBinder extends Binder {
        /**
         * @return 主要为了获取该服务的实例对象
         */
        public FloatWindowService getService() {
            return FloatWindowService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return floatBinder;
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
        onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mWindowManager = (WindowManager) getApplication().getSystemService(getApplication().WINDOW_SERVICE);
        stageService();
        initWindowParams();
        initFloatWindow();
        initStopDialog();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
    }


    @Override
    public void onLevelUp() {
        floatWindowHead.levelChange(1);
    }

    @Override
    public void onLevelDown() {
        floatWindowHead.levelChange(-1);
    }

    @Override
    public void onWindyClick() {
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.sunrise.treadmill", runningActivityName);
        intent.setAction(runningActivityName);
        intent.setComponent(componentName);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onStopClick() {
        mWindowManager.addView(pauseDialog, bottomParams);
    }

    private boolean isShowView = false;

    /**
     * 自动判断当前应该添加还是删除悬浮窗口
     */
    public void toggleFloatWindow() {
        if (floatWindowHead != null && floatWindowBottom != null) {
            if (!isShowView) {
                mWindowManager.addView(floatWindowHead, headParams);
                mWindowManager.addView(floatWindowBottom, bottomParams);
            } else {
                mWindowManager.removeView(floatWindowHead);
                mWindowManager.removeView(floatWindowBottom);
            }
            isShowView = !isShowView;
        }
    }

    public void setRunningActivityName(String activityName) {
        this.runningActivityName = activityName;
    }

    /**
     * 切为前台服务
     */
    private void stageService() {
        Notification.Builder builder = new Notification.Builder(getApplicationContext());
        // builder.setContentInfo("补充内容");
        builder.setContentText("前台服务");
        builder.setContentTitle("悬浮窗口服务");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setAutoCancel(false);
        Notification notification = builder.build();
        startForeground(FloatWindowNotification, notification);
    }

    /**
     * 设置LayoutParams
     */
    private void initWindowParams() {
        headParams = setUpParams(0, -1080);
        bottomParams = setUpParams(0, 1080);
        dialogParams = setUpParams(0, 1080);
    }

    private WindowManager.LayoutParams setUpParams(int x, int y) {

        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        // 设置window type
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        params.format = PixelFormat.RGBA_8888; // 设置图片格式，效果为背景透明

        // 设置Window flag
        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_FULLSCREEN
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;

        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.horizontalMargin = 0;
        params.verticalMargin = 0;
        params.x = x;
        params.y = y;
        return params;
    }

    /**
     * 创建悬浮窗口
     */
    private void initFloatWindow() {
        floatWindowHead = new FloatWindowHead(getApplicationContext());
        floatWindowBottom = new FloatWindowBottom(getApplicationContext());

        floatWindowHead.setLayoutParams(headParams);

        floatWindowBottom.setLayoutParams(bottomParams);
        floatWindowBottom.setWindowBottomCallBack(this);
    }

    private void initStopDialog() {
        pauseDialog = (RelativeLayout) LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_workout_running_pause, null);
        pauseDialog.setLayoutParams(dialogParams);
    }
}
