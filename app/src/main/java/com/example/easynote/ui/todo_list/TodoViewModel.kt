package com.example.easynote.ui.todo_list

import com.example.easynote.database.TodoRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.easynote.EasyNoteApplication


class TodoViewModel(private val todoRepository: TodoRepository) : ViewModel() {
    val allTodos = todoRepository.allTodos.asLiveData()

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