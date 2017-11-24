package com.sunrise.treadmill.dialog.setting;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.activity.setting.SettingLockActivity;
import com.sunrise.treadmill.base.BaseDialogFragment;
import com.sunrise.treadmill.views.NumberKeyBoardView;

import butterknife.BindView;

/**
 * Created by ChuHui on 2017/9/16.
 */

public class SettingsLockPswDialog extends BaseDialogFragment {

    @BindView(R.id.dialog_settings_lock_key_board)
    NumberKeyBoardView keyBoardView;

    @Override
    public int getLayoutId() {
        return R.layout.dialog_settings_lock_customer_pass_word;
    }

    @Override
    public void recycleObject() {
        keyBoardView.recycle();
        keyBoardView = null;
    }

    @Override
    protected void init() {
        keyBoardView.setKeyBoardReturn((SettingLockActivity) getActivity());
    }
}
