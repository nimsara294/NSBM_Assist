package com.example.nsbmassist.Adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nsbmassist.Model.EveCalModel
import com.example.nsbmassist.R

class EveCalAdapter(private val EcList:ArrayList<EveCalModel>):
    RecyclerView.Adapter<EveCalAdapter.ViewHolder>(){

    private lateinit var ECListener:onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        ECListener=clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.list_view, parent,false)
        return ViewHolder(itemView,ECListener)
    }

    override fun onBindViewHolder(holder: EveCalAdapter.ViewHolder, position: Int) {
        val currentEve=EcList[position]
        holder.tvLect.text=(currentEve.event)
    }

    override fun getItemCount(): Int {
        return EcList.size
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