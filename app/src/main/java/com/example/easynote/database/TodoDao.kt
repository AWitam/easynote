package com.example.easynote.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.easynote.models.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllTodo(): Flow<List<Todo>>

    @Query("UPDATE todo_table set  completed = :completed where id = :id")
    suspend fun update(id: Int?, completed: Boolean)

}