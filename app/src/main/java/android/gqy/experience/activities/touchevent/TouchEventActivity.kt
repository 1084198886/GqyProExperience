package android.gqy.experience.activities.touchevent

import android.annotation.SuppressLint
import android.gqy.experience.R
import android.gqy.experience.activities.BaseActivity
import android.gqy.experience.activities.touchevent.view.ChildTextView
import android.gqy.experience.activities.touchevent.view.ParentContainerView
import android.gqy.experience.utils.ToastUtil
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View

/**
 * @author created by gqy on 2021/02/22
 * @desc Android触摸事件
 */
class TouchEventActivity : BaseActivity() {
    val TAG = "TouchEventActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_touch_event)
        initView()
    }

    /**
     * setOnTouchListener.onTouch  返回false时，onTouch先执行，setOnClickListener后执行；
     *                              返回true时，onTouch执行，setOnClickListener不执行；
     */
    @SuppressLint("ClickableViewAccessibility")
    private fun initView() {
        val llParent = findViewById<ParentContainerView>(R.id.ll_parent)
        llParent.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        toast("OnTouchListener.onTouch ACTION_DOWN")
                    }
                    MotionEvent.ACTION_MOVE -> {
                        log("OnTouchListener.onTouch ACTION_MOVE")
                    }
                    MotionEvent.ACTION_UP -> {
                        toast("OnTouchListener.onTouch ACTION_UP")
                    }
                }
                return false
            }

        })
        llParent.setOnClickListener {
            toast("parent点击了")
        }

        val tvChild = findViewById<ChildTextView>(R.id.tv_child)
        tvChild.setOnClickListener {
            toast("child点击了")
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        log("TouchEventActivity-onTouchEvent")
        return super.onTouchEvent(event)
    }

    fun toast(msg: String) {
        ToastUtil.show(this, msg)
    }

    fun log(msg: String) {
        Log.e(TAG, msg)
    }
}
