package com.example.dung.notetodoandroid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_task_description.*

class TaskDescriptionActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_description)
        val message = intent.getStringExtra(AllTasksActivity.PASSING_NUMBER)
        message.let {
            editText.setText(it, TextView.BufferType.EDITABLE)
        }
    }
}