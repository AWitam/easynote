package com.example.easynote.ui.todo_list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.easynote.MainActivity
import com.example.easynote.R

class AddTodoListActivity : AppCompatActivity() {

    lateinit var todoDescription: EditText
    lateinit var saveBtn: ImageView
    lateinit var deleteBtn: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        todoDescription = findViewById(R.id.todo_description)
        saveBtn = findViewById(R.id.icon_save)
        deleteBtn = findViewById(R.id.icon_delete)

        val intent = intent
        val isUpdate = intent?.getBooleanExtra(IS_TODO_UPDATE, false)
        val isCompleted = intent?.getBooleanExtra(TODO_COMPLETED_KEY, false)

        if (isUpdate == true) {
            todoDescription.setText(intent.getStringExtra(TODO_DESCRIPTION_KEY))
        }

        saveBtn.setOnClickListener {

            if (validateInput(todoDescription)) {
                Toast.makeText(
                    this,
                    R.string.todo_empty_not_saved,
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            val resultIntent = Intent(this, MainActivity::class.java)
            resultIntent.putExtra(TODO_DESCRIPTION_KEY, todoDescription.text.toString())

            if (isUpdate == true) {
                resultIntent.putExtra(IS_TODO_UPDATE, true)
                resultIntent.putExtra(TODO_ID, intent.getIntExtra(TODO_ID, -1))
                resultIntent.putExtra(TODO_COMPLETED_KEY, isCompleted)
                startActivity(resultIntent)
                finish()
                return@setOnClickListener
            }

            resultIntent.putExtra(TODO_DESCRIPTION_KEY, todoDescription.text.toString())
            resultIntent.putExtra(TODO_COMPLETED_KEY, false)
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        deleteBtn.setOnClickListener {
            val resultIntent = Intent(this, MainActivity::class.java)
            resultIntent.putExtra(IS_TODO_DELETE, true)
            resultIntent.putExtra(TODO_ID, intent.getIntExtra(TODO_ID, -1))
            startActivity(resultIntent)
            finish()
        }

    }

    private fun validateInput(todoDescription: EditText): Boolean {
        return TextUtils.isEmpty(todoDescription.text.toString())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    companion object {
        const val IS_TODO_UPDATE = "com.example.android.todo.is_todo_update"
        const val TODO_ID = "com.example.android.todo.todo_id"
        const val TODO_DESCRIPTION_KEY = "com.example.android.todo.todo_description"
        const val TODO_COMPLETED_KEY = "com.example.android.todo.todo_completed"
        const val IS_TODO_DELETE = "com.example.android.todo.is_todo_delete"
    }


}
