package android.gqy.experience.activities.nestedscrollview.adapter

import android.content.Context
import android.gqy.experience.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * @author gqy
 * @date 2020/3/31
 * @since 1.0.0
 * @see
 * @desc  TODO
 */
class NestedRecylerAdapter(context: Context) : RecyclerView.Adapter<NestedRecylerAdapter.Holder>() {
    private val list = ArrayList<String>()
    private val inflater = LayoutInflater.from(context)

    fun setData(functions: List<String>) {
        list.clear()
        if (functions.isNotEmpty()) {
            list.addAll(functions)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView = inflater.inflate(R.layout.item_home_funcion, parent, false)
        return Holder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        list[position].let {
            holder.tvFunction.text = it
        }
        holder.tvFunction.setOnClickListener {
            callBack?.itemClick(list[position])
        }
    }

    var callBack: ItemClickCallBack? = null

    interface ItemClickCallBack {
        fun itemClick(bean: String)
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvFunction = itemView.findViewById<TextView>(R.id.tv_function)
    }
}