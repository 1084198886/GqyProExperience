package android.gqy.experience.activities.androidinvokejs

import android.gqy.experience.R
import android.gqy.experience.activities.BaseActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.github.lzyzsd.jsbridge.BridgeHandler
import com.github.lzyzsd.jsbridge.BridgeWebView
import com.github.lzyzsd.jsbridge.CallBackFunction
import com.github.lzyzsd.jsbridge.DefaultHandler

/**
 * created by gqy on 2021/01/28
 * @since 1.0.1
 * @desc android 调用js
 */
class AndroidInvokeJsActivity : BaseActivity() {
    private lateinit var webView: BridgeWebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invoke_js)
        initView()
    }

    private fun initView() {
        webView = findViewById<BridgeWebView>(R.id.webView)
        webView.setDefaultHandler(object : DefaultHandler() {

        })
        webView.loadUrl("file:///android_asset/test.html")

        //      注册监听方法当js中调用callHandler方法时会调用此方法（handlerName必须和js中相同）
        webView.registerHandler("submitFromWeb", object : BridgeHandler {
            override fun handler(data: String, function: CallBackFunction) {
                //显示js传递给Android的消息
                Toast.makeText(this@AndroidInvokeJsActivity, "js返回:$data", Toast.LENGTH_LONG).show()
                //Android返回给JS的消息
                function.onCallBack("我是js调用Android返回数据：1")
            }

        })
        findViewById<View>(R.id.btn_js_invoke_android).setOnClickListener {
//              调用js中的方法（必须和js中的handlerName想同）
            webView.callHandler("functionInJs", "Android调用js66", object : CallBackFunction {
                override fun onCallBack(data: String) {
                    Toast.makeText(this@AndroidInvokeJsActivity, data, Toast.LENGTH_LONG).show()
                }

            })
        }
    }

}
