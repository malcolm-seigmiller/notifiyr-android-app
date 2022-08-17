package com.notifiyr.fragments.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.notifiyr.R
import com.notifiyr.fragments.list.listFragmentDirections
import com.notifiyr.models.Mlist
import com.notifiyr.models.cccode
import kotlinx.android.synthetic.main.carditem.view.*
import kotlinx.android.synthetic.main.db_row.view.*

class MainAdapter: RecyclerView.Adapter<MainAdapter.MyViewHolder>(){

    private var myList = emptyList<Mlist>()

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val abc = MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.carditem, parent, false))
        return abc
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val abc = myList[position]

        holder.itemView.note_text_view_1.text = myList[position].cccode
        holder.itemView.note_text_view_2.text = myList[position].header
        holder.itemView.note_text_View_3.text = myList[position].body

        holder.itemView.carditem.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToViewFragment(abc)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(newList: List<Mlist>){
        myList = newList
        notifyDataSetChanged()
    }
}