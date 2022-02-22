package com.example.todolist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView

import com.example.todolist.databinding.FragmentItemBinding

import java.text.SimpleDateFormat

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MytodolistRecyclerViewAdapter(
    private val values: List<Task>,
    private val onClickListener: clickListener
) : RecyclerView.Adapter<MytodolistRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    fun itemAt(position: Int ) : Task? {
        return values[position]
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        holder.title.text = item.content
        val simpleDateFormat = SimpleDateFormat("MM/dd/yyyy")
        holder.subtitle.text = simpleDateFormat.format(item.duedate)
        holder.check.isChecked = item.complete
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val title: TextView = binding.Tasktitle
        val subtitle: TextView = binding.Tasksubtitle
        val check: CheckBox = binding.cbComplete


        override fun toString(): String {
            return super.toString() + " '" + subtitle.text + "'"
        }
    }

}