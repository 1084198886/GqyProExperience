package android.gqy.experience.activities.home.bean

import android.app.Activity
/**
 * @author gqy
 * @date 2020/3/31 0031.
 * @since 1.0.0
 * @see
 * @desc  TODO
 */
class FunctionBean<T : Activity> {
    var desc :String?=null
    var claz:Class<T>?=null

    constructor(desc: String, claz: Class<T>){
        this.desc=desc
        this.claz=claz
    }
}