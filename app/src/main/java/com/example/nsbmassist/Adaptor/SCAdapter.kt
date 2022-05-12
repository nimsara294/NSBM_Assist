package com.example.nsbmassist.Adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nsbmassist.Model.SCModel
import com.example.nsbmassist.R

class SCAdapter (private val ScList:ArrayList<SCModel>):
    RecyclerView.Adapter<SCAdapter.ViewHolder>(){

    private lateinit var SCListener:onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        SCListener=clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.list_view, parent,false)
        return ViewHolder(itemView,SCListener)
    }

    override fun onBindViewHolder(holder: SCAdapter.ViewHolder, position: Int) {
        val currentEve=ScList[position]
        holder.tvLect.text=(currentEve.event)
    }

    override fun getItemCount(): Int {
        return ScList.size
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