package android.gqy.experience.activities.grpcgo

import android.app.Activity

interface IGrpcGoView {
    fun getActivity(): Activity
    fun appendContent(content :String)
}