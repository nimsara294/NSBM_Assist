package com.example.nsbmassist.Adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nsbmassist.Model.FobModel
import com.example.nsbmassist.R

class FobAdapter(private val fobList: ArrayList<FobModel>):
    RecyclerView.Adapter<FobAdapter.ViewHolder>(){

    private lateinit var lectListener:onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        lectListener=clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.list_view, parent,false)
        return ViewHolder(itemView,lectListener)
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener): RecyclerView.ViewHolder(itemView){
        val tvLect: TextView =itemView.findViewById(R.id.tvLoad)

        init {
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onBindViewHolder(holder: FobAdapter.ViewHolder, position: Int) {
        val currentLect=fobList[position]
        holder.tvLect.text=(currentLect.batch+" "+currentLect.lect)
    }

    override fun getItemCount(): Int {
        return fobList.size
    }

    /*class ViewHolder(itemView: View, clickListener: onItemClickListener): RecyclerView.ViewHolder(itemView){
        val tvLect: TextView =itemView.findViewById(R.id.tvLoad)

        init {
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }*/

}