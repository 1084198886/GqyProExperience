package android.gqy.experience.activities.coordinatorlayout

import android.gqy.experience.R
import android.gqy.experience.activities.BaseActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetDialog

/**
 * created by gqy on 2021/02/04
 * @since 1.0.1
 * @desc  BottomSheetDialog使用
 */
class BottomSheetBehaviorActivity : BaseActivity() {
    private lateinit var shareView: FrameLayout
    private var sheetDialog: BottomSheetDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottomsheetbehavior)
        initView()
    }

    private fun initView() {
        findViewById<Button>(R.id.btn_showsheetdialog).setOnClickListener {
            showBottomSheetDialog()
        }
    }

    private fun showBottomSheetDialog() {
        if (sheetDialog == null) {
            sheetDialog = BottomSheetDialog(this)
            val contentView =
                LayoutInflater.from(this).inflate(R.layout.bottom_sheet_share_dialog, null)
            sheetDialog!!.setContentView(contentView)
            sheetDialog!!.setCancelable(true)
            sheetDialog!!.setCanceledOnTouchOutside(true)
        }
        sheetDialog!!.show()
    }
}