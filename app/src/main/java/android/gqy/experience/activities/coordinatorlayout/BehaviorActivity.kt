package android.gqy.experience.activities.coordinatorlayout

import android.gqy.experience.R
import android.gqy.experience.activities.coordinatorlayout.adapter.CoordinatorAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * created by gqy on 2021/2/3
 * @desc 自定义Behavior
 */
class BehaviorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_behavior)
        initView()
    }

    private fun initView() {
      val   recyclerView = findViewById<RecyclerView>(R.id.my_list)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = CoordinatorAdapter(this)
        val datas = ArrayList<String>()
        for (i in 0 until 50) {
            datas.add("$i")
        }
        adapter.setData(datas)
        recyclerView.adapter = adapter
    }
}