package com.example.easynote


import android.app.Application
import com.example.easynote.database.NoteDatabase
import com.example.easynote.database.NotesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class EasyNoteApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    private val database by lazy { NoteDatabase.getDatabase(this, applicationScope) }
    val notesRepository by lazy { NotesRepository(database.getNoteDao()) }
}