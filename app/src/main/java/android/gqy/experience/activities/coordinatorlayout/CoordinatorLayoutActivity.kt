package android.gqy.experience.activities.coordinatorlayout

import android.gqy.experience.R
import android.gqy.experience.activities.BaseActivity
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

/**
 * created by  gqy on 2021/2/2
 * @desc CoordinatorLayout配合AppBarLayout,Toolbar和TabLayout的使用
 */
class CoordinatorLayoutActivity : BaseActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coordinatorlayout)
        initView()
    }

    private fun initView() {
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        viewPager.offscreenPageLimit = 3

        val adapter = CoordinatorPageAdapter(
            supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        val fragments = ArrayList<Fragment>()
        fragments.add(CoordinatorFragment.newInstance("", ""))
        fragments.add(CoordinatorFragment.newInstance("", ""))
        fragments.add(CoordinatorFragment.newInstance("", ""))
        fragments.add(CoordinatorFragment.newInstance("", ""))
        adapter.setFragments(fragments)
        adapter.setTitles(arrayListOf("标题一", "标题二", "标题三", "标题四"))

         // 此处使用Adapter中的getPageTitle方法，不用addTab，addTab会导致标题不显示；
//        for (pos in 0 until fragments.size) {
//            tabLayout.addTab(tabLayout.newTab().setText("Title"), pos, pos == 0)
//        }
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }

}
