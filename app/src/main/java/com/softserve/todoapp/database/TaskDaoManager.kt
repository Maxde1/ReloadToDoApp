package com.softserve.todoapp.database

import com.softserve.todoapp.model.Task
import java.text.SimpleDateFormat
import java.util.*

class TaskDaoManager {
    private val  taskDao = TaskDaoImpl()

    fun getCompletedTasks():List<Task>{
        return taskDao.getCompletedItems()
    }

    fun getTaskById(id: Int): Task{
        return taskDao.getItem(id)
    }
    fun removeTask(task: Task){
        taskDao.delete(task)
    }
    fun getItems(): List<Task>{
        return taskDao.getItems()
    }
    fun updateTask(title: String, taskContent: String, priority: String, editedTask: Task){
        taskDao.update(createUpdatedTask(title, taskContent, priority, editedTask))
    }
    fun addTask(title: String, taskContent: String, priority: String, currentDate: Date){
        taskDao.insertTask(createNewTask(title, taskContent, priority, currentDate))
    }
    //Fix this. It should be in TaskListAdapter when you
    // display this info in database you should save only data
    private fun formatDate(currentDate: Date): String{
        val formatter = SimpleDateFormat("MMM dd yyyy")
        return formatter.format(currentDate)
    }
    private fun createNewTask(title: String, taskContent: String, priority: String, currentDate: Date): Task{
        return Task(
            title = title,
            task = taskContent,
            priority = getPriority(priority),
            dateCreation = formatDate(currentDate)
        )
    }

    private fun createUpdatedTask(title: String, taskContent: String, priority: String, editedTask: Task):Task{
        editedTask.title = title
        editedTask.task = taskContent
        editedTask.priority = getPriority(priority)
        return editedTask
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