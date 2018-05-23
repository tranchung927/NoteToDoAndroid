package com.example.dung.notetodoandroid

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_all_tasks.*

class AllTasksActivity : BaseActivity(), RecyclerAdapterInterface {

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

        val swipeHandler = object : RecyclerItemTouchHelper(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = recyclerView.adapter as RecyclerAdapter
                numbers.removeAt(viewHolder.adapterPosition)
                adapter.notifyDataSetChanged()
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_graph, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_add -> {
                addNewNumber()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addNewNumber() {
        if (adapter.positionForSelected != null) {
            adapter.positionForSelected = null
        }
        val intent = Intent(this, TaskDescriptionActivity::class.java)
        startActivityForResult(intent, REQUSET_CODE)
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
                    if (adapter.positionForSelected != null) {
                        numbers.set(adapter.positionForSelected!!, number)
                    } else {
                        numbers.add(number)
                    }
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }
}
