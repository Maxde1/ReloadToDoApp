package com.softserve.todoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.softserve.todoapp.R
import com.softserve.todoapp.model.Task
import org.w3c.dom.Text
import java.util.zip.Inflater

class TaskListAdapter(private var taskList: List<Task>): RecyclerView.Adapter<TaskListAdapter.TaskHolder>() {
    class TaskHolder(view: View): RecyclerView.ViewHolder(view){
        var title: TextView ?= null
        var task: TextView ?= null
        var dateCreation: TextView ?= null
                init {
                    title = view.findViewById(R.id.title_content)
                    task = view.findViewById(R.id.task_content)
                    dateCreation = view.findViewById(R.id.date_creation)
                }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListAdapter.TaskHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.acticity_item_of_list, parent, false)
    return TaskHolder(view)
    }

    override fun onBindViewHolder(holder: TaskListAdapter.TaskHolder, position: Int) {
        var taskListItem = taskList[position]
        holder.title?.text = taskListItem.title
        holder.task?.text = taskListItem.task
        holder.dateCreation?.text = taskListItem.dateCreation

    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}