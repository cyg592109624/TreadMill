package com.sunrise.treadmill.dialog.workoutrunning;

import android.content.DialogInterface;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.activity.workoutrunning.BaseRunningActivity;
import com.sunrise.treadmill.base.BaseDialogFragment;
import com.sunrise.treadmill.interfaces.workout.running.DialogCoolDownClick;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/10/17.
 */

public class CoolDownDialog extends BaseDialogFragment {
    private DialogCoolDownClick dialogClick;
    private int delayTime = 60000 * 3;
    private ScheduledExecutorService pool;
    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            dialogClick.onCoolDownSkip();
            dismiss();
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.dialog_workout_running_cool_down;
    }

    @Override
    public void recycleObject() {
        dialogClick = null;
        pool = null;
        task = null;

    }

    @Override
    protected void init() {
        dialogClick = (BaseRunningActivity) getActivity();
        pool = Executors.newScheduledThreadPool(1);
        pool.schedule(task, delayTime, TimeUnit.MILLISECONDS);
    }

    @OnClick({R.id.workout_running_cool_down_skip})
    public void onSkip() {
        pool.shutdownNow();
        dialogClick.onCoolDownSkip();
        dismiss();
    }
}
