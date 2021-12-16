package com.softserve.todoapp.activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.softserve.todoapp.R
import com.softserve.todoapp.const.Keys
import com.softserve.todoapp.database.TaskDaoManager
import com.softserve.todoapp.model.Task

class EditTaskActivity: AppCompatActivity() {
    val taskDaoManager: TaskDaoManager = TaskDaoManager()
    lateinit var editedTask: Task
    lateinit var saveButton: FloatingActionButton
    lateinit var cancelButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activoty_edit_task)
        supportActionBar?.hide()
        //
        val spinner: Spinner = findViewById(R.id.priority_spinner_edit)
        createSpinner(spinner)
        //get task by id
        val intent: Intent = intent
        val id: Int = intent.getIntExtra(Keys.TASK_ID.name, 0)
        editedTask = taskDaoManager.getTaskById(id)
        setDataInFiled()
        //Save button action
        saveButton = findViewById(R.id.save_button)
        saveButton.setOnClickListener{
            saveChanges()
        }
        //Cancel button action
        cancelButton = findViewById(R.id.cancel_button_edit_task)
        cancelButton.setOnClickListener{
            goToMainActivity()
        }

    }
    private fun setDataInFiled(){
        val title: TextInputEditText = findViewById(R.id.title_input_edit)
        val taskContent: TextInputEditText = findViewById(R.id.task_content_input_edit)
        val priorityContent: Spinner = findViewById(R.id.priority_spinner_edit)
        title.setText(editedTask.title)
        taskContent.setText(editedTask.task)
        priorityContent.setSelection(editedTask.priority-1)
    }

    fun saveChanges(){
        updateTask()
        goToMainActivity()
    }

    private fun updateTask(){
        val title: TextInputEditText = findViewById(R.id.title_input_edit)
        val taskContent: TextInputEditText = findViewById(R.id.task_content_input_edit)
        val priorityContent: Spinner = findViewById(R.id.priority_spinner_edit)
        taskDaoManager.updateTask(title.text.toString(),
            taskContent.text.toString(),
            priorityContent.selectedItem.toString(),
            editedTask)
    }

    private fun createSpinner(spinner: Spinner) {
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.priority_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    private fun goToMainActivity(){
        val intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}