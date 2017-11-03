package com.sunrise.treadmill.fragments.home;

import android.view.View;

import com.sunrise.treadmill.Constant;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragment;
import com.sunrise.treadmill.interfaces.home.OnModeSelectReturn;

import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/12.
 */

public class HomeFragmentPage2 extends BaseFragment {
    private OnModeSelectReturn selectReturn;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_page_2;
    }

    @Override
    protected void init() {
        selectReturn = (OnModeSelectReturn) getActivity();
    }

    @Override
    public void recycleObject() {
    }
    public void setSelectReturn(OnModeSelectReturn selectReturn){
        this.selectReturn=selectReturn;
    }


    @OnClick({R.id.home_app_mode_youtube, R.id.home_app_mode_chrome, R.id.home_app_mode_facebook,
            R.id.home_app_mode_flikr, R.id.home_app_mode_instagram, R.id.workout_mode_quick_start})
    public void onMediaClick(View view) {
        switch (view.getId()) {
            default:
                break;
            case R.id.home_app_mode_youtube:
                selectReturn.onMediaStart(Constant.MODE_MEDIA_YOUTUBE);
                break;
            case R.id.home_app_mode_chrome:
                selectReturn.onMediaStart(Constant.MODE_MEDIA_CHROME);
                break;
            case R.id.home_app_mode_facebook:
                selectReturn.onMediaStart(Constant.MODE_MEDIA_FACEBOOK);
                break;
            case R.id.home_app_mode_flikr:
                selectReturn.onMediaStart(Constant.MODE_MEDIA_FLIKR);
                break;
            case R.id.home_app_mode_instagram:
                selectReturn.onMediaStart(Constant.MODE_MEDIA_INSTAGRAM);
                break;
            case R.id.workout_mode_quick_start:
                selectReturn.onWorkOutSetting(Constant.MODE_QUICK_START);
                break;
        }
    }
}
