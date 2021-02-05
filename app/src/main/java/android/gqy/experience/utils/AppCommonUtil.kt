package android.gqy.experience.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import java.util.ArrayList

/**
 * @author gqy
 * @date 2020/3/31 0031.
 * @desc TODO
 * @see
 * @since 1.0.0
 */
object AppCommonUtil {

    fun checkPermissions(context: Context): Boolean {
        val needPermissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        needPermissions.forEach {
            if (ContextCompat.checkSelfPermission(
                    context,
                    it
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }
}