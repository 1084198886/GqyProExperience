package android.gqy.experience.activities.scroller

import android.gqy.experience.R
import android.gqy.experience.activities.BaseActivity
import android.os.Bundle

/**
 * created by gqy on 2021/01/28
 * @since 1.0.1
 * @desc Scroller的用法，实现下拉回弹的效果；
 */
class ScrollerUsageActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroller)
        initView()
    }

    private fun initView() {
    }
}