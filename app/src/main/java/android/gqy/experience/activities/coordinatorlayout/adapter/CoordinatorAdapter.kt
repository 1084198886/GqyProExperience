package android.gqy.experience.activities.coordinatorlayout.adapter

import android.content.Context
import android.gqy.experience.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * created by gqy on 2021/02/02
 * @since 1.0.1
 * @desc
 */
class CoordinatorAdapter(val context: Context) : RecyclerView.Adapter<CoordinatorAdapter.Holder>() {
    private val list = ArrayList<String>()
    private val inflater = LayoutInflater.from(context)

    fun setData(data: List<String>) {
        list.clear()
        list.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(inflater.inflate(R.layout.item_coordinator, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        list[position].run {
            holder.tvContent.text = this
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvContent = itemView.findViewById<TextView>(R.id.tv_content)
    }
}