package android.gqy.experience.activities.touchevent

import android.annotation.SuppressLint
import android.gqy.experience.R
import android.gqy.experience.activities.BaseActivity
import android.gqy.experience.activities.coordinatorlayout.CoordinatorPageAdapter
import android.gqy.experience.activities.touchevent.fragment.IFragmentCallback
import android.gqy.experience.activities.touchevent.fragment.TouchEventFragment
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

/**
 * created by  gqy on 2021/2/23
 * @desc View的getParent().requestDisallowInterceptTouchEvent(true)使用
 */
class RequestDisallowInterceptActivity : BaseActivity(), IFragmentCallback {
    val TAG = "TouchEvent"
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_requestdisallowintercept)
        initView()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initView() {
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        viewPager.offscreenPageLimit = 3
        viewPager.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        log("ViewPager OnTouchListener.onTouch ACTION_DOWN")
                    }
                    MotionEvent.ACTION_MOVE -> {
                        log("ViewPager OnTouchListener.onTouch ACTION_MOVE")
                    }
                    MotionEvent.ACTION_UP -> {
                        log("ViewPager OnTouchListener.onTouch ACTION_UP")
                    }
                }
                return false
            }
        })

        val adapter = CoordinatorPageAdapter(
            supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        val fragments = ArrayList<Fragment>()
        fragments.add(TouchEventFragment.newInstance("", ""))
        fragments.add(TouchEventFragment.newInstance("", ""))
        fragments.add(TouchEventFragment.newInstance("", ""))
        fragments.add(TouchEventFragment.newInstance("", ""))
        adapter.setFragments(fragments)
        adapter.setTitles(arrayListOf("标题一", "标题二", "标题三", "标题四"))

        // 此处使用Adapter中的getPageTitle方法，不用addTab，addTab会导致标题不显示；
//        for (pos in 0 until fragments.size) {
//            tabLayout.addTab(tabLayout.newTab().setText("Title"), pos, pos == 0)
//        }
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun getViewPager(): ViewPager {
        return viewPager
    }

    fun log(msg: String) {
        Log.e(TAG, msg)
    }

}
