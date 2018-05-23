package com.example.dung.notetodoandroid

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.cell.view.*

class RecyclerAdapter(var context: Context, var lists: MutableList<Int>, var delegate: RecyclerAdapterInterface?): RecyclerView.Adapter<RecyclerAdapter.ViewHolderCustom>()  {

    var positionForSelected: Int? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCustom {
        val view = LayoutInflater.from(parent.context)
        val cell = ViewHolderCustom(view.inflate(R.layout.cell, parent, false))
        return cell
    }

    override fun getItemCount(): Int {
        return  lists.size
    }

    override fun onBindViewHolder(holder: ViewHolderCustom, position: Int) {
        holder.bindData(lists[position])
        holder.itemView.setOnClickListener {
            delegate?.didSelected(holder.itemView, position)
            positionForSelected = position
        }
    }
//    val swipeHandler = object : RecyclerItemTouchHelper(this) {
//        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//            val adapter = recyclerView.adapter as RecyclerAdapter
//            numbers.removeAt(viewHolder.adapterPosition)
//            adapter.notifyDataSetChanged()
//        }
//    }

    fun removeAt(position: Int) {
        lists.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHolderCustom(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindData(_number: Int) {
            itemView.textLabel.text = _number.toString()
        }
    }
}