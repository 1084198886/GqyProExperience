package android.gqy.experience.activities.touchevent.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.LinearLayout

/**
 * created by gqy on 2021/02/22
 *
 * @desc 子View容器
 * @since 1.0.4
 */
class ChildContainerView : LinearLayout {
    var TAG = "TouchEvent"

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        Log.e(TAG, "ChildContainerView-dispatchTouchEvent")
//        parent.requestDisallowInterceptTouchEvent(true)
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.e(TAG, "ChildContainerView-onInterceptTouchEvent ACTION_DOWN")
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                Log.e(TAG, "ChildContainerView-onInterceptTouchEvent ACTION_MOVE")
                return false
            }
            MotionEvent.ACTION_CANCEL -> {
                Log.e(TAG, "ChildContainerView-onInterceptTouchEvent ACTION_CANCEL")
            }
            MotionEvent.ACTION_UP -> {
                Log.e(TAG, "ChildContainerView-onInterceptTouchEvent ACTION_UP")
            }
        }
        return false
        //        return super.onInterceptTouchEvent(ev);
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.e(TAG, "ChildContainerView-onTouchEvent ACTION_DOWN")
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                Log.e(TAG, "ChildContainerView-onTouchEvent  ACTION_MOVE")
            }
            MotionEvent.ACTION_CANCEL -> {
                Log.e(TAG, "ChildContainerView-onTouchEvent  ACTION_CANCEL")
            }
            MotionEvent.ACTION_UP -> {
                Log.e(TAG, "ChildContainerView-onTouchEvent  ACTION_UP")
            }
        }
        return false
        //        return super.onTouchEvent(event);
    }
}