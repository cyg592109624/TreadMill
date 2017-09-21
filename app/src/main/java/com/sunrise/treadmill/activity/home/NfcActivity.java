package com.sunrise.treadmill.activity.home;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseActivity;
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

    private static final int ClearShow = -1;
    private static final int ChangeImg_1 = 1001;
    private static final int ChangeImg_2 = 1002;
    private static final int StopChange = 1003;
    private static final int ShowError = 1004;
    private NfcDialog nfcDialog;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ClearShow:
                    stopChange = true;
                    if (nfcDialog != null) {
                        nfcDialog.dismiss();
                    }
                    finishActivity();
                    break;
                case ChangeImg_1:
                    if (!stopChange) {
                        nfc.setImageResource(R.mipmap.img_nfc_2);
                        mHandler.sendEmptyMessageDelayed(ChangeImg_2, 500);
                    }
                    break;
                case ChangeImg_2:
                    if (!stopChange) {
                        nfc.setImageResource(R.mipmap.img_nfc_1);
                        mHandler.sendEmptyMessageDelayed(ChangeImg_1, 500);
                    }
                    break;
                case StopChange:
                    stopChange = true;
                    break;
                case ShowError:
                    stopChange = true;
                    nfcDialog = new NfcDialog();
                    nfcDialog.show(fragmentManager, NfcDialog.Home_Nfc_Dialog);
                    break;
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_nfc;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    protected void init() {
        mHandler.sendEmptyMessageDelayed(ChangeImg_1, 1000);
//        mHandler.sendEmptyMessageDelayed(StopChange, 4000);
        mHandler.sendEmptyMessageDelayed(ShowError, 7000);
        mHandler.sendEmptyMessageDelayed(ClearShow, 10000);

    }
}
