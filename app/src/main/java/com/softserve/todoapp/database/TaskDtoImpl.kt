package com.softserve.todoapp.database

import com.softserve.todoapp.model.Task

class TaskDtoImpl: TaskDao {
    var listOfTasks: MutableList<Task> = mutableListOf()

    override fun getItems(): List<Task> {
        return listOfTasks
    }

    override fun getItem(id: Int): Task {
        return listOfTasks[id]
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