package android.gqy.experience.activities.touchevent.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.LinearLayout

/**
 * created by gqy on 2021/02/22
 *
 * @desc 父容器
 * @since 1.0.4
 */
class ParentContainerView : LinearLayout {
    var TAG = "TouchEvent"

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        return super.dispatchTouchEvent(ev)
    }

    /**
     *   1，OnTouchListener.onTouch返回false， parent onInterceptTouchEvent 返回true, onTouchEvent的ACTION_DOWN返回true，
     *    后续move,up事件会一直传递给parent， child不会接收到任何事件，Activity的onTouchEvent也不会执行；
     *   2，OnTouchListener.onTouch返回false， parent onInterceptTouchEvent 返回true, onTouchEvent的ACTION_DOWN返回false，
     *    后续move,up事件都不会传递给parent， child不会接收到任何事件，事件会返回给Activity的onTouchEvent；
     *   3，OnTouchListener.onTouch返回false， parent onInterceptTouchEvent 返回false, onTouchEvent的ACTION_DOWN返回false，
     *    后续move,up事件都不会传递给parent， child不会接收到任何事件，事件会返回给Activity的onTouchEvent；
     */
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        Log.e(TAG, "ParentContainerView-onInterceptTouchEvent")
        return false
        //        return super.onInterceptTouchEvent(ev);
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.e(TAG, "ParentContainerView-onTouchEvent ACTION_DOWN")
                return false
            }
            MotionEvent.ACTION_MOVE -> {
                Log.e(TAG, "ParentContainerView-onTouchEvent  ACTION_MOVE")
            }
            MotionEvent.ACTION_CANCEL -> {
                Log.e(TAG, "ParentContainerView-onTouchEvent  ACTION_CANCEL")
            }
            MotionEvent.ACTION_UP -> {
                Log.e(TAG, "ParentContainerView-onTouchEvent  ACTION_UP")
            }
        }
        return true
        //        return super.onTouchEvent(event);
    }
}