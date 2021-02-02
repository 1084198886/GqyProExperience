package android.gqy.experience.activities

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

class SPApplication : Application() {
    companion object {
        @Volatile
        private var mInstance: SPApplication? = null

        fun getInstance(): SPApplication {
            return mInstance!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
