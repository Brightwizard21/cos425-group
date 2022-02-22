package com.example.todolist

class clickListener (val clickListener: (task: Task) -> Unit){
    fun onClick(task: Task) {
        return clickListener(task)
    }
}