package com.example.todolist.placeholder

import com.example.todolist.Task
import java.text.SimpleDateFormat
import java.util.*

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object PlaceholderContent {

    val ITEMS: MutableList<Task> = ArrayList()

    val ITEM_MAP: MutableMap<String, Task> = HashMap()

    private val COUNT = 25

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addItem(createPlaceholderItem(i))
        }
    }

    fun getTask(taskId: Int) : Task? {
        return ITEMS.find { task -> taskId.equals(task.id) }
    }
    fun removeTask(taskId: Int) {
        ITEMS.removeAll { task -> taskId.equals(task.id) }
    }

    private fun addItem(item: Task) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id.toString(), item)
    }

    private fun createPlaceholderItem(position: Int): Task {
        val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd")
        val dueDate = simpleDateFormat.parse("2022/2/" + (1..28).random())
        return Task(position, "Task " + position, dueDate, false, "")
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

}