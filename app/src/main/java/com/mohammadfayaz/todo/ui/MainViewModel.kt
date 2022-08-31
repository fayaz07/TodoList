package com.mohammadfayaz.todo.ui

import android.util.Log
import androidx.lifecycle.*
import com.mohammadfayaz.todo.data.NewTodo
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

    fun insertTask(task: String) {
        viewModelScope.launch {
            todoDao.insert(NewTodo(task))
        }
    }

    override fun onCleared() {
        Log.d("test", "viewmodel destroyed")
        super.onCleared()
    }
}