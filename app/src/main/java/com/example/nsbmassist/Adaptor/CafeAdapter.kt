package com.example.nsbmassist.Adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nsbmassist.Model.CafeModel
import com.example.nsbmassist.R

class CafeAdapter (private val CafeList:ArrayList<CafeModel>):
    RecyclerView.Adapter<CafeAdapter.ViewHolder>(){

    private lateinit var CafeListener:onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        CafeListener=clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.list_view, parent,false)
        return ViewHolder(itemView,CafeListener)
    }

    override fun onBindViewHolder(holder: CafeAdapter.ViewHolder, position: Int) {
        val currentCafe=CafeList[position]
        holder.tvLect.text=(currentCafe.name)
    }

    override fun getItemCount(): Int {
        return CafeList.size
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