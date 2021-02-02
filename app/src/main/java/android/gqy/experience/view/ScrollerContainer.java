package android.gqy.experience.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * created by gqy on 2021/01/29
 *
 * @desc  实现可以拉动后回弹的布局
 * 总体思路:
 * 处理View的Touch事件,即重写onTouchEvent()方法:
 * 当手指抬起时将其回到原点,其余情况交给GestureDetector处理.
 *
 * 在GestureDetector中重点是覆写onScroll()方法.在该方法中得到
 * Y方向滑动的距离,从而设置 mScroller.startScroll()方法,准备滑动.
 * 随之刷新界面invalidate()从而执行方法computeScroll().
 * 在computeScroll()方法中调用 scrollTo()方法实现真正的滑动.
 *
 * @since 1.0.1
 */
public class ScrollerContainer extends LinearLayout {
    private final Scroller scroller = new Scroller(getContext());
    private final GestureDetector mGestureDetector = new GestureDetector(getContext(), new GestureListenerImpl());
    private String TAG = "ScrollerContainer";

    public ScrollerContainer(Context context) {
        this(context, null);
    }

    public ScrollerContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        setClickable(true);
        setLongClickable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                //手指抬起时回到最初位置
                prepareScroll(0, 0);
                break;
            default:
                //其余情况交给GestureDetector手势处理
                return mGestureDetector.onTouchEvent(event);
        }
        return super.onTouchEvent(event);
    }

    class GestureListenerImpl implements GestureDetector.OnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        //控制拉动幅度:
        //int disY=(int)((distanceY - 0.5)/2);
        //亦可直接调用:
        //smoothScrollBy(0, (int)distanceY);
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.e(TAG, "distanceY=" + distanceY);
            int disY = (int) ((distanceY - 0.5) / 2);
            beginScroll(0, disY);
            return false;
        }

        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }

    }


    //滚动到目标位置 
    protected void prepareScroll(int fx, int fy) {
        int dx = fx - scroller.getFinalX();
        int dy = fy - scroller.getFinalY();
        beginScroll(dx, dy);
    }


    //设置滚动的相对偏移 
    protected void beginScroll(int dx, int dy) {
        Log.e(TAG, "dx=" + dx + ",dy=" + dy);
        //第一,二个参数起始位置; 第三,四个滚动的偏移量
        scroller.startScroll(scroller.getFinalX(), scroller.getFinalY(), dx, dy,700);
        Log.e(TAG, "scroller.getFinalX()=" + scroller.getFinalX() + "," + "scroller.getFinalY()=" + scroller.getFinalY());
        //必须执行invalidate()从而调用computeScroll()
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            Log.e(TAG, "scroller.getCurrX()=" + scroller.getCurrX() + "," + "scroller.getCurrY()=" + scroller.getCurrY());
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            //必须调用invalidate()，从而调用computeScroll()
            invalidate();
        }
        super.computeScroll();
    }
}
