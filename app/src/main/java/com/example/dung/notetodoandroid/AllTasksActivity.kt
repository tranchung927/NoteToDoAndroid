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

class AllTasksActivity : AppCompatActivity(), RecyclerAdapterInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_tasks)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = RecyclerAdapter(applicationContext, IntArray(100, { i -> i + 1 }).toMutableList(), this)
    }

    override fun didSelected(item: View, position: Int) {
        println(position)
    }
}