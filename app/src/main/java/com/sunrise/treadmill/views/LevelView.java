package com.sunrise.treadmill.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.sunrise.treadmill.R;

/**
 * Created by ChuHui on 2017/9/22.
 */

public class LevelView extends View {
    private Paint mPaint;
    private int bgViewHeight;
    private int bgViewWidth;


    /**
     * 柱子顶部空位高度
     */
    private float paddingTop = 0;
    /**
     * 柱子低部空位高度
     */
    private float paddingBottom = 0;

    /**
     * 柱子的左右边距
     */
    private int space = 11;
    /**
     * 柱子的间距
     */
    private int margin = 4;
    /**
     * 柱子的宽度
     */
    private float rectCellWidth = 0;
    /**
     * 柱子的高度
     */
    private float maxTall = 0;
    /**
     * 每一段的高度
     */
    private float avgCell = 0;


    private RectCell[] rectList = new RectCell[30];

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

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        bgViewWidth = MeasureSpec.getSize(widthMeasureSpec);
        bgViewHeight = MeasureSpec.getSize(heightMeasureSpec);

        paddingBottom = bgViewHeight - (bgViewHeight / 10 - 4);

        paddingTop = bgViewHeight / 5 - 10;

        maxTall = paddingBottom - paddingTop;

        avgCell = maxTall / 36;

        rectCellWidth = (bgViewWidth - 2 * space - 29 * margin) / 30;
    }

    private float toX;
    private float toY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
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

    private void calcPoint() {
        if ((toX < space) | (toY < paddingTop - avgCell) | (toY > paddingBottom)) {
            return;
        }
        float marginFrom = 0, marginTo = 0;
        int tg = -1;
        if (toY < paddingTop) {
            toY = paddingTop;
        } else if (toY > paddingBottom) {
            toY = paddingBottom;
        }
        for (int i = 0; i < 30; i++) {
            if (toX > (space + rectCellWidth * i + margin * i) && toX < (space + rectCellWidth * (i + 1) + margin * i)) {
                marginFrom = space + rectCellWidth * i + margin * i;
                marginTo = space + rectCellWidth * (i + 1) + margin * i;
                tg = i;
                break;
            }
        }
        if (tg != -1) {
            RectCell cell = rectList[tg];
            if (cell == null) {
                cell = new RectCell();
                rectList[tg] = cell;
            }
            float height = 0;
            if (toY == paddingTop) {
                height = toY;
                cell.setLevel(36);
            } else if (toY == paddingBottom) {
                height = paddingBottom;
                cell.setLevel(0);
            } else {
                for (int i = 0; i < 36; i++) {
                    if (toY > (paddingBottom - avgCell * (i + 1)) && toY < (paddingBottom - avgCell * i)) {
                        height = paddingTop + avgCell * (35 - i);
                        cell.setLevel((36 - i));
                        break;
                    }
                }
            }
            cell.setXY(marginFrom, height, marginTo, paddingBottom);
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (RectCell cell : rectList) {
            if (cell != null) {
                canvas.drawRect(cell.getToX1(), cell.getToY1(), cell.getToX2(), cell.getToY2(), mPaint);
            }
        }
        invalidate();
    }

    private class RectCell {
        private float toX1, toX2, toY1, toY2;
        private int level;

        public RectCell() {

        }

        public void setXY(float x1, float y1, float x2, float y2) {
            toX1 = x1;
            toY1 = y1;
            toX2 = x2;
            toY2 = y2;
        }

        public float getToX1() {
            return toX1;
        }

        public void setToX1(float toX1) {
            this.toX1 = toX1;
        }

        public float getToX2() {
            return toX2;
        }

        public void setToX2(float toX2) {
            this.toX2 = toX2;
        }

        public float getToY1() {
            return toY1;
        }

        public void setToY1(float toY1) {
            this.toY1 = toY1;
        }

        public float getToY2() {
            return toY2;
        }

        public void setToY2(float toY2) {
            this.toY2 = toY2;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }
    }
}
