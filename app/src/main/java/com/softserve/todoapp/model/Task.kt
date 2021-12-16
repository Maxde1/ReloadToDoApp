package com.softserve.todoapp.model

import java.util.*

data class Task(
    var id: Int = addId(),
    var title: String,
    var task: String,
    var dateCreation: Date,
    var priority: Int = 0,
    var completion: Boolean = false
) {
    companion object{
        var idGenerator: Int = -1
        fun addId(): Int{
            idGenerator +=1
            return idGenerator
        }
    }

}