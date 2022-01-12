package android.gqy.experience.activities.grpcgo

import android.gqy.experience.R
import android.gqy.experience.utils.FileUtil
import android.gqy.experience.utils.ThreadPool
import android.util.Log
import grpc.GrpcDemoService
import grpc.SignInResp
import grpc.User
import java.util.*

class GrpcGoPresenter(val grpcView: IGrpcGoView) {
    private val TAG="GrpcGoPresenter"
    private var user: User? = null
    private val grpcRunnable = GrpcRunnable()

    fun start() {
        ThreadPool.getSinglePool("GrpcGo").execute(grpcRunnable)
    }

    fun stop() {
        user!!.stopServer()
        ThreadPool.getSinglePool("GrpcGo").cancel(grpcRunnable)
    }

    private inner class GrpcRunnable : Runnable {
        override fun run() {
            Log.e(TAG,"run start")
            val agentId = "001"
            val dbPath = FileUtil.getDbPath()
            val gRPCPort = 10808L
            val tlsCert = readKey(R.raw.certclient)
            val tlsKey = readKey(R.raw.certprivate)
            val caCert = readKey(R.raw.certca)
            user =
                User(agentId, dbPath, gRPCPort, tlsCert, tlsKey, caCert, object : GrpcDemoService {
                    override fun signin(devphyid: String): SignInResp {
                        return  SignInResp()
                    }
                })
            user!!.run()
            Log.e(TAG,"run stopped")
        }
    }

    private fun readKey(keyId: Int): String {
        val sb = StringBuilder()
        val input = grpcView.getActivity().resources.openRawResource(keyId)
        val buffer = ByteArray(1024)
        var len = input.read(buffer)
        while (len > 0) {
            sb.append(String(Arrays.copyOfRange(buffer, 0, len)))
            len = input.read(buffer)
        }
        return sb.toString()
    }
}