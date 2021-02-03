package android.gqy.experience.activities.coordinatorlayout

import android.gqy.experience.R
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.snackbar.Snackbar

/**
 * https://www.cnblogs.com/ldq2016/p/10690231.html
 * @desc Material Design里折叠工具栏的效果
 */
class GoogleScrollingActivity : AppCompatActivity() {

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
        appbar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                        collapsingToolbarLayout.title = "EXPANDED";//设置title为EXPANDED
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.totalScrollRange) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        collapsingToolbarLayout.title = "";//设置title不显示
                        playButton.visibility = View.VISIBLE;//隐藏播放按钮
                        state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                    }
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        if (state == CollapsingToolbarLayoutState.COLLAPSED) {
                            playButton.visibility = View.GONE;//由折叠变为中间状态时隐藏播放按钮
                        }
                        collapsingToolbarLayout.title = "INTERNEDIATE";//设置title为INTERNEDIATE
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
                    }
                }
            }

        })
    }

    private enum class CollapsingToolbarLayoutState {
        EXPANDED, COLLAPSED, INTERNEDIATE
    }
}