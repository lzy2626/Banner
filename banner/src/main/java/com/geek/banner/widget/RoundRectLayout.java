package com.geek.banner.widget;

/**
 * @auther :liuzhanyue
 * @date :2019/8/21 0021 17
 * @des :圆角的RelativeLayout
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.RelativeLayout;

import com.geek.banner.R;

public class RoundRectLayout extends RelativeLayout {

    private Path mPath;
    private int mRadius;

    private int mWidth;
    private int mHeight;
    private int mLastRadius;

    public static final int MODE_NONE = 0;
    public static final int MODE_ALL = 1;
    public static final int MODE_LEFT = 2;
    public static final int MODE_TOP = 3;
    public static final int MODE_RIGHT = 4;
    public static final int MODE_BOTTOM = 5;

    private int mRoundMode = MODE_ALL;

    public RoundRectLayout(Context context) {
        super(context);

        init();
    }

    public RoundRectLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public RoundRectLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setBackgroundDrawable(new ColorDrawable(00000000));
        mPath = new Path();
        mPath.setFillType(Path.FillType.EVEN_ODD);
    }

    /**
     * 设置是否圆角裁边
     *
     * @param roundMode
     */
    public void setRoundMode(int roundMode) {
        mRoundMode = roundMode;
    }

    /**
     * 设置圆角半径,单位dp。修改整个控件的圆角，并不是图片的圆角（图片的圆角设置请在自定义的item中实现）
     *
     * @param radius
     */
    public void setCornerRadius(float radius) {
        mRadius = dp2px(getContext(), radius);
    }

    private void checkPathChanged() {

        if (getWidth() == mWidth && getHeight() == mHeight && mLastRadius == mRadius) {
            return;
        }

        mWidth = getWidth();
        mHeight = getHeight();
        mLastRadius = mRadius;

        mPath.reset();

        switch (mRoundMode) {
            case MODE_ALL:
                mPath.addRoundRect(new RectF(0, 0, mWidth, mHeight), mRadius, mRadius, Path.Direction.CW);
                break;
            case MODE_LEFT:
                mPath.addRoundRect(new RectF(0, 0, mWidth, mHeight),
                        new float[]{mRadius, mRadius, 0, 0, 0, 0, mRadius, mRadius},
                        Path.Direction.CW);
                break;
            case MODE_TOP:
                mPath.addRoundRect(new RectF(0, 0, mWidth, mHeight),
                        new float[]{mRadius, mRadius, mRadius, mRadius, 0, 0, 0, 0},
                        Path.Direction.CW);
                break;
            case MODE_RIGHT:
                mPath.addRoundRect(new RectF(0, 0, mWidth, mHeight),
                        new float[]{0, 0, mRadius, mRadius, mRadius, mRadius, 0, 0},
                        Path.Direction.CW);
                break;
            case MODE_BOTTOM:
                mPath.addRoundRect(new RectF(0, 0, mWidth, mHeight),
                        new float[]{0, 0, 0, 0, mRadius, mRadius, mRadius, mRadius},
                        Path.Direction.CW);
                break;
        }

    }

    @Override
    public void draw(Canvas canvas) {

        if (mRoundMode != MODE_NONE) {
            int saveCount = canvas.save();

            checkPathChanged();

            canvas.clipPath(mPath);
            super.draw(canvas);

            canvas.restoreToCount(saveCount);
        } else {
            super.draw(canvas);
        }
    }

    /**
     * dp转px
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }
}
