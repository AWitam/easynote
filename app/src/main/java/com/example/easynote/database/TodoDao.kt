package com.example.easynote.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
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

    @Update
    suspend fun update(todo: Todo)

}
