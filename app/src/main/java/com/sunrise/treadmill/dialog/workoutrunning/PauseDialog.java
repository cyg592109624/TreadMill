package com.sunrise.treadmill.dialog.workoutrunning;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.activity.workoutrunning.BaseRunningActivity;
import com.sunrise.treadmill.activity.workoutrunning.HillRunningActivity;
import com.sunrise.treadmill.base.BaseDialogFragment;
import com.sunrise.treadmill.interfaces.workoutrunning.PauseDialogClick;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/29.
 */

public class PauseDialog extends BaseDialogFragment {
    public static final String TAG = "PauseDialog";
    private PauseDialogClick pauseDialogClick;
    private ScheduledExecutorService pool;
    private int delayTime=60000*3;
    private TimerTask task=new TimerTask() {
        @Override
        public void run() {
            pauseDialogClick.onPauseTimeOut();
        }
    };

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getContext(), R.style.Dialog_No_BG);
        return dialog;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_workout_running_pause;
    }

    @Override
    protected void init() {
        pauseDialogClick=(BaseRunningActivity)getActivity();
        pool = Executors.newScheduledThreadPool(1);
        pool.schedule(task, delayTime, TimeUnit.MILLISECONDS);
    }

    @OnClick(R.id.workout_running_pause_quit)
    public void onQuit() {
        pool.shutdownNow();
        dismiss();
        pauseDialogClick.onPauseQuit();
    }
    @OnClick(R.id.workout_running_pause_continue)
    public void onContinue() {
        pool.shutdownNow();
        dismiss();
        pauseDialogClick.onPauseContinue();
    }
}
