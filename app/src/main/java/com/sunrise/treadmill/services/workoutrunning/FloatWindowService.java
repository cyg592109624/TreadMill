package com.sunrise.treadmill.services.workoutrunning;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.interfaces.FloatWindowBottomCallBack;
import com.sunrise.treadmill.views.FloatWindowBottom;
import com.sunrise.treadmill.views.FloatWindowHead;

import java.util.List;

/**
 * Created by ChuHui on 2017/10/11.
 */

public class FloatWindowService extends Service implements FloatWindowBottomCallBack {
    private final int FloatWindowNotification = 62111;
    private String workOutRunningActivity = "";

    private WindowManager mWindowManager;
    public PackageManager packageManager;

    private WindowManager.LayoutParams headParams;
    private WindowManager.LayoutParams bottomParams;

    private FloatWindowHead floatWindowHead;
    private FloatWindowBottom floatWindowBottom;

    private ActivityManager mActivityManager;

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
        mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        packageManager=getApplicationContext().getPackageManager();
        stageService();
        initWindowParams();
        initFloatWindow();
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
        //尝试再这里唤醒activity 7.0这里有问题
//        List<ActivityManager.AppTask> list = mActivityManager.getAppTasks();
//        for (ActivityManager.AppTask running : list) {
//            System.out.println(running.getTaskInfo().baseActivity.getClassName());
//          if (running.baseActivity.getClassName().equals(workOutRunningActivity)) {
//               mActivityManager.moveTaskToFront(running.pid, ActivityManager.MOVE_TASK_WITH_HOME);
//           }
//        }
        packageManager.getLaunchIntentForPackage(workOutRunningActivity);

    }

    private boolean isShowView = false;

    public void showFloatWindow() {
        if (floatWindowHead != null && floatWindowBottom != null) {
            if (!isShowView) {
                isShowView = true;
                mWindowManager.addView(floatWindowHead, headParams);
                mWindowManager.addView(floatWindowBottom, bottomParams);
            }
        }
    }

    public void hideFloatWindow() {
        if (floatWindowHead != null && floatWindowBottom != null) {
            if (!isShowView) {
                isShowView = false;
                mWindowManager.removeView(floatWindowHead);
                mWindowManager.removeView(floatWindowBottom);
            }
        }
    }

    public void setRunningActivityName(String activityName) {
        this.workOutRunningActivity = activityName;
    }

    /**
     * 切为前台服务
     */
    private void stageService() {
        Notification.Builder builder = new Notification.Builder(getApplicationContext());
        // builder.setContentInfo("补充内容");
        builder.setContentText("PhyConn");
        builder.setContentTitle("正在运行的HeightBleService");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setAutoCancel(false);
        Notification notification = builder.build();
        startForeground(FloatWindowNotification, notification);
    }

    /**
     * 设置LayoutParams
     */
    private void initWindowParams() {
        mWindowManager = (WindowManager) getApplication().getSystemService(getApplication().WINDOW_SERVICE);

        headParams = new WindowManager.LayoutParams();
        bottomParams = new WindowManager.LayoutParams();
        // 设置window type
        headParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        headParams.format = PixelFormat.RGBA_8888; // 设置图片格式，效果为背景透明


        bottomParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        bottomParams.format = PixelFormat.RGBA_8888; // 设置图片格式，效果为背景透明

        // 设置Window flag
        headParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_FULLSCREEN
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;

        bottomParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_FULLSCREEN
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;

        headParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        headParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        headParams.horizontalMargin = 0;
        headParams.verticalMargin = 0;

        bottomParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        bottomParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        bottomParams.horizontalMargin = 0;
        bottomParams.verticalMargin = 0;

        headParams.x = 0;
        headParams.y = -1080;

        bottomParams.x = 0;
        bottomParams.y = 1090;


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

}