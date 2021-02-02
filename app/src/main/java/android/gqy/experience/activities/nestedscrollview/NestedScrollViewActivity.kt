package android.gqy.experience.activities.nestedscrollview

import android.gqy.experience.R
import android.gqy.experience.activities.BaseActivity
import android.gqy.experience.activities.nestedscrollview.adapter.NestedRecylerAdapter
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * NestedScrollView嵌套RecyclerView使用
 */
class NestedScrollViewActivity : BaseActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nestedscrollview)
        initView()
    }

    private fun initView() {
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        val adapter = NestedRecylerAdapter(this)
        adapter.setData(getDatas())
        recyclerView.adapter = adapter
        recyclerView.isFocusable=false
    }

    private fun getDatas(): List<String> {
        val list = arrayListOf<String>()
        (0..30).forEach {
            list.add("AAAAAAAAAAAAAAAAAAAAA$it")
        }
        return list
    }

}
