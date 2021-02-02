package android.gqy.experience.activities.fileprovider

import android.gqy.experience.R
import android.gqy.experience.activities.BaseActivity
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.content.FileProvider
import android.os.Build
import java.io.File


/**
 * FileProvider使用
 * @see androidx.core.content.FileProvider
 * tips:
 * 1, manifest中声明FileProvider(因为FileProvider是ContentProvider子类,要注册),设置一个meta-data，里面指向一个xml文件
 * 2, 编写XML文件(因为要使用content://uri替代file://uri,需要一个虚拟的路径对文件路径进行映射,
 * 通过path以及xml节点确定可访问的目录，通过name属性来映射真实的文件路径)
 */
class FileProviderActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_provider)
        initView()
    }

    private fun initView() {
        findViewById<View>(R.id.btn_capture).setOnClickListener {
            val file = File(filesDir.absolutePath + "/text", "hello.txt")
            val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                FileProvider.getUriForFile(this, "android.gqy.experience.fileProvider", file)
            } else {
                Uri.fromFile(file)
            }
        }
    }
}
