package com.softserve.todoapp.activity

import android.app.Activity
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.util.Log
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.softserve.todoapp.R
import com.softserve.todoapp.database.TaskDaoImpl
import com.softserve.todoapp.database.TaskDaoManager
import com.softserve.todoapp.model.Task
import java.util.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NewTaskActivity : AppCompatActivity() {
    private val taskDaoManager = TaskDaoManager()
    lateinit var addButton: FloatingActionButton
    lateinit var cancelButton: FloatingActionButton
    lateinit var title: TextInputEditText
    lateinit var alarmButton: ImageButton
    lateinit var timeOutput: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_task)
        supportActionBar?.hide()
        // Create spinner
        val spinner: Spinner = findViewById(R.id.priority_spinner)
        createSpinner(spinner)
        //add button listener
        addButton = findViewById(R.id.add_button)
        addButton.setOnClickListener {
            addNewTask()
        }
        //cancel button listener

        cancelButton = findViewById(R.id.cancel_button_new_task)
        cancelButton.setOnClickListener{
            cancelCreateTask()
        }
        //set alarm
        timeOutput = findViewById(R.id.time_output)
        val mTimePicker: TimePickerDialog
        val mcurrentTime = Calendar.getInstance()
        val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
        val minute = mcurrentTime.get(Calendar.MINUTE)

        mTimePicker = setUpTimePicker(hour, minute)
        alarmButton = findViewById(R.id.alarm)
        alarmButton.setOnClickListener {
                mTimePicker.show()
        }
    }
    private fun setUpTimePicker(hour: Int, minute: Int): TimePickerDialog{
        return TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener {
            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                timeOutput.setText(String.format("%d : %02d", hourOfDay, minute))
            }
        }, hour, minute, false)
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

    private fun addNewTask() {
        title = findViewById(R.id.title_input)
        val taskContent: TextInputEditText = findViewById(R.id.task_content_input)
        val priority: Spinner = findViewById(R.id.priority_spinner)
        //check if fields aren't empty
        val valid = taskDaoManager.isValidTask(
            title.text.toString(),
            taskContent.text.toString())
        if (!valid) {
            val myToast = Toast.makeText(this, "Pleas fill all required fields", Toast.LENGTH_LONG)
            myToast.show()
            return
        }
        taskDaoManager.addTask(
            title.text.toString(),
            taskContent.text.toString(),
            priority.selectedItem as String,
            Date()
        )
        setRemainder()
        val intent2 = Intent(this, MainActivity::class.java)
        startActivity(intent2)
    }

    private fun setRemainder() {
//        val arrOfTime = timeOutput.text.toString().split(":")
//        Log.e("errrrrr",arrOfTime.toString())
//        if (arrOfTime[0].isBlank()){
//            return
//        }
//        val intent = Intent(AlarmClock.ACTION_SET_ALARM)
//        intent.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
//        intent.putExtra(AlarmClock.EXTRA_HOUR, arrOfTime[0].trim().toInt())
//        intent.putExtra(AlarmClock.EXTRA_MINUTES, arrOfTime[1].trim().toInt())
//        intent.putExtra(AlarmClock.EXTRA_MESSAGE, title.text.toString())
//
//        startActivity(intent)
//        Log.e("2", "Not some")

    }

    private fun cancelCreateTask(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}