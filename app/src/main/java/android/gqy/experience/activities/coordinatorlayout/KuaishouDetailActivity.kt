package android.gqy.experience.activities.coordinatorlayout

import android.gqy.experience.R
import android.gqy.experience.activities.BaseActivity
import android.gqy.experience.activities.coordinatorlayout.adapter.CoordinatorAdapter
import android.gqy.experience.utils.RecyclerViewDivider
import android.media.MediaPlayer
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * created by gqy on 2021/02/06
 * @since 1.0.1
 * @desc  快手APP视频详情页
 */
class KuaishouDetailActivity : BaseActivity() {
    private val TAG = "KuaishouDetail"
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var surfaceView: SurfaceView
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kuaishou_detail)
        initView()
    }

    private fun initView() {
        surfaceView = findViewById<SurfaceView>(R.id.surfaceview)
        surfaceView.holder!!.addCallback(holderCallBack)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(RecyclerViewDivider(this, 2, RecyclerView.HORIZONTAL))
        val adapter = CoordinatorAdapter(this)
        adapter.setData(arrayListOf("item1", "item2", "item3", "item4", "item5", "item6", "item7", "item8", "item9", "item10"))
        recyclerView.adapter = adapter
    }

    private val holderCallBack = object : SurfaceHolder.Callback {
        override fun surfaceChanged(
            holder: SurfaceHolder,
            format: Int,
            width: Int,
            height: Int
        ) {
        }

        override fun surfaceDestroyed(holder: SurfaceHolder) {
            mediaPlayer?.stop()
            mediaPlayer?.release()
        }

        override fun surfaceCreated(holder: SurfaceHolder) {
            initMediaPlayer()
            //设置让surfaceView来播放
            mediaPlayer!!.setDisplay(surfaceView.holder)
            mediaPlayer!!.prepareAsync()
        }
    }

    fun initMediaPlayer() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer()
        }
        mediaPlayer!!.setOnPreparedListener(object : MediaPlayer.OnPreparedListener {
            override fun onPrepared(mp: MediaPlayer) {
                mp.start()
            }
        })
        try {
            mediaPlayer!!.setDataSource("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}