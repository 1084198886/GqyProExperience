package android.gqy.experience.activities.touchevent.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatTextView

/**
 * created by gqy on 2021/02/22
 *
 * @desc 子View容器
 * @since 1.0.4
 */
class ChildTextView : AppCompatTextView {
    var TAG = "TouchEvent"

    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!,
        attrs
    ) {
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        Log.e(TAG, "ChildTextView-dispatchTouchEvent")
//        parent.requestDisallowInterceptTouchEvent(true)
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.e(TAG, "ChildTextView-onTouchEvent getX=${event.x},getY=${event.y}")

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.e(TAG, "ChildTextView-onTouchEvent ACTION_DOWN")
                return false
            }
            MotionEvent.ACTION_MOVE -> {
                Log.e(TAG, "ChildTextView-onTouchEvent  ACTION_MOVE")
            }
            MotionEvent.ACTION_CANCEL -> {
                Log.e(TAG, "ChildTextView-onTouchEvent  ACTION_CANCEL")
            }
            MotionEvent.ACTION_UP -> {
                Log.e(TAG, "ChildTextView-onTouchEvent  ACTION_UP")
            }
        }
        return false
//        val ret = super.onTouchEvent(event);
//        Log.e(TAG, "ChildTextView-onTouchEvent  ret:$ret")
//        return ret
    }
}