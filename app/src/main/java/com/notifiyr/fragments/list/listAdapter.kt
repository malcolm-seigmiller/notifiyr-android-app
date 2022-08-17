package com.notifiyr.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.notifiyr.R
import com.notifiyr.models.cccode
import kotlinx.android.synthetic.main.db_row.view.*

class listAdapter: RecyclerView.Adapter<listAdapter.MyViewHolder>() {

    private var Cclist = emptyList<cccode>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.db_row, parent, false))
    }

    override fun getItemCount(): Int {
        return Cclist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = Cclist[position]
        holder.itemView.text_view_1.text = currentItem.cccode.toString()
        holder.itemView.id_text_view.text = currentItem.id.toString()

        holder.itemView.db_carditem.setOnClickListener {
            val action = listFragmentDirections.actionListFragment2ToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(cccode:List<cccode>){
        this.Cclist = cccode
        notifyDataSetChanged()
    }
}