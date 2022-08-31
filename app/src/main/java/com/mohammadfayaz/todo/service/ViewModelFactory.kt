package com.mohammadfayaz.todo.service

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohammadfayaz.todo.data.TodoDao
import com.mohammadfayaz.todo.ui.MainViewModel

class MViewModelFactory(private val todoDao: TodoDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when(modelClass) {
            MainViewModel::class.java -> {
                MainViewModel(todoDao) as T
            }
            else -> {
                throw IllegalStateException()
            }
        }

    }
}