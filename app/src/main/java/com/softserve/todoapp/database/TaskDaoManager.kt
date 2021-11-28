package com.softserve.todoapp.database

import android.util.Log
import com.softserve.todoapp.model.Task
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class TaskDaoManager {
    private val  taskDao = TaskDaoImpl()
    fun removeTask(task: Task){
        taskDao.delete(task)
    }
    fun getItems(): List<Task>{
        return taskDao.getItems()
    }
    fun addTask(title: String, taskContent: String, priority: String, currentDate: Date){
        taskDao.insertTask(createTask(title, taskContent, priority, currentDate))
    }
    private fun formatDate(currentDate: Date): String{
        val formatter = SimpleDateFormat("MMM dd yyyy")
        return formatter.format(currentDate)
    }
    private fun createTask(title: String, taskContent: String, priority: String, currentDate: Date): Task{
        return Task(
            title = title,
            task = taskContent,
            priority = getPriority(priority),
            dateCreation = formatDate(currentDate)
        )
    }
    private fun getPriority(priority: String): Int{
        return when(priority){
            "High" -> 3
            "Medium" -> 2
            else -> 1
        }
    }
    fun isValidTask(title: String, taskContent: String): Boolean{
        return title.isNotBlank() && taskContent.isNotBlank()
    }

}