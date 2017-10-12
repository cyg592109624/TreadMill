package com.sunrise.treadmill.interfaces;

import android.content.ComponentName;
import android.os.IBinder;

/**
 * Created by ChuHui on 2017/10/11.
 */

public interface FloatServiceBinder {
    /**
     * 启动并且绑定服务完成
     */
    void onBindSucceed(ComponentName componentName, IBinder iBinder);
    void onServiceDisconnected(ComponentName componentName);
}
