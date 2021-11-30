package com.softserve.todoapp.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabItem
import com.softserve.todoapp.R
import com.softserve.todoapp.adapter.OnClickTaskAdapter
import com.softserve.todoapp.adapter.TaskListAdapter
import com.softserve.todoapp.database.TaskDaoManager
import com.softserve.todoapp.model.Task

class CompletedTask: AppCompatActivity(), OnClickTaskAdapter {

    val taskDaoManager: TaskDaoManager = TaskDaoManager()
    lateinit var adapter: TaskListAdapter
    lateinit var recycler: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_completed_tasks)
        setUpAdapter()
        val tabButton: TabItem = findViewById(R.id.in_progress_tab)
        tabButton.setOnClickListener{
            goToMainActivity()
        }
    }
    private fun setUpAdapter(){
        recycler = findViewById(R.id.listOfTasks)
        recycler.layoutManager = LinearLayoutManager(this)
        adapter = TaskListAdapter(taskDaoManager.getCompletedTasks(), this)
        recycler.adapter = adapter

    }
    private fun goToMainActivity(){
        val intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    override fun onClickSubmitCheckBox(task: Task, positionOfTask: Int) {
        TODO("Not yet implemented")
    }

    override fun onClickEditButton(id: Int) {
        TODO("Not yet implemented")
    }
}