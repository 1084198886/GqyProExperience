package android.gqy.experience.activities.home.adapter

import android.content.Context
import android.gqy.experience.R
import android.gqy.experience.activities.home.bean.FuncMenu
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
class AppFunctionAdapter(context: Context) : RecyclerView.Adapter<AppFunctionAdapter.Holder>() {
    private val list = ArrayList<FuncMenu>()
    private val inflater = LayoutInflater.from(context)

    fun setData(functions: List<FuncMenu>) {
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
            holder.tvFunction.text = it.desc
        }
        holder.tvFunction.setOnClickListener {
            callBack!!.itemClick(list[position])
        }
    }

    var callBack: ItemClickCallBack? = null

    interface ItemClickCallBack {
        fun itemClick(bean: FuncMenu)
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvFunction = itemView.findViewById<TextView>(R.id.tv_function)
    }
}