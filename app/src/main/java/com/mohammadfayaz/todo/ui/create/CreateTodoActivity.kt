package com.mohammadfayaz.todo.ui.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.mohammadfayaz.todo.R
import com.mohammadfayaz.todo.data.TodoDb

class CreateTodoActivity : AppCompatActivity() {

    lateinit var taskField: EditText
    lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_todo)

        taskField = findViewById(R.id.newTodoTextField)
        saveButton = findViewById(R.id.saveTodoButton)

        saveButton.setOnClickListener {
            TodoDb.add(taskField.text.toString())

            taskField.text.clear()

            finish()
        }
    }
}