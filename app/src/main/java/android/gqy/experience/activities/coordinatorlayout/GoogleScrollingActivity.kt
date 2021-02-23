package android.gqy.experience.activities.coordinatorlayout

import android.gqy.experience.R
import android.gqy.experience.activities.BaseActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.snackbar.Snackbar

/**
 * https://www.cnblogs.com/ldq2016/p/10690231.html
 * @desc Material Design里折叠工具栏的效果
 */
class GoogleScrollingActivity : BaseActivity() {
    private lateinit var collapsingToolbarLayout: CollapsingToolbarLayout
    private var state = CollapsingToolbarLayoutState.EXPANDED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(findViewById(R.id.toolbar))
        val playButton = findViewById<Button>(R.id.btn_play)

        collapsingToolbarLayout = findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)
        /**
         * 修改展示的文字标题
         */
        collapsingToolbarLayout.title = title
        playButton.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val appbar = findViewById<AppBarLayout>(R.id.app_bar)
        appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { 
                appBarLayout, verticalOffset ->
            if (verticalOffset == 0) {
                if (state != CollapsingToolbarLayoutState.EXPANDED) {
                    state = CollapsingToolbarLayoutState.EXPANDED
                    collapsingToolbarLayout.title = "EXPANDED"
                }
            } else if (Math.abs(verticalOffset) >= appBarLayout.totalScrollRange) {
                if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                    collapsingToolbarLayout.title = ""
                    playButton.visibility = View.VISIBLE
                    state = CollapsingToolbarLayoutState.COLLAPSED
                }
            } else {
                if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                    if (state == CollapsingToolbarLayoutState.COLLAPSED) {
                        playButton.visibility = View.GONE
                    }
                    collapsingToolbarLayout.title = "INTERNEDIATE"
                    state = CollapsingToolbarLayoutState.INTERNEDIATE
                }
            }
        })
    }

    private enum class CollapsingToolbarLayoutState {
        EXPANDED, COLLAPSED, INTERNEDIATE
    }
}