package com.softserve.todoapp.adapter

import android.app.Application
import android.content.Intent
import android.graphics.Typeface.create
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.softserve.todoapp.R
import com.softserve.todoapp.activity.NewTaskActivity
import com.softserve.todoapp.model.Task
import org.w3c.dom.Text
import java.util.zip.Inflater
import kotlin.properties.Delegates

class TaskListAdapter(private var taskList: List<Task>, private var onClickTask: OnClickTaskAdapter):
    RecyclerView.Adapter<TaskListAdapter.TaskHolder>() {
    class TaskHolder(view: View): RecyclerView.ViewHolder(view){
        var title: TextView ?= null
        var task: TextView ?= null
        var dateCreation: TextView ?= null
        var submitCheckBox: CheckBox ?= null
        var priorityColor: AppCompatImageView ?= null
        var editButton: AppCompatImageView ?= null
                init {
                    title = view.findViewById(R.id.title_content)
                    task = view.findViewById(R.id.task_content)
                    dateCreation = view.findViewById(R.id.date_creation)
                    submitCheckBox = view.findViewById(R.id.submit_check_box)
                    priorityColor = view.findViewById(R.id.priority_color)
                    editButton = view.findViewById(R.id.edit_button)
                }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListAdapter.TaskHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.acticity_item_of_list, parent, false)
    return TaskHolder(view)
    }

    override fun onBindViewHolder(holder: TaskListAdapter.TaskHolder, position: Int) {
        val taskListItem = taskList[position]
        holder.title?.text = taskListItem.title
        holder.task?.text = taskListItem.task
        holder.dateCreation?.text = taskListItem.dateCreation
        holder.priorityColor?.setBackgroundResource (
            when(taskListItem.priority){
                1 -> R.color.green
                2 -> R.color.yellow
                else -> R.color.red
            }
        )
        holder.submitCheckBox?.setOnClickListener {
            onClickTask.onClickSubmitCheckBox(taskListItem, position)
        }
        holder.editButton?.setOnClickListener{
            Log.e("TaskAdapter", taskListItem.id.toString())
            onClickTask.onClickEditButton(taskListItem.id)
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }


}