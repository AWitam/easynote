package com.example.easynote.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class Todo(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "todo_description") var todo_description: String?,
    @ColumnInfo(name = "completed") var completed: Boolean = false,
)