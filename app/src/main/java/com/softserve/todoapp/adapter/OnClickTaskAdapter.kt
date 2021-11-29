package com.softserve.todoapp.adapter

import com.softserve.todoapp.model.Task

interface OnClickTaskAdapter {
    fun onClickSubmitCheckBox(task: Task, positionOfTask: Int)
    fun onClickEditButton(id: Int)
}