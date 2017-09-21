package com.sunrise.treadmill.dialog.home;

import android.os.Handler;
import android.os.Message;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseDialogFragment;

/**
 * Created by ChuHui on 2017/9/21.
 */

public class NfcDialog extends BaseDialogFragment {
    public static final String TAG = "NfcDialog";
    private static final int CloseDialog = 1000;
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CloseDialog:
                    dismiss();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.dialog_home_nfc;
    }

    @Override
    public void init() {
        mHandler.sendEmptyMessageDelayed(CloseDialog, 2000);
    }
}
