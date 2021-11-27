package com.softserve.todoapp.adapter

import android.app.Application
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.softserve.todoapp.R
import com.softserve.todoapp.model.Task
import org.w3c.dom.Text
import java.util.zip.Inflater
import kotlin.properties.Delegates

class TaskListAdapter(private var taskList: List<Task>, private var onClickTask: OnClickTaskAdapter): RecyclerView.Adapter<TaskListAdapter.TaskHolder>() {
    class TaskHolder(view: View): RecyclerView.ViewHolder(view){
        var title: TextView ?= null
        var task: TextView ?= null
        var dateCreation: TextView ?= null
        var submitCheckBox: CheckBox ?= null

                init {
                    title = view.findViewById(R.id.title_content)
                    task = view.findViewById(R.id.task_content)
                    dateCreation = view.findViewById(R.id.date_creation)
                    submitCheckBox = view.findViewById(R.id.submit_check_box)
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

        holder.submitCheckBox?.setOnClickListener {
            onClickTask.onClickSubmitCheckBox(taskListItem, position)
        }

    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}