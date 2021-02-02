package android.gqy.experience.activities.coordinatorlayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.gqy.experience.R
import android.gqy.experience.activities.coordinatorlayout.adapter.CoordinatorAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CoordinatorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CoordinatorFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_coordinator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View) {
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = CoordinatorAdapter(context!!)
        val datas = ArrayList<String>()
        for (i in 0 until 50) {
            datas.add("$i")
        }
        adapter.setData(datas)
        recyclerView.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                CoordinatorFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}