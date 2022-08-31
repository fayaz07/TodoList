package com.mohammadfayaz.todo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mohammadfayaz.todo.R
import com.mohammadfayaz.todo.data.TodoDb
import com.mohammadfayaz.todo.ui.adapter.TodoAdapter
import com.mohammadfayaz.todo.ui.create.CreateTodoActivity

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: TodoAdapter
    lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = TodoAdapter { id->
            TodoDb.toggleTodoStatus(id)
            refreshList()
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter

        refreshList()

        fab = findViewById(R.id.floatingActionButton)

        fab.setOnClickListener {
            startActivity(Intent(this, CreateTodoActivity::class.java))
        }
    }

    private fun refreshList() {
        adapter.list = TodoDb.get()
    }

    override fun onResume() {
        super.onResume()

        refreshList()
    }
}
