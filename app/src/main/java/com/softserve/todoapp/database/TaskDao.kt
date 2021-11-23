package com.softserve.todoapp.database

import com.softserve.todoapp.model.Task

interface TaskDao {

    fun getItems(): List<Task>

    fun getItem(id: Int): Task

    fun insertTask(task: Task)

    fun update(task: Task)

    fun delete(task: Task)

}