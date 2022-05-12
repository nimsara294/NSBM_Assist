package com.example.nsbmassist.Adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nsbmassist.Model.TransModel
import com.example.nsbmassist.R

class TransAdapter (private val TransList:ArrayList<TransModel>):
    RecyclerView.Adapter<TransAdapter.ViewHolder>(){

    private lateinit var TransListener:onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        TransListener=clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.list_view, parent,false)
        return ViewHolder(itemView,TransListener)
    }

    override fun onBindViewHolder(holder: TransAdapter.ViewHolder, position: Int) {
        val currentTrip=TransList[position]
        holder.tvLect.text=(currentTrip.start+" to "+currentTrip.dest)
    }

    override fun getItemCount(): Int {
        return TransList.size
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