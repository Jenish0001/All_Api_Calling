package com.example.all_api_calling.Adpter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.all_api_calling.MainActivity
import com.example.all_api_calling.R
import com.example.all_api_calling.volleyModel

class My_Adpter(val mainActivity: MainActivity, val list: ArrayList<volleyModel>) :
    RecyclerView.Adapter<My_Adpter.ViewData>() {


    class ViewData(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var f_userID = itemView.findViewById<TextView>(R.id.f_userID)
        var f_id = itemView.findViewById<TextView>(R.id.f_id)
        var f_title = itemView.findViewById<TextView>(R.id.f_title)
        var f_body = itemView.findViewById<TextView>(R.id.f_body)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {
        var view = LayoutInflater.from(mainActivity).inflate(R.layout.item, parent, false)
        return ViewData(view)
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {
        holder.f_userID.text = list[position].userId
        holder.f_id.text = list[position].id
        holder.f_title.text = list[position].title
        holder.f_body.text = list[position].body

    }

    override fun getItemCount(): Int {
        return list.size
    }

//    fun filterAdpter(l1: ArrayList<String>)
//    {
//
//    }
}