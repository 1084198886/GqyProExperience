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
        Log.e(TAG, "ParentContainerView-dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    /**
     *   1，OnTouchListener.onTouch返回false， parent onInterceptTouchEvent=true, onTouchEvent ACTION_DOWN=true，
     *    后续move,up事件会一直传递给parent， child不会接收到任何事件，Activity的onTouchEvent也不会执行；
     *
     *   2，OnTouchListener.onTouch返回false， parent onInterceptTouchEvent 返回true, onTouchEvent的ACTION_DOWN返回false，由于parent没有处理ACTION_DOWN事件，
     *    事件会返回给Activity的onTouchEvent，后续move,up事件都不会传递给parent，child也不会接收到任何事件，只会一直传递给Activity.onTouchEvent。
     *
     *   3，OnTouchListener.onTouch返回false， parent onInterceptTouchEvent 返回false,事件会传递给child的onInterceptTouchEvent,
     *   child如果拦截了ACTION_DOWN事件，后续事件则都交给child onTouchEvent处理; child如果未拦截ACTION_DOWN事件,但其onTouchEvent针对ACTION_DOWN事件返回true，
     *   后续的事件也会直接交给child处理；
     *   如果child未拦截，且child.onTouchEvent返回false，则交由parent onTouchEvent处理，如果parent.onTouchEvent处理ACTION_DOWN返回true，则后续事件都由parent处理；
     *   否则parent交给其上层的parent(最终到达Activity.onTouchEvent)处理.
     *
     *   4,如果parent.onInterceptTouchEvent没有拦截ACTION_DOWN事件，而被child拦截，后续的move,up事件仍然会经过parent的 dispatchTouchEvent，onInterceptTouchEvent
     *
     *   5,OnTouchListener.onTouch返回false，parent.onInterceptTouchEvent  ACTION_DOWN返回false，move,up返回true，child的onTouchEvent 针对ACTION_DOWN返回true，
     *   默认返回false。触摸事件开始时，ACTION_DOWN事件由child处理，紧跟的第一个ACTION_MOVE事件被parent拦截时，parent.onTouchEvent并不会执行，
     *   而是触发child.onTouchEvent方法，产生ACITON_CANCEL事件，后续的move，up事件会直接传递给parent。
     */
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.e(TAG, "ParentContainerView-onInterceptTouchEvent ACTION_DOWN")
                return false
            }
            MotionEvent.ACTION_MOVE -> {
                Log.e(TAG, "ParentContainerView-onInterceptTouchEvent ACTION_MOVE")
                return true
            }
            MotionEvent.ACTION_CANCEL -> {
                Log.e(TAG, "ParentContainerView-onInterceptTouchEvent ACTION_CANCEL")
            }
            MotionEvent.ACTION_UP -> {
                Log.e(TAG, "ParentContainerView-onInterceptTouchEvent ACTION_UP")
            }
        }
        return false
        //        return super.onInterceptTouchEvent(ev);
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.e(TAG, "ParentContainerView-onTouchEvent ACTION_DOWN")
//                return false
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
        return false
        //        return super.onTouchEvent(event);
    }
}