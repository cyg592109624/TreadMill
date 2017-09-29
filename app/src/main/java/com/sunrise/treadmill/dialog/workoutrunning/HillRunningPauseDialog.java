package com.sunrise.treadmill.dialog.workoutrunning;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.activity.workoutrunning.HillRunningActivity;
import com.sunrise.treadmill.base.BaseDialogFragment;

import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/29.
 */

public class HillRunningPauseDialog extends BaseDialogFragment {
    public static final String TAG = "HillRunningPauseDialog";

    @Override
    public int getLayoutId() {
        return R.layout.dialog_workout_running_pause;
    }

    @OnClick(R.id.workout_running_pause_quit)
    public void onQuit() {
        dismiss();
        ((HillRunningActivity) getActivity()).finishActivity();
    }
}
