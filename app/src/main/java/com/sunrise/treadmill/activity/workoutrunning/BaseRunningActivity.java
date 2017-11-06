package com.sunrise.treadmill.activity.workoutrunning;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.provider.Settings;
import android.support.constraint.ConstraintLayout;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunrise.treadmill.Constant;
import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.activity.summary.SummaryActivity;
import com.sunrise.treadmill.base.BaseFragmentActivity;
import com.sunrise.treadmill.bean.WorkOut;
import com.sunrise.treadmill.dialog.workoutrunning.CoolDownDialog;
import com.sunrise.treadmill.dialog.workoutrunning.CountDownDialog;
import com.sunrise.treadmill.dialog.workoutrunning.PauseDialog;
import com.sunrise.treadmill.interfaces.services.FloatServiceBinder;
import com.sunrise.treadmill.interfaces.services.FloatWindowBottomCallBack;
import com.sunrise.treadmill.interfaces.workout.running.DialogCoolDownClick;
import com.sunrise.treadmill.interfaces.workout.running.DialogPauseClick;
import com.sunrise.treadmill.services.workoutrunning.FloatWindowService;
import com.sunrise.treadmill.utils.AnimationsContainer;
import com.sunrise.treadmill.utils.DateUtil;
import com.sunrise.treadmill.utils.ImageUtils;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;
import com.sunrise.treadmill.utils.ThreadPoolUtils;
import com.sunrise.treadmill.views.workout.running.FloatWindowBottom;
import com.sunrise.treadmill.views.workout.running.FloatWindowHead;
import com.sunrise.treadmill.views.workout.LevelView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/30.
 */

public class BaseRunningActivity extends BaseFragmentActivity implements FloatServiceBinder, AnimationsContainer.OnAnimationStoppedListener,
        DialogPauseClick, DialogCoolDownClick, FloatWindowBottomCallBack {

    private int parentWidth;
    private int parentHeight;


    @BindView(R.id.include2)
    ConstraintLayout leftView;

    @BindView(R.id.include3)
    ConstraintLayout rightLayout;

    @BindView(R.id.workout_running_media_bg)
    ImageView rightLayoutBg;

    @BindView(R.id.workout_running_media_ctr)
    ImageView mediaCtrlImage;


    @BindView(R.id.workout_running_level_view)
    LevelView levelView;

    @BindView(R.id.workout_running_head)
    FloatWindowHead headView;

    @BindView(R.id.workout_running_bottom)
    FloatWindowBottom bottomView;

    /**
     * 当前Level 应该由计时器进行更新
     */
    private int tgLevel = 0;

    /**
     * 第一次进入该界面时 是否先进入倒数对话框 进入时倒数动作只进行一次
     */
    private int showCountDown = -1;

    public PackageManager packageManager;

    public FloatWindowService floatWindowServer;

    private FloatServiceBinder serviceBinder;


    /**
     * 最大显示时间
     */
    private final long MaxCountDownTimer = 100 * 60 * 1000;

    /**
     * 累计时间进行运算时 定时任务运行时间
     */
    private final long timeCountDown = 365 * 24 * 60 * 60 * 1000;

    /**
     * 运动时间 计时器
     */
    private CountDownTimer runningTimer;

    /**
     * true则为累减形式  false则为累加形式
     */
    private boolean isCountDownTime = false;

    /**
     * LevelView中每阶持续时间 根据目前情况决定 以秒为单位
     */
    private long avgLevelTime = 0;

    /**
     * 目标运动时间 以秒为单位
     */
    private long runningTime_target = 0;

    /**
     * 一共运行多长时间  以秒为单位
     */
    private long runningTime_total =1;

    /**
     * 剩余运动时间  以秒为单位
     */
    private long runningTime_surplus = 0;


    private ServiceConnection floatWindowConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            serviceBinder.onBindSucceed(componentName, iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            serviceBinder.onServiceDisconnected(componentName);
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_workout_running;
    }

    @Override
    protected void setTextStyle() {
        TextView levelHint = findViewById(R.id.workout_running_level_view_hint);
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(levelHint, TextUtils.Microsoft(activityContext));
        } else {
            TextUtils.setTextTypeFace(levelHint, TextUtils.Arial(activityContext));
        }
    }

    @Override
    public void recycleObject() {
        leftView = null;
        levelView = null;

        rightLayout = null;
        rightLayoutBg = null;
        mediaCtrlImage = null;

        headView = null;
        bottomView = null;

        packageManager = null;
        unbindService(floatWindowConnection);
        floatWindowConnection = null;
        serviceBinder = null;
        floatWindowServer = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bindServer();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        showCountDown = getIntent().getIntExtra(Constant.SHOW_COUNT_DOWN, Constant.SHOW_COUNT_DOWN_TRUE);
        workOutInfo = getIntent().getParcelableExtra(Constant.WORK_OUT_INFO);

        setUpInfo(workOutInfo);

        bottomView.setWindowBottomCallBack(BaseRunningActivity.this);

        packageManager = getApplicationContext().getPackageManager();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(dm);
        parentWidth = dm.widthPixels;
        parentHeight = dm.heightPixels;
        dm = null;

    }

    /**
     * 将workout信息写入界面 并重调整各个部位的实例对象
     *
     * @param workOut
     */
    protected void setUpInfo(WorkOut workOut) {
        if (workOut == null) {
            return;
        }

        //这里获取到的是目标运行分钟数
        runningTime_target = Integer.valueOf(workOutInfo.getTime());
        //这里获取已经运行的时间 以秒为单位
        runningTime_total = Integer.valueOf(workOutInfo.getRunningTime());

        if (runningTime_target > 5) {
            //累减形式 计算时间
            isCountDownTime = true;

            runningTime_surplus = runningTime_target * 60 - runningTime_total;
            headView.setTimeValue(DateUtil.getFormatMMSS(runningTime_surplus));

            avgLevelTime = (runningTime_target * 60) / LevelView.columnCount;
            tgLevel = (int) runningTime_total / (int) avgLevelTime;
            System.out.println("(runningTime_target * 60) / LevelView.columnCount");

        } else {
            //累加形式 计算时间
            isCountDownTime = false;

            avgLevelTime = 60;
            System.out.println("avgLevelTime");
            headView.setTimeValue(DateUtil.getFormatMMSS(runningTime_total));
            tgLevel = (int) runningTime_total / (int) avgLevelTime;
        }
        //在这里转换成秒数
        runningTime_target = runningTime_target * 60;
        //获取已经运行时间
        runningTime_total = Integer.valueOf(workOut.getRunningTime());
        reFlashLevelView();
        createRunningTimer();
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (showCountDown == Constant.SHOW_COUNT_DOWN_TRUE) {
            showCountDownDialog();
            showCountDown = Constant.SHOW_COUNT_DOWN_FALSE;
        }
    }


    @Override
    public void onBindSucceed(ComponentName componentName, IBinder iBinder) {
        if (iBinder != null) {
            floatWindowServer = ((FloatWindowService.FloatBinder) iBinder).getService();
            floatWindowServer.setRunningActivityName(getPackageName() + "." + getLocalClassName());
            floatWindowServer.setActivity(BaseRunningActivity.this);
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

    }

    @Override
    public void animationStopped() {
        if (runningTimer != null) {
            runningTimer.start();
        }
    }

    @Override
    public void onLevelUp() {
        if (headView.getLevel() >= Constant.MAX_LEVEL) {
            return;
        }
        headView.levelChange(1);
        reFlashLevelView();
    }

    @Override
    public void onLevelDown() {
        if (headView.getLevel() <= Constant.MIN_LEVEL) {
            return;
        }
        headView.levelChange(-1);
        reFlashLevelView();
    }

    @Override
    public void onWindyClick() {

    }

    @Override
    public void onStopClick() {
        runningTimer.cancel();
        ShowPauseDialog();
    }

    @Override
    public void onStartClick() {

    }

    @Override
    public void onHomeClick() {

    }

    @Override
    public void onBackClick() {

    }


    @Override
    public void onPauseQuit() {
        showCoolDownDialog();
    }

    @Override
    public void onPauseContinue() {
        showCountDownDialog();
    }

    @Override
    public void onPauseTimeOut() {
        goToSummary();
    }

    @Override
    public void onCoolDownSkip() {
        System.out.println("onCoolDownSkip ------------>");
        if (runningTimer != null) {
            runningTimer.cancel();
            runningTimer = null;
        }
        goToSummary();
    }


    private boolean isShowView = false;
    private boolean isAnimLeftView = false;
    private boolean isAnimRightView = false;

    @OnClick(R.id.include2)
    public void showLeftView() {
        if (isShowView) {
            if (isAnimLeftView) {
                isShowView = false;
                isAnimLeftView = false;
                animLeftView(false);
            }
        } else {
            if (!isAnimLeftView) {
                isShowView = true;
                isAnimLeftView = true;
                animLeftView(true);
            }
        }
    }

    @OnClick(R.id.workout_running_media_ctr)
    public void mediaPop() {
        if (isShowView) {
            if (isAnimRightView) {
                isShowView = false;
                isAnimRightView = false;
                animRightView(false);
            }
        } else {
            if (!isAnimLeftView) {
                isShowView = true;
                isAnimRightView = true;
                animRightView(true);
            }
        }
    }

    private void animLeftView(boolean isScale) {
        PropertyValuesHolder scaleX = null;
        PropertyValuesHolder scaleY = null;
        PropertyValuesHolder translationX = null;
        PropertyValuesHolder translationY = null;
        if (isScale) {
            scaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, 1.7f);
            scaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, 1.7f);
            translationX = PropertyValuesHolder.ofFloat("translationX", 0, (parentWidth - leftView.getWidth()) / 2);
            translationY = PropertyValuesHolder.ofFloat("translationY", 0, -(parentHeight - leftView.getHeight()) / 5);
        } else {
            scaleX = PropertyValuesHolder.ofFloat("scaleX", 1.7f, 1f);
            scaleY = PropertyValuesHolder.ofFloat("scaleY", 1.7f, 1f);
            translationX = PropertyValuesHolder.ofFloat("translationX", (parentWidth - leftView.getWidth()) / 2, 0);
            translationY = PropertyValuesHolder.ofFloat("translationY", -(parentHeight - leftView.getHeight()) / 5, 0);
        }
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(leftView, scaleX, scaleY, translationX, translationY);
        animator.setDuration(500);
        animator.start();

    }

    private void animRightView(final boolean translation) {
        PropertyValuesHolder translationX = null;
        if (translation) {
            translationX = PropertyValuesHolder.ofFloat("translationX", rightLayout.getWidth() - mediaCtrlImage.getWidth(), 0);
        } else {
            translationX = PropertyValuesHolder.ofFloat("translationX", 0, rightLayout.getWidth() - mediaCtrlImage.getWidth());
        }
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(rightLayout, translationX);
        animator.setDuration(500);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                if (translation) {
                    rightLayoutBg.setVisibility(View.VISIBLE);
                    ImageUtils.changeImageView(mediaCtrlImage, R.drawable.bg_dialog_3);
                }
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (!translation) {
                    rightLayoutBg.setVisibility(View.INVISIBLE);
                    ImageUtils.changeImageView(mediaCtrlImage, R.mipmap.btn_sportmode_media_1);
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator.start();
    }

    private void bindServer() {
        serviceBinder = BaseRunningActivity.this;
        ThreadPoolUtils.runTaskOnThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(activityContext, FloatWindowService.class);
                bindService(intent, floatWindowConnection, Context.BIND_AUTO_CREATE);
            }
        });
    }

    /**
     * 更新LevelView
     */
    private void reFlashLevelView() {
        //将浮标移动到指定段位位
        levelView.setTgLevel(tgLevel);
        //指定段位的段数
        levelView.setColumn(tgLevel, headView.getLevel());
        //刷新(重新绘制onDraw())
        levelView.reFlashView();
    }

    /**
     * 倒计时对话框
     */
    private void showCountDownDialog() {
        ThreadPoolUtils.runTaskOnThread(new Runnable() {
            @Override
            public void run() {
                CountDownDialog dialog = new CountDownDialog();
                dialog.show(fragmentManager, Constant.TAG);
            }
        });
    }

    /**
     * 暂停对话框
     */
    private void ShowPauseDialog() {
        ThreadPoolUtils.runTaskOnThread(new Runnable() {
            @Override
            public void run() {
                PauseDialog pauseDialog = new PauseDialog();
                pauseDialog.show(fragmentManager, Constant.TAG);
            }
        });
    }

    /**
     * 冷却对话框
     */
    private void showCoolDownDialog() {
        ThreadPoolUtils.runTaskOnThread(new Runnable() {
            @Override
            public void run() {
                CoolDownDialog dialog = new CoolDownDialog();
                dialog.show(fragmentManager, Constant.TAG);
            }
        });
    }

    /**
     * 跳转到summary界面
     * 必然带数据跳转
     */
    private void goToSummary() {
        Intent intent = new Intent(BaseRunningActivity.this, SummaryActivity.class);
        finishActivity();
        startActivity(intent);
    }

    /**
     * 计时任务 需要用线程执行
     */
    private void createRunningTimer() {
        if (runningTimer != null) {
            runningTimer.cancel();
            runningTimer = null;
        }
        if (isCountDownTime) {
            runningTimer = new CountDownTimer(runningTime_surplus * 1000, 1000) {
                @Override
                public void onTick(long l) {
                    timerTick();
                }

                @Override
                public void onFinish() {
                    //倒计时结束时的动作
                    ThreadPoolUtils.runTaskOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            showCoolDownDialog();
                        }
                    });
                }
            };
        } else {
            runningTimer = new CountDownTimer(timeCountDown, 1000) {
                @Override
                public void onTick(long l) {
                    timerTick();
                }

                @Override
                public void onFinish() {
                    //超出倒计时范围时
                }
            };
        }
    }

    private synchronized void timerTick() {
        ++runningTime_total;
        if (isCountDownTime) {
            runningTime_surplus = runningTime_target - runningTime_total;
            headView.setTimeValue(DateUtil.getFormatMMSS(runningTime_surplus));
        } else {
            headView.setTimeValue(DateUtil.getFormatMMSS(runningTime_total));
        }
        if (runningTime_total % avgLevelTime == 0) {
            //切换到下一阶段的Level
            ++tgLevel;
            if (tgLevel % LevelView.columnCount == 0) {
                tgLevel = 0;
            }
            reFlashLevelView();
        }
    }
}
