package com.sunrise.treadmill.dialog.workoutrunning;

import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseDialogFragment;
import com.sunrise.treadmill.utils.ImageUtils;

import butterknife.BindView;

/**
 * Created by ChuHui on 2017/10/9.
 */

public class CountDownDialog extends BaseDialogFragment {
    public static final String TAG = "CountDownDialog";
    private int countDownSpace = 900;

    private static final int countDown_1 = 6001;
    private static final int countDown_2 = 6002;
    private static final int countDown_3 = 6003;
    private static final int countDown_4 = 6004;

    @BindView(R.id.workout_running_dialog_count_down_img)
    ImageView img;

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case countDown_1:
                    ImageUtils.changeImageView(img,R.mipmap.img_sportmode_countdown_2);
                    mHandler.sendEmptyMessageDelayed(countDown_2,countDownSpace);
                    break;
                case countDown_2:
                    ImageUtils.changeImageView(img,R.mipmap.img_sportmode_countdown_3);
                    mHandler.sendEmptyMessageDelayed(countDown_3,countDownSpace);
                    break;
                case countDown_3:
                    ImageUtils.changeImageView(img,R.mipmap.img_sportmode_countdown_4);
                    mHandler.sendEmptyMessageDelayed(countDown_4,countDownSpace);
                    break;
                case countDown_4:
                    dismiss();
                    break;
            }
        }
    };
    @Override
    public int getLayoutId() {
        return R.layout.dialog_count_down;
    }

    @Override
    protected void init() {
        mHandler.sendEmptyMessageDelayed(countDown_1,countDownSpace);
    }
}
