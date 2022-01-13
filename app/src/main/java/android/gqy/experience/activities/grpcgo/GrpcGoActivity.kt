package android.gqy.experience.activities.grpcgo

import android.app.Activity
import android.gqy.experience.R
import android.gqy.experience.activities.BaseActivity
import android.os.Bundle
import android.widget.TextView

/**
 * grpc在go中的使用
 */
class GrpcGoActivity : BaseActivity(), IGrpcGoView {
    val TAG = "GrpcGoActivity"
    private lateinit var presenter: GrpcGoPresenter
    private lateinit var tvContent: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grpc_go)
        presenter = GrpcGoPresenter(this)
        initView()
    }

    private fun initView() {
        tvContent = findViewById<TextView>(R.id.tv_content)
    }

    override fun getActivity(): Activity {
        return this
    }

    override fun appendContent(content: String) {
        val origStr = tvContent.text.toString()
        if (origStr.isEmpty()) {
            tvContent.text = content
        } else {
            tvContent.text = "${origStr}\n$content"
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }
}
