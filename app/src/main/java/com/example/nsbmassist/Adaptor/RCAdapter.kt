package com.example.nsbmassist.Adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nsbmassist.Model.RCModel
import com.example.nsbmassist.R

class RCAdapter(private val RCList:ArrayList<RCModel>):
    RecyclerView.Adapter<RCAdapter.ViewHolder>(){

    private lateinit var RCListener:onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        RCListener=clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.list_view, parent,false)
        return ViewHolder(itemView,RCListener)
    }

    override fun onBindViewHolder(holder: RCAdapter.ViewHolder, position: Int) {
        val currentRC=RCList[position]
        holder.tvLect.text=(currentRC.event)
    }

    override fun getItemCount(): Int {
        return RCList.size
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener): RecyclerView.ViewHolder(itemView){
        val tvLect: TextView =itemView.findViewById(R.id.tvLoad)

        init {
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

}