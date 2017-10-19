package com.sunrise.treadmill.activity.home;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.widget.ImageView;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragmentActivity;
import com.sunrise.treadmill.dialog.home.NfcDialog;

import butterknife.BindView;

/**
 * Created by ChuHui on 2017/9/6.
 */

public class NfcActivity extends BaseFragmentActivity {
    @BindView(R.id.nfc_img)
    ImageView nfc;

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private boolean stopChange = false;

    private static final int CLEAR_SHOW = -1;
    private static final int CHANGE_IMG_1 = 1001;
    private static final int CHANGE_IMG_2 = 1002;
    private static final int STOP_CHANGE = 1003;
    private static final int SHOW_ERROR = 1004;
    private NfcDialog nfcDialog;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                default:
                    break;
                case CLEAR_SHOW:
                    stopChange = true;
                    if (nfcDialog != null) {
                        nfcDialog.dismiss();
                    }
                    finishActivity();
                    break;
                case CHANGE_IMG_1:
                    if (!stopChange) {
                        nfc.setImageResource(R.mipmap.img_nfc_2);
                        mHandler.sendEmptyMessageDelayed(CHANGE_IMG_2, 500);
                    }
                    break;
                case CHANGE_IMG_2:
                    if (!stopChange) {
                        nfc.setImageResource(R.mipmap.img_nfc_1);
                        mHandler.sendEmptyMessageDelayed(CHANGE_IMG_1, 500);
                    }
                    break;
                case STOP_CHANGE:
                    stopChange = true;
                    break;
                case SHOW_ERROR:
                    stopChange = true;
                    nfcDialog = new NfcDialog();
                    nfcDialog.show(fragmentManager, NfcDialog.TAG);
                    break;
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_nfc;
    }

    @Override
    public void clearObj() {
        nfc=null;
        fragmentManager=null;
        mHandler=null;
        nfcDialog=null;
        setContentView(R.layout.view_null);
    }

    @Override
    protected void init() {
        mHandler.sendEmptyMessageDelayed(CHANGE_IMG_1, 1000);
        mHandler.sendEmptyMessageDelayed(STOP_CHANGE, 4000);
        mHandler.sendEmptyMessageDelayed(SHOW_ERROR, 7000);
        mHandler.sendEmptyMessageDelayed(CLEAR_SHOW, 10000);

    }
}
