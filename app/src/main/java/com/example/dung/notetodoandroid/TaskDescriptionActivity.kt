package com.example.dung.notetodoandroid

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager

import kotlinx.android.synthetic.main.activity_task_description.*


class TaskDescriptionActivity: AppCompatActivity() {

    companion object {
        val CALL_BACK = "call back"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_description)

        setSupportActionBar(findViewById(R.id.tool_bar_description))

        if (supportActionBar != null) {
            supportActionBar!!.setTitle("")
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }

        val message = intent.getStringExtra(AllTasksActivity.PASSING_NUMBER)
        message.let {
            editText.setText(it)
        }

        editText.setOnKeyListener(View.OnKeyListener { v, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                //Perform Code
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
                val numberText = editText.text.toString()
                if (!numberText.isEmpty()) {
                    val result = Intent()
                    result.putExtra(CALL_BACK, numberText)
                    setResult(Activity.RESULT_OK, result)
                    finish()
                }
                return@OnKeyListener true
            }
            false
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
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