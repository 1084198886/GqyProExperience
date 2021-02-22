package android.gqy.experience.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * created by gqy on 2021/01/27
 *
 * @desc 自定义会旋转的钟表指针
 * @since 1.0.1
 */
public class CirclePgBar extends View {
    private Paint mBackPaint;
    private Paint mFrontPaint;
    private Paint mTextPaint;
    private final float mStrokeWidth = 10; // mWidth-直径  描边属性
    private float mHalfStrokeWidth = mStrokeWidth / 2;
    private final float mRadius = 200;
    private RectF mRect;
    private int mProgress = 0;
    //目标值，想改多少就改多少
    private int mTargetProgress = 90;
    private int mMax = 100;
    private int mWidth;
    private int mHeight;
    private static final String TAG = "CirclePgBar";


    public CirclePgBar(Context context) {
        super(context);
        init();
    }

    public CirclePgBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mBackPaint = new Paint();
        mBackPaint.setColor(Color.RED);
        mBackPaint.setAntiAlias(true);
        mBackPaint.setStyle(Paint.Style.STROKE); //  描边属性
        mBackPaint.setStrokeWidth(mStrokeWidth);

        mFrontPaint = new Paint();
        mFrontPaint.setColor(Color.GREEN);
        mFrontPaint.setAntiAlias(true);
        mFrontPaint.setStyle(Paint.Style.STROKE);
        mFrontPaint.setStrokeWidth(mStrokeWidth);


        mTextPaint = new Paint();
        mTextPaint.setColor(Color.GREEN);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(80);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }


    //重写测量大小的onMeasure方法和绘制View的核心方法onDraw()
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getRealSize(widthMeasureSpec);
        mHeight = getRealSize(heightMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);
    }

    /**
     * 只绘制一次背景圆。
     */
    private boolean hasDrawBackCircle = false;

    @Override
    protected void onDraw(Canvas canvas) {
        initRect();
        // 弧形角度；
        float angle = mProgress / (float) mMax * 360;
        if (!hasDrawBackCircle) {
            hasDrawBackCircle = true;
            canvas.drawCircle(mWidth / 2, mHeight / 2, mRadius, mBackPaint);
        }

        // 绘制圆弧 useCenter==true 画半径
        canvas.drawArc(mRect, -90, angle, true, mFrontPaint);
        canvas.drawText(mProgress + "%", mWidth / 2 + mHalfStrokeWidth, mHeight / 2 + mHalfStrokeWidth, mTextPaint);
        if (mProgress < mTargetProgress) {
            mProgress += 1;
            // 触发onDraw;也可以子线程posInvalidate()
            invalidate();
        }

    }

    public int getRealSize(int measureSpec) {
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if (mode == MeasureSpec.AT_MOST || mode == MeasureSpec.UNSPECIFIED) {
            //自己计算
            return (int) (mRadius * 2 + mStrokeWidth);
        } else {
            return size;
        }
    }

    private void initRect() {
        if (mRect == null) {
            mRect = new RectF();
            int viewSize = (int) (mRadius * 2);
            int left = (int) ((mWidth - viewSize) / 2);
            int top = (int) ((mHeight - viewSize) / 2);
            int right = left + viewSize;
            int bottom = top + viewSize;
//            Log.e(TAG, "viewSize=" + viewSize + ",left=" + left + ",top=" + top +
//                    ",right=" + right + ",bottom=" + bottom);
            mRect.set(left, top, right, bottom);
        }
    }
}
