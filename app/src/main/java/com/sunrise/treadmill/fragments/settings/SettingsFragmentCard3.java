package com.sunrise.treadmill.fragments.settings;

import android.content.Intent;
import android.provider.Settings;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragment;
import com.sunrise.treadmill.services.settings.BackPressServer;

/**
 * Created by ChuHui on 2017/9/14.
 */

public class SettingsFragmentCard3 extends BaseFragment {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_settings_card_3;
    }

    @Override
    public void recycleObject() {
    }

    @Override
    protected void init() {
        openWiFi();
    }

    private void openWiFi() {
        try {
            Intent serverIntent=new Intent(getContext(), BackPressServer.class);
            getActivity().startService(serverIntent);
            Thread.sleep(200);
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_WIFI_SETTINGS);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
