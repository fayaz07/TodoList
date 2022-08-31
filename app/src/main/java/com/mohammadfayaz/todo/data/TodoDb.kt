package com.mohammadfayaz.todo.data

object TodoDb {
    private val list: MutableList<TodoModel> = mutableListOf()

    fun add(task: String) {
        list.add(TodoModel(list.size + 1, task, false))
    }

    fun get() = list

    fun toggleTodoStatus(id: Int) {
        list[id-1].completed = !list[id-1].completed
    }
}