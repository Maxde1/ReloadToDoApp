package com.softserve.todoapp.database

import com.softserve.todoapp.model.Task
import java.lang.Exception

class TaskDaoImpl: TaskDao {
    companion object{
        var listOfTasks: MutableList<Task> = mutableListOf()
    }

    override fun getItems(): List<Task> {
        return listOfTasks
    }

    override fun getItem(id: Int): Task {
        for (i in listOfTasks){
            if (i.id == id){
                return i
            }
        }
        throw Exception("Item not found")
    }

    override fun insertTask(task: Task) {
        listOfTasks.add(task)
    }

    override fun update(task: Task) {
        listOfTasks[listOfTasks.indexOf(task)] = task
    }

    override fun delete(task: Task) {
        listOfTasks.remove(task)
    }
}