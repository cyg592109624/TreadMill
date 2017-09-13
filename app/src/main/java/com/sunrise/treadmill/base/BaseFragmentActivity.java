package com.sunrise.treadmill.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.sunrise.treadmill.utils.AMUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ChuHui on 2017/9/9.
 */

public abstract class BaseFragmentActivity extends FragmentActivity {
    private Unbinder bind;
    private AMUtils amUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeSystemUiState();
        setContentView(getLayoutId());
        bind = ButterKnife.bind(this);
        amUtils = AMUtils.getInstance();
        amUtils.addActivity(this);
        setTextStyle();
    }

    public abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
    protected void setTextStyle() {

    }

    public void finishActivity() {
        amUtils.finishActivity();
    }

    /**
     * 隐藏部分系统内容
     */
    protected void changeSystemUiState() {
        //软件键盘插入方式为悬浮
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        //去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        //去掉虚拟按键全屏显示 部分无效
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        final View decorView = getWindow().getDecorView();

        //虚拟键隐藏
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int i) {
                int uiOptions = -1;
                //隐藏虚拟按键，并且全屏
                if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
                    uiOptions = View.GONE;
                } else if (Build.VERSION.SDK_INT >= 19) {
                    //for new api versions.
                    uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav  bar
                            | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                            | View.SYSTEM_UI_FLAG_IMMERSIVE;
                }
                decorView.setSystemUiVisibility(uiOptions);
            }
        });
    }
}
