package com.todos.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.todos.R
import com.todos.pojo.Todo
import kotlinx.android.synthetic.main.holder_item.view.*
import javax.inject.Inject


class HomeAdapter @Inject constructor() : RecyclerView.Adapter<HomeAdapter.MyViewHolder>(){
    var itemsList: ArrayList<Todo> = ArrayList()
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.getContext())
        val view: View = inflater.inflate(R.layout.holder_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.item_title_textView.text= itemsList[position].title
        holder.itemView.item_body_textView.text= itemsList[position].body
    }

    fun setList(newList: ArrayList<Todo>){
        itemsList= newList
        notifyDataSetChanged()
    }

    fun getTodo(position: Int): Todo{
        return itemsList[position]
    }
}