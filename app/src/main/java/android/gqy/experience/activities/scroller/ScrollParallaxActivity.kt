package android.gqy.experience.activities.scroller

import android.gqy.experience.R
import android.gqy.experience.activities.BaseActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.core.widget.NestedScrollView

/**
 * created by gqy on 2021/02/22
 * @since 1.0.1
 * @desc 滚动视差效果
 */
class ScrollParallaxActivity : BaseActivity() {
    val TAG = "ScrollParallax"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_parallax)
        initView()
    }

    private fun initView() {
        val scrollView = findViewById<NestedScrollView>(R.id.scrollView)
        val headerView = findViewById<LinearLayout>(R.id.headerView)
        scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            Log.e(TAG, "scrollY=$scrollY")
            val f = (scrollY * 0.65f).toInt()
            headerView.scrollTo(0, -f)
        })
    }
}