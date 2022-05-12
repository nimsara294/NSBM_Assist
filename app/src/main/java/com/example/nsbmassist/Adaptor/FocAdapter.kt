package com.example.nsbmassist.Adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nsbmassist.Model.FocModel
import com.example.nsbmassist.R

class FocAdapter(private val focList:ArrayList<FocModel>):
    RecyclerView.Adapter<FocAdapter.ViewHolder>(){

    private lateinit var lectListener:onItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.list_view, parent,false)
        return ViewHolder(itemView,lectListener)
    }
    class ViewHolder(itemView: View, clickListener: onItemClickListener):RecyclerView.ViewHolder(itemView){
        val tvLect:TextView=itemView.findViewById(R.id.tvLoad)

        init {
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }


    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        lectListener=clickListener
    }

    /*override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.list_view, parent,false)
        return ViewHolder(itemView,lectListener)
    }*/

    override fun onBindViewHolder(holder: FocAdapter.ViewHolder, position: Int) {
        val currentLect=focList[position]
        holder.tvLect.text=(currentLect.batch+" "+currentLect.lect)
    }

    override fun getItemCount(): Int {
        return focList.size
    }

    /*class ViewHolder(itemView: View, clickListener: onItemClickListener):RecyclerView.ViewHolder(itemView){
        val tvLect:TextView=itemView.findViewById(R.id.tvLoad)

        init {
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }*/

}