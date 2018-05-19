package com.example.dung.notetodoandroid

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_all_tasks.*
import kotlinx.android.synthetic.main.cell.view.*

class AllTasksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_tasks)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = ListAdapter(applicationContext, IntArray(100, { i -> i + 1 }).toMutableList())
    }
}

class ListAdapter(var c: Context, var lists: MutableList<Int>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(R.layout.cell, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bindData(lists[position].toString())
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindData(_list: String) {
            itemView.textLabel.text = _list
        }
    }
}