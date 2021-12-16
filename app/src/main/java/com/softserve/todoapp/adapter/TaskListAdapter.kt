package com.softserve.todoapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.softserve.todoapp.R
import com.softserve.todoapp.model.Task
import java.text.SimpleDateFormat
import java.util.*

class TaskListAdapter(private var taskList: List<Task>, private var onClickTask: OnClickTaskAdapter):
    RecyclerView.Adapter<TaskListAdapter.TaskHolder>() {

    val sortedList = taskList.sortedBy { it.completion }
    class TaskHolder(view: View): RecyclerView.ViewHolder(view){
        var title: TextView ?= null
        var task: TextView ?= null
        var dateCreation: TextView ?= null
        var submitCheckBox: CheckBox ?= null
        var priorityColor: AppCompatImageView ?= null
        var editButton: AppCompatImageView ?= null
        var taskContentField: TextView ?= null
        var deleteButton: AppCompatImageView ?= null
                init {
                    title = view.findViewById(R.id.title_content)
                    task = view.findViewById(R.id.task_content)
                    dateCreation = view.findViewById(R.id.date_creation)
                    submitCheckBox = view.findViewById(R.id.submit_check_box)
                    priorityColor = view.findViewById(R.id.priority_color)
                    editButton = view.findViewById(R.id.edit_button)
                    taskContentField = view.findViewById(R.id.task_content)
                    deleteButton = view.findViewById(R.id.delete_button)
                }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListAdapter.TaskHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.acticity_item_of_list, parent, false)
    return TaskHolder(view)
    }

    override fun onBindViewHolder(holder: TaskListAdapter.TaskHolder, position: Int) {
        val taskListItem = sortedList[position]
        holder.title?.text = taskListItem.title
        holder.task?.text = formatViewOfTask(taskListItem.task)
        holder.dateCreation?.text = formatDate(taskListItem.dateCreation)
        holder.priorityColor?.setBackgroundResource (
            when(taskListItem.priority){
                1 -> R.color.green
                2 -> R.color.yellow
                else -> R.color.red
            }
        )
        if (taskListItem.completion==true){
            formatCompletedTask(holder)
        }
        holder.submitCheckBox?.setOnClickListener {
            taskListItem.completion=true
            formatCompletedTask(holder)
            onClickTask.onClickSubmitCheckBox(taskListItem, position)
        }
        holder.editButton?.setOnClickListener{
            onClickTask.onClickEditButton(taskListItem.id)
        }
        holder.taskContentField?.setOnClickListener{
            onClickTask.onClickTaskField(taskListItem.task, taskListItem.title)
        }
        holder.deleteButton?.setOnClickListener{
            onClickTask.onClickDeleteButton(taskListItem, position)
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
    private fun formatViewOfTask(taskText: String): String{
        //Add three dots if string has more that 35 symbols
        if (taskText.length >= 35){
            return taskText.substring(0, 35)+"..."
        }
        return taskText
    }

    private fun formatDate(currentDate: Date): String{
        val formatter = SimpleDateFormat("MMM dd yyyy")
        return formatter.format(currentDate)
    }
    private fun formatCompletedTask(holder: TaskHolder){
        holder?.let {
            it.deleteButton?.isVisible = true
        }
        holder?.let {
            it.submitCheckBox?.isVisible = false
        }
        holder?.let{
            it.priorityColor?.isVisible = false
        }
    }

}