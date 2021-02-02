package android.gqy.experience.utils

import android.app.Activity
import java.util.*

/**
 * @author gqy
 * @date 2020/3/12.
 * @version 1.0.1
 * @desc : activity管理
 */
object AppExitUtil {
    private val acys = HashSet<Activity>()

    fun add(acy: Activity) {
        acys.add(acy)
    }

    fun remove(acy: Activity?) {
        acys.remove(acy)
        if (acy != null && !acy.isFinishing) {
            acy.finish()
        }
        if (acys.size == 0) {
            killProcess()
        }
    }

    fun removeAll(){
        val iter = acys.iterator()
        val arrs = arrayOfNulls<Activity>(acys.size)
        var i = 0
        while (iter.hasNext()) {
            arrs[i] = iter.next()
            i += 1
        }
        acys.clear()
        arrs.indices
                .filter { arrs[it] != null && !arrs[it]!!.isFinishing }
                .forEach { arrs[it]!!.finish() }
    }

    fun exit() {
        removeAll()
        killProcess()
    }

    private fun killProcess() {
        android.os.Process.killProcess(android.os.Process.myPid())
    }
}