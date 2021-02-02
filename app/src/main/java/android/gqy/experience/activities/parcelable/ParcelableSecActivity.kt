package android.gqy.experience.activities.parcelable

import android.gqy.experience.R
import android.gqy.experience.activities.BaseActivity
import android.gqy.experience.activities.parcelable.bean.ParcelableBean
import android.gqy.experience.utils.GsonUtil
import android.os.Bundle
import android.widget.TextView

/**
 * Parcelable用法
 */
class ParcelableSecActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parcelable_sec)
        initData()
    }

    private fun initData() {
        val tvParcelable = findViewById<TextView>(R.id.tv_parcelable)
        val parcelableObj = intent.getParcelableExtra<ParcelableBean>("parcelableData")
        tvParcelable.text = GsonUtil.GsonString(parcelableObj)
    }
}
