package com.example.easynote.ui.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.easynote.database.NotesRepository
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.example.easynote.EasyNoteApplication


class NotesViewModel(private val notesRepository: NotesRepository ) : ViewModel() {
    val allNotes = notesRepository.allNotes.asLiveData()

    init {
        println("BlankViewModel init")
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

