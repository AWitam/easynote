package com.example.easynote.ui.notes


import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.easynote.EasyNoteApplication
import com.example.easynote.database.NotesRepository
import com.example.easynote.models.Note
import kotlinx.coroutines.launch


class NotesViewModel(private val notesRepository: NotesRepository) : ViewModel() {
    val allNotes = notesRepository.allNotes.asLiveData()

    fun insertNote(activityResult: ActivityResult) {
        val data = activityResult.data
        val title = data?.getStringExtra(NotesDetailsActivity.TITLE_KEY)
        val content = data?.getStringExtra(NotesDetailsActivity.DESCRIPTION_KEY)
        val date = data?.getStringExtra(NotesDetailsActivity.DATE_KEY)

        viewModelScope.launch {
            viewModelScope.launch {
                notesRepository.insert(
                    Note(
                        title = title,
                        note = content,
                        date = date,
                        id = null
                    )
                )

            }
        }
    }


    fun updateNote(intent: Intent) {
        val title = intent.getStringExtra(NotesDetailsActivity.TITLE_KEY)
        val content = intent.getStringExtra(NotesDetailsActivity.DESCRIPTION_KEY)
        val date = intent.getStringExtra(NotesDetailsActivity.DATE_KEY)
        val id = intent.getIntExtra(NotesDetailsActivity.NOTE_ID, -1)

        viewModelScope.launch {
            notesRepository.update(
                Note(
                    title = title,
                    note = content,
                    date = date,
                    id = id
                )
            )

        }
    }

    fun deleteNote(intent: Intent) {
        val title = intent.getStringExtra(NotesDetailsActivity.TITLE_KEY)
        val content = intent.getStringExtra(NotesDetailsActivity.DESCRIPTION_KEY)
        val date = intent.getStringExtra(NotesDetailsActivity.DATE_KEY)
        val id = intent.getIntExtra(NotesDetailsActivity.NOTE_ID, -1)


        viewModelScope.launch {
            notesRepository.delete(
                Note(
                    title = title,
                    note = content,
                    date = date,
                    id = id
                )
            )
        }
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
