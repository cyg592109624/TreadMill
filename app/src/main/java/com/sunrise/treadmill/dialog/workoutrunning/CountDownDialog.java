package com.sunrise.treadmill.dialog.workoutrunning;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseDialogFragment;
import com.sunrise.treadmill.utils.BitMapUtils;
import com.sunrise.treadmill.utils.ImageUtils;

import butterknife.BindView;

/**
 * Created by ChuHui on 2017/10/9.
 */

public class CountDownDialog extends BaseDialogFragment {
    public static final String TAG = "CountDownDialog";

    @BindView(R.id.workout_running_dialog_count_down_img)
    ImageView img;

    @Override
    public int getLayoutId() {
        return R.layout.dialog_count_down;
    }

    @Override
    protected void init() {
//        reSetBitmap();
        img.setImageResource(R.drawable.img_sportmode_countdown_1);
    }

    private void reSetBitmap() {
        try {
//            Glide.with(getContext()).load(R.drawable.img_sportmode_countdown_1).into(img);
//            Thread.sleep(1000);
//            Glide.with(this).load(R.drawable.img_sportmode_countdown_2).into(img);
//            Thread.sleep(1000);
//            Glide.with(this).load(R.drawable.img_sportmode_countdown_3).into(img);
//            Thread.sleep(1000);
//            Glide.with(this).load(R.drawable.img_sportmode_countdown_4).into(img);
//            Thread.sleep(1000);
            dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
