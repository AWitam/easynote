package com.example.easynote.ui.todo_list

import android.content.Intent
import androidx.activity.result.ActivityResult
import com.example.easynote.database.TodoRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.easynote.EasyNoteApplication
import com.example.easynote.models.Todo
import kotlinx.coroutines.launch


class TodoViewModel(private val todoRepository: TodoRepository) : ViewModel() {
    val allTodos = todoRepository.allTodos.asLiveData()

    fun insertTodo(activityResult: ActivityResult) {
        val data = activityResult.data
        val todoDescription = data?.getStringExtra(AddTodoListActivity.TODO_DESCRIPTION_KEY)

        viewModelScope.launch {
            viewModelScope.launch {
                todoRepository.insert(
                    Todo(
                        todo_description = todoDescription,
                        completed = false,
                        id = null
                    )
                )

            }
        }
    }

    fun updateTodo(intent: Intent) {
        val todoFromIntent = getDataFromIntent(intent)

        viewModelScope.launch {
            todoRepository.update(
                todoFromIntent
            )
        }
    }

    fun deleteTodo(intent: Intent) {
        val todoFromIntent = getDataFromIntent(intent)

        viewModelScope.launch {
            todoRepository.delete(
                todoFromIntent
            )
        }
    }

    fun getDataFromIntent(intent: Intent): Todo {
        val todoDescription = intent.getStringExtra(AddTodoListActivity.TODO_DESCRIPTION_KEY)
        val id = intent.getIntExtra(AddTodoListActivity.TODO_ID, -1)
        val completed = intent.getBooleanExtra(AddTodoListActivity.TODO_COMPLETED_KEY, false)

        return Todo(
            todo_description = todoDescription,
            completed = completed,
            id = id
        )
    }

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application = checkNotNull(extras[APPLICATION_KEY])
                return TodoViewModel(
                    (application as EasyNoteApplication).todoRepository,
                ) as T
            }
        }
    }
}

