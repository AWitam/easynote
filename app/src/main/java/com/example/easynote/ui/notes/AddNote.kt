package com.example.easynote.ui.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.easynote.R

class AddNote : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        val titleInput: EditText = findViewById(R.id.notes_title)
        val descriptionInput: EditText = findViewById(R.id.notes_description)
        val saveBtn: ImageView = findViewById(R.id.icon_save)


    }
}