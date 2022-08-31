package com.mohammadfayaz.todo.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mohammadfayaz.todo.R
import com.mohammadfayaz.todo.data.DbService
import com.mohammadfayaz.todo.data.TodoDao
import com.mohammadfayaz.todo.service.MViewModelFactory
import com.mohammadfayaz.todo.ui.adapter.TodoAdapter
import com.mohammadfayaz.todo.ui.create.CreateTodoActivity

class MainActivity : AppCompatActivity() {

  lateinit var recyclerView: RecyclerView
  lateinit var adapter: TodoAdapter
  lateinit var fab: FloatingActionButton
  lateinit var viewModel: MainViewModel

  val todoDao: TodoDao by lazy { DbService.todoDao(this.applicationContext) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    viewModel =
      ViewModelProvider(this, MViewModelFactory(todoDao)).get(MainViewModel::class.java)

    adapter = TodoAdapter { id, updatedStatus -> viewModel.toggleTodoStatus(id, updatedStatus) }

    recyclerView = findViewById(R.id.recyclerView)
    recyclerView.adapter = adapter

    fab = findViewById(R.id.floatingActionButton)

    fab.setOnClickListener {
      startActivityForResult(Intent(this, CreateTodoActivity::class.java), 1)
    }

    viewModel.getTodos().observe(this) {
      adapter.list = it
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == 1 && resultCode == RESULT_OK) {
      viewModel.insertTask(data?.extras?.getString("task") ?: "")
    }
  }
}
