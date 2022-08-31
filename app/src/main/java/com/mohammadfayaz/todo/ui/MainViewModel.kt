package com.mohammadfayaz.todo.ui

import android.util.Log
import androidx.lifecycle.*
import com.mohammadfayaz.todo.data.TodoDao
import com.mohammadfayaz.todo.data.TodoModel
import kotlinx.coroutines.launch

class MainViewModel(private val todoDao: TodoDao) : ViewModel() {

    fun getTodos() = todoDao.getList().asLiveData()

    fun toggleTodoStatus(id: Int, updatedStatus: Boolean) {
        viewModelScope.launch {
            todoDao.toggleTodo(id, updatedStatus)
        }
    }

    override fun onCleared() {
        Log.d("test", "viewmodel destroyed")
        super.onCleared()
    }
}