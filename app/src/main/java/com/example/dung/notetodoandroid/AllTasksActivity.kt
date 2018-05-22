package com.example.dung.notetodoandroid

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_all_tasks.*

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
                    numbers.add(number)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out)
    }

    override fun startActivityForResult(intent: Intent?, requestCode: Int) {
        super.startActivityForResult(intent, requestCode)
        overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.from_left_in, R.anim.from_right_out)
    }
}
