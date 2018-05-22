package com.example.dung.notetodoandroid

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
import kotlinx.android.synthetic.main.activity_all_tasks.*
import kotlinx.android.synthetic.main.tool_bar.*

class AllTasksActivity : AppCompatActivity(), RecyclerAdapterInterface {

    private var numbers = IntArray(100, { i -> i + 1 }).toMutableList()

    private val adapter by lazy { RecyclerAdapter(applicationContext, numbers, this) }
    private val REQUSET_CODE = 1

    companion object {
        val PASSING_NUMBER = "passing number"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_tasks)
        val toolbar: Toolbar = findViewById(R.id.toolBarAllTask)
        setSupportActionBar(toolbar)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }

    override fun didSelected(item: View, position: Int) {
        val intent = Intent(this, TaskDescriptionActivity::class.java).apply {
            putExtra(PASSING_NUMBER, numbers[position].toString())
        }
        startActivityForResult(intent, REQUSET_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUSET_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val number = data?.getStringExtra(TaskDescriptionActivity.CALL_BACK).toString().toIntOrNull()
                if (number != null) {
                    numbers.add(number)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }
}
