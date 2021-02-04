package android.gqy.experience.activities

import androidx.multidex.MultiDexApplication

class SPApplication : MultiDexApplication() {
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
}
