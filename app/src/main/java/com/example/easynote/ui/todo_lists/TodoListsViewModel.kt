package com.example.easynote.ui.todo_lists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoListsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is todo lists  Fragment"
    }
    val text: LiveData<String> = _text
}