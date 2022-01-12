package android.gqy.experience.activities.grpcgo

import android.app.Activity
import android.gqy.experience.R
import android.gqy.experience.activities.BaseActivity
import android.os.Bundle

/**
 * grpc在go中的使用
 */
class GrpcGoActivity : BaseActivity(), IGrpcGoView {
    val TAG = "GrpcGoActivity"
    private lateinit var presenter: GrpcGoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grpc_go)
        presenter = GrpcGoPresenter(this)
        initView()
    }

    private fun initView() {
    }

    override fun getActivity(): Activity {
        return this
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
