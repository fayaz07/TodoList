package com.mohammadfayaz.todo.ui.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.mohammadfayaz.todo.R
import com.mohammadfayaz.todo.data.DbService
import com.mohammadfayaz.todo.data.TodoDao
import com.mohammadfayaz.todo.service.MViewModelFactory
import com.mohammadfayaz.todo.ui.MainViewModel

class CreateTodoActivity : AppCompatActivity() {

  lateinit var taskField: EditText
  lateinit var saveButton: Button

  lateinit var viewModel: MainViewModel
  val todoDao: TodoDao by lazy { DbService.todoDao(this.applicationContext) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_create_todo)

    supportActionBar?.title = "Add Todo"

    viewModel = ViewModelProvider(this, MViewModelFactory(todoDao)).get(MainViewModel::class.java)

    taskField = findViewById(R.id.newTodoTextField)
    saveButton = findViewById(R.id.saveTodoButton)

    saveButton.setOnClickListener {
        viewModel.insertTask(taskField.text.toString())
        finish()
    }
  }
}