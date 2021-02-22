package android.gqy.experience.utils

import android.content.Context
import android.widget.Toast

/**
 * created by gqy on 2021/02/06
 * @since 1.0.1
 * @desc
 */
object ToastUtil {

    fun show(context: Context, msg: String?) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}