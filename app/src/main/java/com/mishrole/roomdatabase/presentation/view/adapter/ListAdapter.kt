package com.mishrole.roomdatabase.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mishrole.roomdatabase.R
import com.mishrole.roomdatabase.data.entity.User

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    // User is our Model Class
    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Inflate custom_row
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        // Find view id and set text from our currentItem
        holder.itemView.findViewById<TextView>(R.id.tvListId).text = currentItem.id.toString()
        holder.itemView.findViewById<TextView>(R.id.tvListFirstname).text = currentItem.firstname
        holder.itemView.findViewById<TextView>(R.id.tvListLastname).text = currentItem.lastName
        holder.itemView.findViewById<TextView>(R.id.tvListAge).text = currentItem.age.toString()
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    // Set userList to pass in the params
    fun setData(user: List<User>) {
        this.userList = user
        notifyDataSetChanged()
    }

}