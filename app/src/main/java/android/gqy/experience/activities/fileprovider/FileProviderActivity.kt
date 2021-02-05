package android.gqy.experience.activities.fileprovider

import android.content.Intent
import android.gqy.experience.R
import android.gqy.experience.activities.BaseActivity
import android.gqy.experience.utils.AppCommonUtil
import android.gqy.experience.utils.FileUtil
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.content.FileProvider
import android.os.Build
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.TextView
import java.io.File
import java.io.IOException


/**
 * FileProvider使用
 * @see androidx.core.content.FileProvider
 * tips:
 * 1, manifest中声明FileProvider(因为FileProvider是ContentProvider子类,要注册),设置一个meta-data，里面指向一个xml文件
 * 2, 编写XML文件(因为要使用content://uri替代file://uri,需要一个虚拟的路径对文件路径进行映射,
 * 通过path以及xml节点确定可访问的目录，通过name属性来映射真实的文件路径)
 */
class FileProviderActivity : BaseActivity() {
    private lateinit var mImageView: ImageView
    private val REQ_CODE_OPEN_CAMERA: Int = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_provider)
        initView()
    }

    private fun initView() {
        val mTextView = findViewById<TextView>(R.id.tv_description)
        mTextView.setText("本Demo演示API 24+平台FileProvider使用，当前API level:${Build.VERSION.SDK_INT}")

        mImageView = findViewById<ImageView>(R.id.imageView)
        findViewById<View>(R.id.btn_capture).setOnClickListener {
            openSysCamera()
        }
    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !AppCommonUtil.checkPermissions(this)) {
            requestPermissions(AppCommonUtil.needPermissions, 100)
        }
    }

    private fun openSysCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        var file: File? = null
        val originImgUri: Uri?
        try {
            file = createOriginalImageFile()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        if (file != null) {
            originImgUri = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                Uri.fromFile(file)
            } else {
                FileProvider.getUriForFile(this, "android.gqy.experience.fileProvider", file)
            }
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, originImgUri)
            startActivityForResult(cameraIntent, REQ_CODE_OPEN_CAMERA)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQ_CODE_OPEN_CAMERA -> {
                val tempFile = File(FileUtil.getCameraImagePath() + "image.png")
                val bitmap = BitmapFactory.decodeFile(tempFile.absolutePath)
                mImageView.setImageBitmap(bitmap)
            }
        }
    }

    /**
     * 创建原图像保存的文件
     *
     * @return
     */
    private fun createOriginalImageFile(): File? {
        val imagePath = FileUtil.getCameraImagePath()
        if (imagePath != null) {
            val file = File(imagePath + "image.png")
            if (!file.exists()) {
                file.createNewFile()
            }
            return file
        }
        return null
    }
}
