package android.gqy.experience.activities.splash

import android.gqy.experience.R
import android.gqy.experience.activities.BaseActivity
import android.gqy.experience.activities.home.HomeActivity
import android.os.Bundle

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initView()
    }

    private fun initView() {
    }

    override fun onResume() {
        super.onResume()
        jumpToActivity(HomeActivity::class.java)
    }
}
