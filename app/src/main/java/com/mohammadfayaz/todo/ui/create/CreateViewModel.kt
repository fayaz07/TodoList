package com.mohammadfayaz.todo.ui.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohammadfayaz.todo.data.NewTodo
import com.mohammadfayaz.todo.data.TodoDao
import kotlinx.coroutines.launch

class CreateViewModel(private val todoDao: TodoDao) : ViewModel() {

    fun create(task: String) {
        viewModelScope.launch {
            todoDao.insert(NewTodo(task))
        }
    }
}