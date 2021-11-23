package com.softserve.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.softserve.todoapp.adapter.TaskListAdapter
import com.softserve.todoapp.model.Task

class MainActivity : AppCompatActivity() {
    lateinit var adapter: TaskListAdapter
    lateinit var recycler: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler = findViewById(R.id.listOfTasks)
        recycler.layoutManager = LinearLayoutManager(this)
        adapter = TaskListAdapter(createData())
        recycler.adapter = adapter
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