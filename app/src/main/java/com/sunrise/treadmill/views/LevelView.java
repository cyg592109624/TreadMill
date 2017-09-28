package com.sunrise.treadmill.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.bean.LevelColumn;

/**
 * Created by ChuHui on 2017/9/22.
 */

public class LevelView extends View {
    private Paint mPaint;
    private int viewHeight;
    private int viewWidth;

    private float toX;
    private float toY;

    /**
     * 柱子的高度
     */
    private float maxTall = 0;


    /**
     * 柱子的上边距
     */
    private float topSpace = 0;

    /**
     * 柱子的下边距
     */
    private float bottomSpace = 0;

    /**
     * 柱子的左边距
     */
    private float leftSpace = 0;
    /**
     * 柱子的右边距
     */
    private float rightSpace = 0;

    /**
     * 柱子的宽度
     */
    private int columnWidth = 0;
    /**
     * 柱子的间隔
     */
    private int columnMargin = 0;

    /**
     * 柱子低部位置同时也是柱子的起点
     */
    private float columnStartArea = 0;

    /**
     * 每一段的高度 一共36段
     */
    private float levelHeight = 0;

    private int levelCount = 36;

    private int columnCount = 30;

    private LevelColumn[] rectList;

    private boolean slideEnable = true;

    private Bitmap buoyBitmap;

    private int tgLevel = 0;
    private float buoyLeft;

    public LevelView(Context context) {
        this(context, null);
    }

    public LevelView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LevelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setAntiAlias(true);
        mPaint.setFilterBitmap(true);
        mPaint.setColor(getResources().getColor(R.color.factory_tabs_on));

        rectList = new LevelColumn[columnCount];

        topSpace = 115f;
        bottomSpace = 60f;
        leftSpace = 159f;
        rightSpace = 30f;
        columnMargin = 6;
        columnWidth = 41;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = MeasureSpec.getSize(widthMeasureSpec);
        viewHeight = MeasureSpec.getSize(heightMeasureSpec);
        calcLength();
    }

    public void calcLength() {
        columnStartArea = viewHeight - bottomSpace;
        maxTall = columnStartArea - topSpace;
        levelHeight = maxTall / levelCount;
    }

    public void setSpace(float top, float bottom, float left, float right) {
        setTopSpace(top);
        setBottomSpace(bottom);
        setLeftSpace(left);
        setRightSpace(right);
    }

    public void setTopSpace(float space) {
        this.topSpace = space;
    }

    public void setBottomSpace(float space) {
        this.bottomSpace = space;
    }

    public void setLeftSpace(float space) {
        this.leftSpace = space;
    }

    public void setRightSpace(float space) {
        this.rightSpace = space;
    }

    public void setColumnMargin(int columnMargin) {
        this.columnMargin = columnMargin;
    }

    public void setColumnWidth(int columnWidth) {
        this.columnWidth = columnWidth;
    }

    private float buoyBitmapWidth, buoyBitmapHeight;

    public void setBuoyBitmap(int optionsWidth, int optionsHeight) {
        if (optionsWidth < 0 | optionsHeight < 0) {
            return;
        }
        buoyBitmapWidth = optionsWidth / 3;
        buoyBitmapHeight = optionsHeight / 3;

        buoyBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.img_sportmode_profile_dot_1).copy(Bitmap.Config.ARGB_8888, false);
        //最后一个参数的作用是 是否裁剪原图
        buoyBitmap = Bitmap.createScaledBitmap(buoyBitmap, optionsWidth, optionsHeight, false);
    }

    public void setTgLevel(int tgLevel) {
        if (tgLevel > -1 && tgLevel < columnCount) {
            this.tgLevel = tgLevel;
        }
        calcBuoyLeft();
    }

    private void calcBuoyLeft() {
        buoyLeft = leftSpace + (columnMargin + columnWidth) * tgLevel;
    }

    public void reFlashView() {
        invalidate();
    }


    public void setRectList(LevelColumn[] columns) {
        if (columns.length != columnCount) {
            return;
        }
        this.rectList = columns;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!slideEnable) {
            return true;
        }
        final float x = event.getX();
        final float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                toX = x;
                toY = y;
                calcPoint();
                break;
            case MotionEvent.ACTION_MOVE:
                toX = x;
                toY = y;
                calcPoint();
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }

    public void setSlideEnable(boolean slideEnable) {
        this.slideEnable = slideEnable;
    }

    private void calcPoint() {
        float left = 0, right = 0, top = 0, bottom = 0;
        top = toY;
        bottom = columnStartArea;
        if (top < topSpace) {
            top = topSpace;
        }
        if (top > bottom) {
            top = bottom;
        }
        int tg = -1;
        for (int i = 0; i < columnCount; i++) {
            if (toX > (leftSpace + columnWidth * i + columnMargin * i) && toX < (leftSpace + columnWidth * (i + 1) + columnMargin * i)) {
                left = leftSpace + columnWidth * i + columnMargin * i;
                right = leftSpace + columnWidth * (i + 1) + columnMargin * i;
                tg = i;
                break;
            }
        }
        if (tg != -1) {
            LevelColumn cell = rectList[tg];
            if (cell == null) {
                cell = new LevelColumn();
                rectList[tg] = cell;
            }
            for (int i = 0; i < levelCount; i++) {
                if (toY > (columnStartArea - levelHeight * (i + 1)) && toY < (columnStartArea - levelHeight * i)) {
                    top = topSpace + levelHeight * (levelCount - i);
                    cell.setLevel((levelCount - i));
                    break;
                }
            }
            cell.setXY(left, top, right, bottom);
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (LevelColumn cell : rectList) {
            if (cell != null) {
                canvas.drawRect(cell.getToX1(), cell.getToY1(), cell.getToX2(), cell.getToY2(), mPaint);
            }
        }
        if (buoyBitmap != null) {
            canvas.drawBitmap(buoyBitmap, buoyLeft + buoyBitmapWidth, topSpace - buoyBitmapHeight, null);
        }
    }
}
