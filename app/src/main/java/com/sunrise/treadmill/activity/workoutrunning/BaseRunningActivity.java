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
import com.sunrise.treadmill.dialog.workoutrunning.CoolDownDialog;
import com.sunrise.treadmill.dialog.workoutrunning.CountDownDialog;
import com.sunrise.treadmill.dialog.workoutrunning.PauseDialog;
import com.sunrise.treadmill.interfaces.services.FloatServiceBinder;
import com.sunrise.treadmill.interfaces.services.FloatWindowBottomCallBack;
import com.sunrise.treadmill.interfaces.workout.running.DialogCoolDownClick;
import com.sunrise.treadmill.interfaces.workout.running.DialogPauseClick;
import com.sunrise.treadmill.services.workoutrunning.FloatWindowService;
import com.sunrise.treadmill.utils.AnimationsContainer;
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

    private int parentWidth;
    private int parentHeight;

    public static int showCountDown = 1;

    public PackageManager packageManager;

    public FloatWindowService floatWindowServer;

    private FloatServiceBinder serviceBinder;

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
        serviceBinder = BaseRunningActivity.this;
        bottomView.setWindowBottomCallBack(BaseRunningActivity.this);

        packageManager = getApplicationContext().getPackageManager();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(dm);
        parentWidth = dm.widthPixels;
        parentHeight = dm.heightPixels;
        dm = null;

    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (showCountDown == 1) {
            showCountDownDialog();
            showCountDown = 2;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        floatWindowServer.toggleFloatWindow();
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

    }

    @Override
    public void onLevelUp() {
        headView.levelChange(1);
    }

    @Override
    public void onLevelDown() {
        headView.levelChange(-1);
    }

    @Override
    public void onWindyClick() {
        floatWindowServer.toggleFloatWindow();
    }

    @Override
    public void onStopClick() {
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
    public void onCoolDownSkip() {
        goToSummary();
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
        ThreadPoolUtils.runTaskOnThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(activityContext, FloatWindowService.class);
                bindService(intent, floatWindowConnection, Context.BIND_AUTO_CREATE);
            }
        });
    }

    private void showCountDownDialog() {
        ThreadPoolUtils.runTaskOnThread(new Runnable() {
            @Override
            public void run() {
                CountDownDialog dialog = new CountDownDialog();
                dialog.show(fragmentManager, CountDownDialog.TAG);
            }
        });
    }

    private void ShowPauseDialog() {
        ThreadPoolUtils.runTaskOnThread(new Runnable() {
            @Override
            public void run() {
                PauseDialog pauseDialog = new PauseDialog();
                pauseDialog.show(fragmentManager, PauseDialog.TAG);
            }
        });
    }

    private void showCoolDownDialog() {
        ThreadPoolUtils.runTaskOnThread(new Runnable() {
            @Override
            public void run() {
                CoolDownDialog dialog = new CoolDownDialog();
                dialog.show(fragmentManager, CoolDownDialog.TAG);
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

    public void onLevelChange(int value) {
        headView.setLevelValue(value);
    }
}
