package com.softserve.todoapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabItem
import com.softserve.todoapp.R
import com.softserve.todoapp.adapter.OnClickTaskAdapter
import com.softserve.todoapp.adapter.TaskListAdapter
import com.softserve.todoapp.database.TaskDaoImpl
import com.softserve.todoapp.database.TaskDaoManager
import com.softserve.todoapp.model.Task

class MainActivity : AppCompatActivity(), OnClickTaskAdapter {
    lateinit var adapter: TaskListAdapter
    lateinit var recycler: RecyclerView
    //--
    //var database: TaskDaoImpl = TaskDaoImpl()
    val taskDaoManager: TaskDaoManager = TaskDaoManager()

    //view of create button
    lateinit var createButton: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        setUpAdapter()

        //Create new task
        createButton = findViewById(R.id.floatingActionButton)
        createButton.setOnClickListener{
            val intent= Intent(this, NewTaskActivity::class.java)
            startActivity(intent)
        }
        //Tabs button
//        val tabButton: TabItem = findViewById(R.id.completed_tasks_main)
//        tabButton.setOnClickListener{
//            goToCompletedTaskActivity()
//        }

    }
    override fun onResume() {
        super.onResume()
        adapter.notifyItemInserted(taskDaoManager.getItems().size-1)
    }

    private fun setUpAdapter(){
        recycler = findViewById(R.id.listOfTasks)
        recycler.layoutManager = LinearLayoutManager(this)
        adapter = TaskListAdapter(taskDaoManager.getItems(), this)
        recycler.adapter = adapter

    }

    override fun onClickSubmitCheckBox(task: Task, positionOfTask: Int) {
        taskDaoManager.removeTask(task)
        adapter.notifyItemRemoved(positionOfTask)
    }

    override fun onClickEditButton(id: Int) {
        val intent: Intent = Intent(this, EditTaskActivity::class.java)
        intent.putExtra("taskId", id)
        startActivity(intent)
    }
    private fun goToCompletedTaskActivity(){
        val intent: Intent = Intent(this, CompletedTask::class.java)
        startActivity(intent)
    }


}