package android.gqy.experience.activities.imageselector

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.gqy.experience.R
import android.gqy.experience.activities.BaseActivity
import android.gqy.experience.utils.AppCommonUtil
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.engine.impl.GlideEngine
import com.zhihu.matisse.engine.impl.PicassoEngine
import com.zhihu.matisse.filter.Filter
import com.zhihu.matisse.internal.entity.CaptureStrategy
import kotlin.Int
import kotlin.String
import kotlin.also

/**
 * created by gqy on 2021/2/5
 * @desc 图片选择器
 */
class ImageSelectorActivity : BaseActivity(), View.OnClickListener {
    private val REQUEST_CODE_CHOOSE = 23
    private var mAdapter: UriAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_selector)

        findViewById<View>(R.id.zhihu).setOnClickListener(this)
        findViewById<View>(R.id.dracula).setOnClickListener(this)
        findViewById<View>(R.id.only_gif).setOnClickListener(this)
        val recyclerView = findViewById<View>(R.id.recyclerview) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = UriAdapter().also {
            mAdapter = it
        }
    }

    private var clickView: View? = null

    @SuppressLint("CheckResult")
    override fun onClick(v: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !AppCommonUtil.checkPermissions(this)) {
            clickView = v
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 100)
        } else {
            startAction(v)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        var result = true
        grantResults.forEach {
            if (it == PackageManager.PERMISSION_DENIED) {
                result = false
            }
        }
        if (result) {
            startAction(clickView!!)
        } else {
            Toast.makeText(
                this@ImageSelectorActivity,
                R.string.permission_request_denied,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun startAction(v: View) {
        when (v.id) {
            R.id.zhihu -> Matisse.from(this@ImageSelectorActivity)
                .choose(MimeType.ofImage(), false)
                .countable(true)
                .capture(true)
                .captureStrategy(
                    CaptureStrategy(true, "android.gqy.experience.fileProvider", "test")
                )
                .maxSelectable(9)
                .addFilter(GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .gridExpectedSize(
                    resources.getDimensionPixelSize(R.dimen.grid_expected_size)
                )
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(GlideEngine())
                .setOnSelectedListener({ uriList, pathList ->
                    Log.e(
                        "onSelected",
                        "onSelected: pathList=$pathList"
                    )
                })
                .showSingleMediaType(true)
                .originalEnable(true)
                .maxOriginalSize(10)
                .autoHideToolbarOnSingleTap(true)
                .setOnCheckedListener({ isChecked ->
                    Log.e("isChecked", "onCheck: isChecked=$isChecked")
                })
                .forResult(REQUEST_CODE_CHOOSE)
            R.id.dracula -> Matisse.from(this@ImageSelectorActivity)
                .choose(MimeType.ofImage())
                .theme(R.style.Matisse_Dracula)
                .countable(false)
                .addFilter(GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .maxSelectable(9)
                .originalEnable(true)
                .maxOriginalSize(10)
                .imageEngine(PicassoEngine())
                .forResult(REQUEST_CODE_CHOOSE)
            R.id.only_gif -> Matisse.from(this@ImageSelectorActivity)
                .choose(MimeType.of(MimeType.GIF), false)
                .countable(true)
                .maxSelectable(9)
                .addFilter(GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .gridExpectedSize(
                    resources.getDimensionPixelSize(R.dimen.grid_expected_size)
                )
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(GlideEngine())
                .showSingleMediaType(true)
                .originalEnable(true)
                .maxOriginalSize(10)
                .autoHideToolbarOnSingleTap(true)
                .forResult(REQUEST_CODE_CHOOSE)
            else -> {
            }
        }
        mAdapter!!.setData(null, null)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == Activity.RESULT_OK) {
            mAdapter!!.setData(Matisse.obtainResult(data), Matisse.obtainPathResult(data))
            Log.e(
                "OnActivityResult ",
                java.lang.String.valueOf(Matisse.obtainOriginalState(data))
            )
        }
    }

    private class UriAdapter : RecyclerView.Adapter<UriAdapter.UriViewHolder>() {
        private var mUris: List<Uri>? = null
        private var mPaths: List<String>? = null
        fun setData(
            uris: List<Uri>?,
            paths: List<String>?
        ) {
            mUris = uris
            mPaths = paths
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): UriViewHolder {
            return UriViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.uri_item, parent, false)
            )
        }

        override fun onBindViewHolder(
            holder: UriViewHolder,
            position: Int
        ) {
            holder.mUri.text = mUris!![position].toString()
            holder.mPath.text = mPaths!![position]
            holder.mUri.alpha = if (position % 2 == 0) 1.0f else 0.54f
            holder.mPath.alpha = if (position % 2 == 0) 1.0f else 0.54f
        }

        override fun getItemCount(): Int {
            return if (mUris == null) 0 else mUris!!.size
        }

        internal class UriViewHolder(contentView: View) :
            RecyclerView.ViewHolder(contentView) {
            val mUri: TextView = contentView.findViewById<View>(R.id.uri) as TextView
            val mPath: TextView = contentView.findViewById<View>(R.id.path) as TextView
        }
    }

}
