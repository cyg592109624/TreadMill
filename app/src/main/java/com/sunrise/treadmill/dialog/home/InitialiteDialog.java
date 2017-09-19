package com.sunrise.treadmill.dialog.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ChuHui on 2017/9/12.
 */

public class InitialiteDialog extends BaseDialogFragment {
    public static final String Home_Initialite_Dialog="InitialiteDialog";

    private static final int clearAnim=6000;
    View inflaterView;
    @BindView(R.id.home_dialog_inititalite_img)
    ImageView img;

    private Handler mHandler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case clearAnim:
                    img.clearAnimation();
                    dismiss();
                    break;
                default:
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        inflaterView = inflater.inflate(R.layout.dialog_home_initialite, container);
        ButterKnife.bind(this, inflaterView);
        img.startAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.home_dialog_initialite));
        mHandler.sendEmptyMessageDelayed(clearAnim,5000);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public View inflateView() {
        return inflaterView;
    }


}
