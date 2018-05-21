package com.example.dung.notetodoandroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_all_tasks.*

class AllTasksActivity : AppCompatActivity(), RecyclerAdapterInterface {

    private var numbers = IntArray(100, { i -> i + 1 }).toMutableList()

    companion object {
        val PASSING_NUMBER = "passing number"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_tasks)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = RecyclerAdapter(applicationContext, numbers, this)
    }

    override fun didSelected(item: View, position: Int) {
        val intent = Intent(this, TaskDescriptionActivity::class.java).apply {
            putExtra(PASSING_NUMBER, numbers[position].toString())
        }
        startActivity(intent)
    }
}