package android.gqy.experience.activities.home

import android.gqy.experience.R
import android.gqy.experience.activities.BaseActivity
import android.gqy.experience.activities.coordinatorlayout.*
import android.gqy.experience.activities.drawindicator.DrawViewPagerIndicatorActivity
import android.gqy.experience.activities.drawrotecircle.CirclePgBarActivity
import android.gqy.experience.activities.fileprovider.FileProviderActivity
import android.gqy.experience.activities.home.adapter.AppFunctionAdapter
import android.gqy.experience.activities.home.bean.FuncMenu
import android.gqy.experience.activities.imageselector.ImageSelectorActivity
import android.gqy.experience.activities.nestedscrollview.NestedScrollViewActivity
import android.gqy.experience.activities.parcelable.ParcelableActivity
import android.gqy.experience.activities.pulltozoomview.PullToZoomListActivity
import android.gqy.experience.activities.pulltozoomview.PullToZoomScrollActivity
import android.gqy.experience.activities.pulltozoomview.recyclerview.PullToZoomRecyclerActivity
import android.gqy.experience.activities.scroller.ScrollerUsageActivity
import android.gqy.experience.activities.scroller.ScrollParallaxActivity
import android.gqy.experience.activities.touchevent.RequestDisallowInterceptActivity
import android.gqy.experience.activities.touchevent.TouchEventActivity
import android.gqy.experience.utils.RecyclerViewDivider
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.newcapec.lib.melexis.NcJni

/**
 * created by gqy on 2021/01/28
 * @since 1.0.1
 * @desc 主页
 */
class HomeActivity : BaseActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initView()
        NcJni.InitParameters("11".toByteArray())
    }

    private fun initView() {
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(RecyclerViewDivider(this, 1, RecyclerView.HORIZONTAL))
        val adapter = AppFunctionAdapter(this)
        adapter.callBack = object : AppFunctionAdapter.ItemClickCallBack {
            override fun itemClick(bean: FuncMenu) {
                jumpByFuncMenu(bean)
            }
        }
        adapter.setData(getFunctions())
        recyclerView.adapter = adapter
    }

    private fun jumpByFuncMenu(bean: FuncMenu) {
        when (bean) {
            FuncMenu.ScrollParallax -> jumpToActivity(ScrollParallaxActivity::class.java)
            FuncMenu.Parcelable -> jumpToActivity(ParcelableActivity::class.java)
            FuncMenu.NestedScrollView -> jumpToActivity(NestedScrollViewActivity::class.java)
            FuncMenu.FileProvider -> jumpToActivity(FileProviderActivity::class.java)
            FuncMenu.CircleProgressBar -> jumpToActivity(CirclePgBarActivity::class.java)
            FuncMenu.Scroller -> jumpToActivity(ScrollerUsageActivity::class.java)
            FuncMenu.DrawViewPagerIndicator -> jumpToActivity(DrawViewPagerIndicatorActivity::class.java)
            FuncMenu.CoordinatorLayout -> jumpToActivity(CoordinatorLayoutActivity::class.java)
            FuncMenu.GoogleScrollingActivity -> jumpToActivity(GoogleScrollingActivity::class.java)
            FuncMenu.MatialDesignBehavior -> jumpToActivity(BehaviorActivity::class.java)
            FuncMenu.BottomSheetBehavior -> jumpToActivity(BottomSheetBehaviorActivity::class.java)
            FuncMenu.CoverHeaderScrollBehavior -> jumpToActivity(CoverHeaderScrollActivity::class.java)
            FuncMenu.ImageSelector -> jumpToActivity(ImageSelectorActivity::class.java)
            FuncMenu.ScrollShowAndHideTitlebar -> jumpToActivity(ScrollShowTitlebarActivity::class.java)
            FuncMenu.KuaishouAppDetail -> jumpToActivity(KuaishouDetailActivity::class.java)
            FuncMenu.PullToZoomListView -> jumpToActivity(PullToZoomListActivity::class.java)
            FuncMenu.PullToZoomScrollView -> jumpToActivity(PullToZoomScrollActivity::class.java)
            FuncMenu.PullToZoomRecyclerView -> jumpToActivity(PullToZoomRecyclerActivity::class.java)
            FuncMenu.TouchEventDemo -> jumpToActivity(TouchEventActivity::class.java)
            FuncMenu.RequestDisallowInterceptTouchEvent -> jumpToActivity(RequestDisallowInterceptActivity::class.java)
        }
    }

    private fun getFunctions(): List<FuncMenu> {
        val list = ArrayList<FuncMenu>()
        list.add(FuncMenu.Parcelable)
        list.add(FuncMenu.NestedScrollView)
        list.add(FuncMenu.FileProvider)
        list.add(FuncMenu.CircleProgressBar)
        list.add(FuncMenu.Scroller)
        list.add(FuncMenu.ScrollParallax)
        list.add(FuncMenu.DrawViewPagerIndicator)
        list.add(FuncMenu.CoordinatorLayout)
        list.add(FuncMenu.GoogleScrollingActivity)
        list.add(FuncMenu.MatialDesignBehavior)
        list.add(FuncMenu.BottomSheetBehavior)
        list.add(FuncMenu.CoverHeaderScrollBehavior)
        list.add(FuncMenu.ImageSelector)
        list.add(FuncMenu.ScrollShowAndHideTitlebar)
        list.add(FuncMenu.KuaishouAppDetail)
        list.add(FuncMenu.PullToZoomListView)
        list.add(FuncMenu.PullToZoomScrollView)
        list.add(FuncMenu.PullToZoomRecyclerView)
        list.add(FuncMenu.TouchEventDemo)
        list.add(FuncMenu.RequestDisallowInterceptTouchEvent)
        return list
    }


    override fun onResume() {
        super.onResume()
//        jumpToActivity(TouchEventActivity::class.java)
    }
}
