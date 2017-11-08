package com.sunrise.treadmill.dialog.workoutrunning;

import android.app.Dialog;
import android.os.Bundle;

import com.sunrise.treadmill.Constant;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.activity.workoutrunning.BaseRunningActivity;
import com.sunrise.treadmill.base.BaseDialogFragment;
import com.sunrise.treadmill.interfaces.workout.running.DialogCoolDownClick;
import com.sunrise.treadmill.interfaces.workout.running.DialogWarmUpClick;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/10/17.
 */

public class WarmUpDialog extends BaseDialogFragment {
    private DialogWarmUpClick dialogClick;

    private ScheduledExecutorService pool;
    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            dialogClick.onWarmUpSkip();
            dismiss();
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.dialog_workout_running_cool_down;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getContext(), R.style.Dialog_No_BG);
        return dialog;
    }

    @Override
    public void recycleObject() {
        dialogClick = null;
        pool = null;
        task = null;

    }

    @Override
    protected void init() {
//        dialogClick = (BaseRunningActivity) getActivity();
        pool = Executors.newScheduledThreadPool(1);
        pool.schedule(task, Constant.DIALOG_WAIT_TIME, TimeUnit.MILLISECONDS);
    }

    @OnClick({R.id.workout_running_cool_down_skip})
    public void onSkip() {
        pool.shutdownNow();
        dialogClick.onWarmUpSkip();
        dismiss();
    }
}
