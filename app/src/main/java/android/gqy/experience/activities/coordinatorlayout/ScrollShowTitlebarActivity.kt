package android.gqy.experience.activities.coordinatorlayout

import android.gqy.experience.R
import android.gqy.experience.activities.BaseActivity
import android.gqy.experience.utils.ToastUtil
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.NestedScrollView

/**
 * created by gqy on  2021/2/6
 * @desc Material Design 滑动显示或隐藏标题栏
 */
class ScrollShowTitlebarActivity : BaseActivity() {
    private val TAG = "ScrollShowTitlebar"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_showtitlebar)

        val toolBar = findViewById<View>(R.id.toolbar)
        val scrollView = findViewById<NestedScrollView>(R.id.scrollView)
        scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            Log.e(TAG, "scrollY:$scrollY,toolBarHeight:${toolBar.height}")
            if (scrollY > toolBar.height + 50) {
                toolBar.alpha = 1.0f
            } else {
                toolBar.alpha = scrollY * 1.0f / (toolBar.height + 50)
            }
        })
        findViewById<View>(R.id.ivCollect).setOnClickListener {
            ToastUtil.show(this, "start")
        }
    }

}