package com.example.easynote

import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.easynote.database.NotesRepository
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import com.example.easynote.models.Note
import com.example.easynote.ui.notes.AddNoteActivity
import kotlinx.coroutines.launch


class NotesViewModel(private val notesRepository: NotesRepository) : ViewModel() {
    val allNotes = notesRepository.allNotes.asLiveData()

    fun insert(note: ActivityResult) = viewModelScope.launch {
        val data: Intent? = note.data
        val title = data?.getStringExtra(AddNoteActivity.TITLE_KEY)
        val content = data?.getStringExtra(AddNoteActivity.DESCRIPTION_KEY)
        val date = data?.getStringExtra(AddNoteActivity.DATE_KEY)

        notesRepository.insert(
            Note(
                title = title,
                note = content,
                date = date,
                id = null
            )
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
                return NotesViewModel(
                    (application as EasyNoteApplication).notesRepository,
                ) as T
            }
        }
    }
}

