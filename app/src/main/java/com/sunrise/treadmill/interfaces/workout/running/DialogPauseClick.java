package com.sunrise.treadmill.interfaces.workout.running;

/**
 * Created by ChuHui on 2017/10/17.
 */

public interface DialogPauseClick {
    /**
     * 离开workout running
     */
    void onPauseQuit();

    /**
     * 继续运动
     */
    void onPauseContinue();

    /**
     * 暂停时间到达极限
     */
    void onPauseTimeOut();
}
