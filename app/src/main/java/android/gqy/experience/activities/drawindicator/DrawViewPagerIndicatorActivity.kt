package android.gqy.experience.activities.drawindicator

import android.gqy.experience.R
import android.gqy.experience.activities.BaseActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager

/**
 * created by gqy on 2021/01/28
 * @since 1.0.1
 * @desc Canvas绘制Indicator指示器
 */
class DrawViewPagerIndicatorActivity : BaseActivity() {
    private val TAG = "DrawViewPagerIndicator"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw_indicator)
        initView()
    }

    private fun initView() {
        val vpIndicator = findViewById<SimpleViewPagerIndicator>(R.id.vpIndicator)
        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val adapter = ViewPagerFragmentPageAdapter(
            supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        val fragments = ArrayList<Fragment>()
        fragments.add(IndicatorFragment.newInstance("", ""))
        fragments.add(IndicatorFragment.newInstance("", ""))
        fragments.add(IndicatorFragment.newInstance("", ""))
        adapter.setFragments(fragments)
        vpIndicator.setTabCount(fragments.size)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                vpIndicator.scroll(position, positionOffset)
            }

            override fun onPageSelected(position: Int) {
            }

        })
    }
}