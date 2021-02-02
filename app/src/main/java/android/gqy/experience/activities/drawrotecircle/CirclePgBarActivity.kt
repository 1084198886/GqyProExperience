package android.gqy.experience.activities.drawrotecircle

import android.gqy.experience.R
import android.gqy.experience.activities.BaseActivity
import android.gqy.experience.view.CirclePgBar
import android.os.Bundle

/**
 * created by gqy on 2021/01/28
 * @since 1.0.1
 * @desc
 */
class CirclePgBarActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circlepgbar)
        initView()
    }

    private fun initView() {
        val circlePgBar = findViewById<CirclePgBar>(R.id.circlePgBar)
    }
}