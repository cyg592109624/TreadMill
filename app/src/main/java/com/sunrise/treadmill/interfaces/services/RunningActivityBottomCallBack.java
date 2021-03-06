package com.sunrise.treadmill.interfaces.services;

/**
 * Created by ChuHui on 2017/10/11.
 */

public interface RunningActivityBottomCallBack {
    /**
     * 段数提高
     */
    void onLevelUp();

    /**
     * 段数降低
     */
    void onLevelDown();

    /**
     * 改变风量
     */
    void onWindyClick();

    /**
     * 按下停止按钮
     */
    void onStopClick();
}
