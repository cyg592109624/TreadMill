package com.sunrise.treadmill.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by ChuHui on 2017/9/12.
 */

public class LogoImageView extends android.support.v7.widget.AppCompatImageView {
    private int mLastMotionX, mLastMotionY;
    /**
     * 是否移动了
     */
    private boolean isMoved;

    /**
     * 是否释放了
     */
    private boolean isReleased;

    /**
     * 计数器，防止多次点击导致最后一次形成longpress的时间变短
     */
    private int mCounter;
    /**
     * 长按的runnable
     */
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mCounter--;
            //计数器大于0，说明当前执行的Runnable不是最后一次down产生的。
            if (mCounter > 0 || isMoved || isReleased) {
                return;
            }
            performLongClick();
        }
    };

    /**
     * 移动的阈值
     */
    private  final int TOUCH_SLOP = 100;


    public LogoImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LogoImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isLongClickable() {
        return super.isLongClickable();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastMotionX = x;
                mLastMotionY = y;
                mCounter++;
                isReleased = false;
                isMoved = false;
                postDelayed(mRunnable, 100);
                break;
            case MotionEvent.ACTION_MOVE:
                if (isMoved) {

                    break;
                }
                if (Math.abs(mLastMotionX - x) > TOUCH_SLOP
                        || Math.abs(mLastMotionY - y) > TOUCH_SLOP) {
                    //移动超过阈值，则表示移动了
                    isMoved = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                //释放了
                isReleased = true;
                break;
            default:
                break;
        }
        return true;
    }
}
