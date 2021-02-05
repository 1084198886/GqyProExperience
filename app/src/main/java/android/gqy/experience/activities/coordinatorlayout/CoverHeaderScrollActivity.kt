package android.gqy.experience.activities.coordinatorlayout

import android.gqy.experience.R
import android.gqy.experience.activities.BaseActivity
import android.os.Bundle

/**
 * created by gqy on 2021/2/3
 * @desc 自定义Behavior  实现RecyclerView(或者其他可滑动的NestedScrollView) 滑动覆盖header的效果
 */
class CoverHeaderScrollActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coverheaderscroll)
        initView()
    }

    private fun initView() {
    }
}