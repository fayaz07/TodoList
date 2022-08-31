package com.mohammadfayaz.todo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mohammadfayaz.todo.R
import com.mohammadfayaz.todo.data.DbService
import com.mohammadfayaz.todo.data.TodoDao
import com.mohammadfayaz.todo.data.TodoDb
import com.mohammadfayaz.todo.service.MViewModelFactory
import com.mohammadfayaz.todo.ui.adapter.TodoAdapter
import com.mohammadfayaz.todo.ui.create.CreateTodoActivity
import kotlinx.coroutines.launch

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
            startActivity(Intent(this, CreateTodoActivity::class.java))
        }

        refreshList()
    }

    private fun refreshList() {
        viewModel.getTodos().observe(this) {
            adapter.list = it
        }
    }
}
