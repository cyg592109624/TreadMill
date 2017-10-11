package com.sunrise.treadmill.interfaces;

/**
 * Created by ChuHui on 2017/10/11.
 */

public interface FloatWindowBottomCallBack {
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
}
