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
import com.sunrise.treadmill.bean.Level;
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
     * 第一次进入该界面时 是否先进入倒数对话框 进入时倒数动作只进行一次
     */
    private int showCountDown = -1;

    public PackageManager packageManager;

    public FloatWindowService floatWindowServer;

    private FloatServiceBinder serviceBinder;


    /**
     * 当前Level 同时也是浮标位置 应该由计时器进行更新
     */
    private int tgLevel = 0;

    /**
     * 计时器运行次数 当累加时间的时候可以算出 经历了多少次Level(就是说可以突然30这个数值)
     */
    private int timerMissionTimes = 0;

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
    private long runningTimeTarget = 0;

    /**
     * 一共运行多长时间  以秒为单位
     */
    private long runningTimeTotal = 0;

    /**
     * 剩余运动时间  以秒为单位
     */
    private long runningTimeSurplus = 0;


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
        if (floatWindowConnection != null) {
            unbindService(floatWindowConnection);
        }
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
        runningTimeTarget = Integer.valueOf(workOut.getTime());
        //这里获取已经运行的时间 以秒为单位
        runningTimeTotal = Integer.valueOf(workOut.getRunningTime());


        if (runningTimeTarget > 5) {
            //累减形式 计算时间
            isCountDownTime = true;
            //在这里转换成秒数
            runningTimeTarget = runningTimeTarget * 60;

            runningTimeSurplus = runningTimeTarget - runningTimeTotal;
            headView.setTimeValue(DateUtil.getFormatMMSS(runningTimeSurplus));
            avgLevelTime = runningTimeTarget / LevelView.columnCount;
            tgLevel = (int) runningTimeTotal / (int) avgLevelTime;
            //获取段数信息
            headView.setLevelValue(workOutInfo.getLevelList().get(tgLevel).getLevel());


        } else {
            //累加形式 计算时间
            isCountDownTime = false;
            headView.setTimeValue(DateUtil.getFormatMMSS(runningTimeTotal));
            avgLevelTime = 60;
            tgLevel = (int) runningTimeTotal / (int) avgLevelTime;
            //获取段数信息
            //因为时累加时间 (workOutInfo.getLevelList()的数量可能突破30；这里需要另类的计算
            //暂时不做处理
            headView.setLevelValue(workOutInfo.getLevelList().get(tgLevel).getLevel());

        }
        createRunningTimer();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (showCountDown == Constant.SHOW_COUNT_DOWN_TRUE) {
            levelView.calcLength();
            levelView.setLevelList(workOutInfo.getLevelList());
            moveBuoy();
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
        upDataLevelValue();
    }

    @Override
    public void onLevelDown() {
        if (headView.getLevel() <= Constant.MIN_LEVEL) {
            return;
        }
        headView.levelChange(-1);
        upDataLevelValue();
    }

    @Override
    public void onWindyClick() {

    }

    @Override
    public void onStopClick() {
        runningTimer.cancel();
        showPauseDialog();
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
     * 更新Level
     */
    private void upDataLevelValue() {
        //将当前的Level值写入LevelList
        workOutInfo.getLevelList().get(tgLevel).setLevel(headView.getLevel());
        //更新Level值
        levelView.setColumn(tgLevel, headView.getLevel());
        //刷新(重新绘制onDraw())
        levelView.reFlashView();
    }

    /**
     * 移动浮标位置
     */
    private void moveBuoy() {
        levelView.setTgLevel(tgLevel);
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
    private void showPauseDialog() {
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
        ThreadPoolUtils.runTaskOnUIThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(BaseRunningActivity.this, SummaryActivity.class);
                finishActivity();
                startActivity(intent);
            }
        });
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
            runningTimer = new CountDownTimer(runningTimeSurplus * 1000, 1000) {
                @Override
                public void onTick(long l) {
                    timerTick();
                }

                @Override
                public void onFinish() {
                    runningTimeTotal++;
                    //倒计时结束时的动作
                    headView.setTimeValue(DateUtil.getFormatMMSS(0));
                    showCoolDownDialog();
                    System.out.println("runningTimeTotal     --------》" + runningTimeTotal);
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
        runningTimeTotal++;
        if (isCountDownTime) {
            runningTimeSurplus = runningTimeTarget - runningTimeTotal;
            headView.setTimeValue(DateUtil.getFormatMMSS(runningTimeSurplus));
        } else {
            headView.setTimeValue(DateUtil.getFormatMMSS(runningTimeTotal));
        }
        //切换到下一阶段的Level
        if (runningTimeTotal % avgLevelTime == 0) {
            timerMissionTimes = timerMissionTimes + 1;
            tgLevel = tgLevel + 1;
            headView.setLevelValue(workOutInfo.getLevelList().get(timerMissionTimes).getLevel());
            if (timerMissionTimes % LevelView.columnCount == 0) {
                tgLevel = 0;
                for (int i = 0; i < LevelView.columnCount; i++) {
                    Level level = new Level();
                    level.setLevel(headView.getLevel());
                    workOutInfo.getLevelList().add(level);
                    levelView.setLevelList(workOutInfo.getLevelList());
                }
            }
            //将浮标移动到指定段位位
            moveBuoy();
        }
    }
}
