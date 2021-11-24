package com.softserve.todoapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.softserve.todoapp.R
import com.softserve.todoapp.adapter.TaskListAdapter
import com.softserve.todoapp.database.TaskDaoImpl
import com.softserve.todoapp.model.Task

class MainActivity : AppCompatActivity() {
    lateinit var adapter: TaskListAdapter
    lateinit var recycler: RecyclerView
    var database: TaskDaoImpl = TaskDaoImpl()

    //view of create button
    lateinit var createButton: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler = findViewById(R.id.listOfTasks)
        recycler.layoutManager = LinearLayoutManager(this)
        adapter = TaskListAdapter(createData())
        recycler.adapter = adapter
        createButton = findViewById(R.id.floatingActionButton)

        createButton.setOnClickListener{
            val intent= Intent(this, NewTaskActivity::class.java)
            startActivity(intent)
        }

    }

    fun createData(): List<Task>{
        return listOf(
            Task("sometext", "todo", "121414"),
            Task("sometext", "todo", "121414"),
            Task("sometext", "todo", "121414"),
            Task("sometext", "todo", "121414"),
            Task("sometext", "todo", "121414"),
            Task("sometext", "todo", "121414"),
            Task("sometext", "todo", "121414"),
            Task("sometext", "todo", "121414")
        )
    }

}