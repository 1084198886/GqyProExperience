package android.gqy.experience.activities


import android.content.Intent
import android.gqy.experience.utils.AppExitUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @author gqy
 * @date 2020/3/11
 * @version 1.0.0
 * @desc  基类
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppExitUtil.add(this)
    }

    override fun onDestroy() {
        AppExitUtil.remove(this)
        super.onDestroy()
    }

    fun <T> jumpToActivity(cls: Class<T>) {
        startActivity(Intent().setClass(this, cls))
    }
}
