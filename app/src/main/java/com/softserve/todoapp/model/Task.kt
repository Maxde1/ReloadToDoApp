package com.softserve.todoapp.model

data class Task(
    var id: Int = addId(),
    var title: String,
    var task: String,
    var dateCreation: String,
    var priority: Int = 0,
) {
    companion object{
        ////Ahhahha man you are so funny
        var idGenerator: Int = -1
        fun addId(): Int{
            idGenerator +=1
            return idGenerator
        }
    }

}