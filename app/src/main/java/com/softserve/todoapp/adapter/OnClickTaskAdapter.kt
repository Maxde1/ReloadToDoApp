package com.softserve.todoapp.adapter

import com.softserve.todoapp.model.Task

interface OnClickTaskAdapter {
    fun onClickDeleteButton(task: Task, positionOfTask: Int)

    fun onClickSubmitCheckBox(task: Task, positionOfTask: Int)

    fun onClickEditButton(id: Int)

    fun onClickTaskField(task: String, title: String)
}