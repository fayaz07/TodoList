package com.mohammadfayaz.todo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class TodoModel(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val title: String,
    var completed: Boolean
)

fun NewTodo(task: String) = TodoModel(title = task, completed = false)
