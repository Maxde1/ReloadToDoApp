package com.softserve.todoapp.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log

import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.softserve.todoapp.R
import com.softserve.todoapp.database.TaskDaoImpl
import com.softserve.todoapp.database.TaskDaoManager
import com.softserve.todoapp.model.Task
import java.util.*
import android.widget.Toast


class NewTaskActivity : AppCompatActivity() {
    private val taskDaoManager = TaskDaoManager()

    lateinit var addButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_task)


        // Create spinner
        val spinner: Spinner = findViewById(R.id.priority_spinner)
        createSpinner(spinner)

        //add button listener
        addButton = findViewById(R.id.add_button)
        addButton.setOnClickListener {
            addNewTask()
        }

    }


    private fun createSpinner(spinner: Spinner) {
        ArrayAdapter.createFromResource(
            this,
            R.array.priority_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }
    //This shouldn't do this class


    private fun addNewTask() {
        val title: TextInputEditText = findViewById(R.id.title_input)
        val taskContent: TextInputEditText = findViewById(R.id.task_content_input)
        val priority: Spinner = findViewById(R.id.priority_spinner)
        //check if fields aren't empty
        if (taskDaoManager.isValidTask(
                title.text.toString(),
                taskContent.text.toString()
            )
        )
        {
            taskDaoManager.addTask(
                title.text.toString(),
                taskContent.text.toString(),
                priority.selectedItem.toString(),
                Date()
            )
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            val myToast = Toast.makeText(this, "Pleas fill all required fields", Toast.LENGTH_LONG)
            myToast.show()
        }


    }

}