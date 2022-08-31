package com.mohammadfayaz.todo.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TodoModel::class], version = 1, exportSchema = false)
abstract class TodoDb: RoomDatabase() {

    abstract fun todoDao(): TodoDao

//    private val list: MutableList<TodoModel> = mutableListOf()
//
//    fun add(task: String) {
//        list.add(TodoModel(list.size + 1, task, false))
//    }
//
//    fun get() = list
//
//    fun toggleTodoStatus(id: Int) {
//        list[id-1].completed = !list[id-1].completed
//    }
}