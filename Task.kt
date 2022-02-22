package com.example.todolist

import java.util.*

data class Task (
    val id: Int,
    var content: String?,
    var duedate: Date?,
    var complete: Boolean = false,
    var details: String?
        ){
}