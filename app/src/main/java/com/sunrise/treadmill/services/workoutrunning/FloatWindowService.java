package com.sunrise.treadmill.services.workoutrunning;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.PixelFormat;
import android.os.Binder;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.sunrise.treadmill.Constant;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.activity.home.HomeActivity;
import com.sunrise.treadmill.activity.summary.SummaryActivity;
import com.sunrise.treadmill.activity.workoutrunning.BaseRunningActivity;
import com.sunrise.treadmill.activity.workoutrunning.QuickStartRunningActivity;
import com.sunrise.treadmill.bean.WorkOut;
import com.sunrise.treadmill.interfaces.services.FloatWindowBottomCallBack;
import com.sunrise.treadmill.utils.DateUtil;
import com.sunrise.treadmill.views.workout.running.FloatWindowBottom;
import com.sunrise.treadmill.views.workout.running.FloatWindowHead;


/**
 * Created by ChuHui on 2017/10/14.
 */

public class FloatWindowService extends Service implements FloatWindowBottomCallBack {

    private final int FloatWindowNotification = 62111;
    private String runningActivityName = "";

    private static final int LEVEL_UP = 1;

    private static final int LEVEL_DOWN = -1;

    private BaseRunningActivity activity;

    private WindowManager mWindowManager;

    private WindowManager.LayoutParams paramsHead;
    private WindowManager.LayoutParams paramsBottom;

    private WindowManager.LayoutParams dialogParams;

    private FloatWindowHead floatWindowHead;
    private FloatWindowBottom floatWindowBottom;

    private ConstraintLayout dialogCountDown;
    private ConstraintLayout dialogPause;
    private ConstraintLayout dialogCoolDown;

    /**
     * 运行时间为一年
     */
    private final long timeCountDown = 365 * 24 * 60 * 60 * 1000;

    private final long timeSpace = 1000;

    private WorkOut workOutInfo;

    /**
     * 是否以倒计时形式显示时间
     */
    private boolean isCountDownTime = false;

    /**
     * 目标运动时间 以秒为单位
     */
    private long runningTime_target = 0;

    /**
     * 一共运行多长时间  以秒为单位
     */
    private long runningTime_total = 0;

    /**
     * 剩余运动时间  以秒为单位
     */
    private long runningTime_surplus = 0;

    private CountDownTimer sportTimer;


    @Override
    public void onCreate() {
        super.onCreate();
        mWindowManager = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);
        stageService();
        initWindowParams();
        initFloatWindow();
        initDialog();
        initCountDownTimer();
    }

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
        //可以通过这里获取bind方法中传递数据
        return floatBinder;
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
        onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        workOutInfo = intent.getParcelableExtra(Constant.WORK_OUT_INFO);
        if (workOutInfo.getWorkOutModeName().equals(Constant.WORK_OUT_MODE_MEDIA)) {
            floatWindowBottom.showHomeBtn();
            floatWindowBottom.showStartBtn();

            floatWindowBottom.hideBackBtn();
            floatWindowBottom.hideStopBtn();

            toggleFloatWindow();
        }
        //这里获取到的是分钟数
        runningTime_target = Integer.valueOf(workOutInfo.getTime());
        if (runningTime_target > 5) {
            isCountDownTime = true;
        } else {
            isCountDownTime = false;
        }
        //在这里转换成秒数
        runningTime_target = runningTime_target * 60;

        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
        activity = null;
        View[] views = {floatWindowHead, floatWindowBottom, dialogPause, dialogCoolDown};
        for (View v : views) {
            reMoveView(v);
        }
        views = null;
        mWindowManager = null;

        paramsHead = null;
        paramsBottom = null;

        dialogParams = null;

        floatWindowHead.recycle();
        floatWindowHead.removeAllViews();
        floatWindowHead = null;

        floatWindowBottom.recycle();
        floatWindowBottom.removeAllViews();
        floatWindowBottom = null;

        dialogPause.removeAllViews();
        dialogPause = null;

        dialogCoolDown.removeAllViews();
        dialogCoolDown = null;
    }

    @Override
    public void onLevelUp() {
        floatWindowHead.levelChange(LEVEL_UP);
    }

    @Override
    public void onLevelDown() {
        floatWindowHead.levelChange(LEVEL_DOWN);
    }

    @Override
    public void onWindyClick() {

    }

    @Override
    public void onStartClick() {
        sportTimer.start();
        floatWindowBottom.hideHomeBtn();
        floatWindowBottom.showBackBtn();

        floatWindowBottom.hideStartBtn();
        floatWindowBottom.showStopBtn();
    }

    @Override
    public void onStopClick() {
        sportTimer.cancel();
        mWindowManager.addView(dialogPause, paramsBottom);
    }


    @Override
    public void onHomeClick() {
        sportTimer.cancel();
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
        Intent serverIntent = new Intent(getApplicationContext(), FloatWindowService.class);
        toggleFloatWindow();
        stopService(serverIntent);
    }

    @Override
    public void onBackClick() {
        sportTimer.cancel();
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), QuickStartRunningActivity.class);
        intent.putExtra(Constant.SHOW_COUNT_DOWN, Constant.SHOW_COUNT_DOWN_FALSE);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
        Intent serverIntent = new Intent(getApplicationContext(), FloatWindowService.class);
        toggleFloatWindow();
        stopService(serverIntent);
    }

    private boolean isShowView = false;

    /**
     * 自动判断当前应该添加还是删除悬浮窗口
     */
    public void toggleFloatWindow() {
        if (floatWindowHead != null && floatWindowBottom != null) {
            if (!isShowView) {
                mWindowManager.addView(floatWindowHead, paramsHead);
                mWindowManager.addView(floatWindowBottom, paramsBottom);
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

    public void setActivity(BaseRunningActivity act) {
        this.activity = act;
    }

    public void onLevelChange(int value) {
        floatWindowHead.setLevelValue(value);
    }

    /**
     * 切为前台服务
     */
    private void stageService() {
        Notification.Builder builder = new Notification.Builder(getApplicationContext());
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
        paramsHead = setUpParams(0, -800);
        paramsBottom = setUpParams(0, 800);
        dialogParams = setUpParams(0, 0);
    }

    private WindowManager.LayoutParams setUpParams(int x, int y) {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        // 设置window type
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        // 设置图片格式，效果为背景透明
        params.format = PixelFormat.RGBA_8888;

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
        floatWindowHead = new FloatWindowHead(getApplicationContext(), null);
        floatWindowBottom = new FloatWindowBottom(getApplicationContext(), null);

        floatWindowHead.setLayoutParams(paramsHead);

        floatWindowBottom.setLayoutParams(paramsBottom);
        floatWindowBottom.setWindowBottomCallBack(FloatWindowService.this);
    }

    /**
     * 创建弹窗
     */
    private void initDialog() {
        DisplayMetrics dm = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        dm = null;
        dialogPause = (ConstraintLayout) LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_workout_running_pause, null);

        dialogCoolDown = (ConstraintLayout) LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_workout_running_cool_down, null);

        dialogCountDown = (ConstraintLayout) LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_workout_running_count_down, null);

        dialogPause.setLayoutParams(dialogParams);
        dialogPause.setMinHeight(height);
        dialogPause.setMinWidth(width);

        dialogCoolDown.setLayoutParams(dialogParams);
        dialogCoolDown.setMinHeight(height);
        dialogCoolDown.setMinWidth(width);

        dialogCountDown.setLayoutParams(dialogParams);
        dialogCountDown.setMinHeight(height);
        dialogCountDown.setMinWidth(width);

        dialogClick();
    }

    /**
     * 弹窗点击事件
     */
    private void dialogClick() {
        View.OnClickListener pauseClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    default:
                        break;
                    case R.id.workout_running_pause_quit:
                        mWindowManager.removeView(dialogPause);
                        mWindowManager.addView(dialogCoolDown,dialogParams);
                        //这里需要插入一个计时器 3分钟自动跳转到summary界面
                        sportTimer.cancel();
                        break;
                    case R.id.workout_running_pause_continue:
                        mWindowManager.removeView(dialogPause);
                        sportTimer.start();
                        break;
                    case R.id.workout_running_cool_down_skip:
                        //进入summary界面 同时关闭服务，关闭窗口
                        mWindowManager.removeView(dialogCoolDown);

                        Intent intent = new Intent();
                        intent.setClass(getApplicationContext(), SummaryActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra(Constant.WORK_OUT_INFO, workOutInfo);

                        startActivity(intent);

                        Intent serverIntent = new Intent(getApplicationContext(), FloatWindowService.class);
                        toggleFloatWindow();
                        stopService(serverIntent);

                        break;
                }
            }
        };

        dialogPause.findViewById(R.id.workout_running_pause_quit).setOnClickListener(pauseClick);
        dialogPause.findViewById(R.id.workout_running_pause_continue).setOnClickListener(pauseClick);

        dialogCoolDown.findViewById(R.id.workout_running_cool_down_skip).setOnClickListener(pauseClick);

    }

    private void initCountDownTimer() {
        sportTimer = new CountDownTimer(timeCountDown, timeSpace) {
            @Override
            public void onTick(long l) {
                runningTime_total++;
                if (isCountDownTime) {
                    runningTime_surplus = runningTime_target - runningTime_total;
                    floatWindowHead.setTimeValue(DateUtil.getFormatMMSS(runningTime_surplus));
                } else {
                    floatWindowHead.setTimeValue(DateUtil.getFormatMMSS(runningTime_total));
                }
            }

            @Override
            public void onFinish() {

            }
        };
    }


    private void reMoveView(View view) {
        try {
            mWindowManager.removeView(view);
        } catch (Exception e) {

        }
    }

}
