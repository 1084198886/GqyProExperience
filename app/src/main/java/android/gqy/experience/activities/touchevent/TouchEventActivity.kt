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
import kotlinx.android.synthetic.main.activity_touch_event.view.*

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

        val metrics = resources.displayMetrics
        Log.e(
            TAG,
            "hardware density[${metrics.density}],width:${metrics.widthPixels},height:${metrics.heightPixels}"
        )
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
                        log("parent OnTouchListener.onTouch ACTION_DOWN")
                    }
                    MotionEvent.ACTION_MOVE -> {
                        log("parent OnTouchListener.onTouch ACTION_MOVE")
                    }
                    MotionEvent.ACTION_UP -> {
                        log("parent OnTouchListener.onTouch ACTION_UP")
                    }
                }
                return false
            }

        })
        llParent.setOnClickListener {
            toast("parent点击了")
        }

        val tvChild = findViewById<ChildTextView>(R.id.tv_child)
        tvChild.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        log("child OnTouchListener.onTouch ACTION_DOWN")
                    }
                    MotionEvent.ACTION_MOVE -> {
                        log("child OnTouchListener.onTouch ACTION_MOVE")
                    }
                    MotionEvent.ACTION_UP -> {
                        log("child OnTouchListener.onTouch ACTION_UP")
                    }
                }
                return false
            }

        })
        tvChild.setOnLongClickListener {
            toast("child长按了")
            // 返回false时，可同时执行click点击事件
            false
        }
        tvChild.setOnClickListener {
            toast("child点击了")
        }

        findViewById<View>(R.id.btn_offsetLeftAndRight).setOnClickListener {
            tvChild.offsetLeftAndRight(20)
        }
        findViewById<View>(R.id.offsetTopAndBottom).setOnClickListener {
            tvChild.offsetTopAndBottom(20)
        }
        findViewById<View>(R.id.scrollContent).setOnClickListener {
            llParent.scrollTo(10, 0)
        }
        findViewById<View>(R.id.setTranslationX).setOnClickListener {
            tvChild.translationX = 20f
        }
        findViewById<View>(R.id.setTranslationY).setOnClickListener {
            tvChild.translationY = 20f
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
