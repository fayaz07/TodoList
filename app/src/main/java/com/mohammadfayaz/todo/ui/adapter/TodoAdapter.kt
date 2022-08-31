package com.mohammadfayaz.todo.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mohammadfayaz.todo.R
import com.mohammadfayaz.todo.data.TodoModel

class TodoAdapter(private val onClick: (itemId: Int) -> Unit) : RecyclerView.Adapter<TodoViewHolder>() {
    var list: List<TodoModel> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_todo_item, parent, false)
        return TodoViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val item = list[position]
        holder.todoTitle.text = item.title
        holder.todoCheckBox.isChecked = item.completed

        holder.itemView.rootView.setOnClickListener {
            onClick(item.id)
        }
    }

    override fun getItemCount() = list.size

}

class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView.rootView) {
    val todoTitle: TextView = itemView.findViewById(R.id.todoTitle)
    val todoCheckBox: CheckBox = itemView.findViewById(R.id.todoCheckBox)
}