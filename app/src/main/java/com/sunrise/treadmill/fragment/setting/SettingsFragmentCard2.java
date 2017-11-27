package com.sunrise.treadmill.fragment.setting;

import android.content.Intent;
import android.provider.Settings;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragment;

/**
 * Created by ChuHui on 2017/9/14.
 */

public class SettingsFragmentCard2 extends BaseFragment {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_settings_card_2;
    }

    @Override
    public void recycleObject() {
    }

    @Override
    protected void init() {
        openBle();
    }

    private void openBle() {
        try {
            Thread.sleep(200);
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_BLUETOOTH_SETTINGS);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
