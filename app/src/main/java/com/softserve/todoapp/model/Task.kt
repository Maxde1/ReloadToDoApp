package com.softserve.todoapp.model

data class Task(
    var title: String,
    var task: String,
    var dateCreation: String,
    var priority: Int = 1
) {

}