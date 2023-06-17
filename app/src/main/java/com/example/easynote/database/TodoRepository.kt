package com.example.easynote.database

import androidx.lifecycle.ViewModel
import com.example.easynote.models.Todo
import kotlinx.coroutines.flow.Flow

class TodoRepository(private val todoDao: TodoDao) : ViewModel() {


    val allTodos: Flow<List<Todo>> = todoDao.getAllTodo()

    suspend fun insert(todo: Todo) {
        todoDao.insert(todo)
    }

    suspend fun delete(todo: Todo) {
        todoDao.delete(todo)
    }

    suspend fun update(todo: Todo) {
        todoDao.update(todo)
    }

}