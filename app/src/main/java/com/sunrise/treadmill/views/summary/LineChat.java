package com.sunrise.treadmill.views.summary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.sunrise.treadmill.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ChuHui on 2017/10/18.
 */

public class LineChat extends View {
    private float mX;
    private float mY;

    private int viewWidth;
    private int viewHeight;
    private float avgWidth;
    private float avgHeight;

    private final Paint mGesturePaint = new Paint();
    private final Path mPath = new Path();
    private List<Integer> data;
    private int dataSize = 31;
    private int rankCount = 36;

    public LineChat(Context context) {
        this(context, null);
    }

    public LineChat(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineChat(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mGesturePaint.setAntiAlias(true);
        mGesturePaint.setStyle(Paint.Style.STROKE);
        mGesturePaint.setStrokeWidth(5);
        mGesturePaint.setColor(ContextCompat.getColor(context, R.color.factory_tabs_on));
        data = createData(dataSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = MeasureSpec.getSize(widthMeasureSpec);
        viewHeight = MeasureSpec.getSize(heightMeasureSpec);
        avgWidth = (viewWidth * 1.00f / (dataSize - 1));
        avgHeight = (viewHeight * 1.00f / rankCount);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawData();
        canvas.drawPath(mPath, mGesturePaint);
    }


    private List<Integer> createData(int size) {
        Random random = new Random();
        int max = 36;
        int min = 10;
        List<Integer> array = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            array.add(random.nextInt(max) % (max - min + 1) + min);
        }
        return array;
    }

    private void drawData() {
        mX = 0;
        mY = viewHeight - data.get(0) * avgHeight;
        mPath.moveTo(mX, mY);
        float x = 1.00f;
        float y = 1.00f;
        float previousX = 1.00f;
        float previousY = 1.00f;
        for (int i = 1; i < dataSize; i++) {

            x = avgWidth * i;
            y = viewHeight - data.get(i) * avgHeight;
            previousX = mX;
            previousY = mY;

            float cX = (x + previousX) / 2;
            float cY = (y + previousY) / 2;

            //二次贝塞尔，实现平滑曲线；previousX, previousY为操作点，cX, cY为终点
            mPath.quadTo(previousX, previousY, cX, cY);

            //第二次执行时，第一次结束调用的坐标值将作为第二次调用的初始坐标值
            mX = x;
            mY = y;
        }
    }
}
