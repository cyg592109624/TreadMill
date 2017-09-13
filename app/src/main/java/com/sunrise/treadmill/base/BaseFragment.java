package com.sunrise.treadmill.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ChuHui on 2017/9/9.
 */

public abstract class BaseFragment extends Fragment {
    //是否可见
    protected boolean isVisible;
    // 标志位，标志Fragment已经初始化完a成。
    public boolean isPrepared = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setTextStyle();
        return getLayoutView();
    }

    /**
     * 实现Fragment数据的缓加载 * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInVisible();
        }
    }

    public abstract View getLayoutView();

    protected void onInVisible() {

    }

    protected void setTextStyle() {

    }

    protected void onVisible() {
        //加载数据
        loadData();
    }

    protected abstract void loadData();

}
