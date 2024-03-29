package com.example.easynote.database

import androidx.lifecycle.ViewModel
import com.example.easynote.models.Note
import kotlinx.coroutines.flow.Flow

class NotesRepository(private val noteDao: NoteDao) : ViewModel() {

    val allNotes : Flow<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }

    suspend fun update(note: Note) {
        noteDao.update(note.id, note.title, note.note)
    }

}