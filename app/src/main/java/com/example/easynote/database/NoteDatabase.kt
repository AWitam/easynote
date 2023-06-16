package com.example.easynote.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.easynote.models.Note
import com.example.easynote.models.Todo
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Note::class, Todo::class], version = 2)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NoteDao
    abstract fun getTodoListDao(): TodoDao

    companion object {

        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): NoteDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}