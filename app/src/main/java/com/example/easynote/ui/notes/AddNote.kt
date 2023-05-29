package com.example.easynote.ui.notes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.easynote.R
import com.example.easynote.databinding.ActivityAddNoteBinding
import com.example.easynote.models.Note
import java.text.SimpleDateFormat
import java.util.*

class AddNote : AppCompatActivity() {

    private lateinit var binding : ActivityAddNoteBinding

    private lateinit var note : Note
    private lateinit var oldNote: Note
    var isUpdate = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            oldNote = intent.getSerializableExtra("current_note") as Note
            binding.notesTitle.setText(oldNote.title)
            binding.notesDescription.setText(oldNote.note)
            isUpdate = true

        } catch (e : Exception) {
            e.printStackTrace()
        }

        binding.iconSave.setOnClickListener{
            val title = binding.notesTitle.text.toString()
            val note_desc = binding.notesDescription.text.toString()

            if (title.isNotEmpty() || note_desc.isNotEmpty()){
                val formater = SimpleDateFormat("EEE, d MMM yyyy HH:mm:a")

                if (isUpdate) {
                    note = Note(
                        oldNote.id,title,note_desc,formater.format(Date())
                    )
                }else {
                    note = Note(
                        null,title,note_desc,formater.format(Date())
                    )
                }
                val intent = Intent()
                intent.putExtra("note", note)
                setResult(Activity.RESULT_OK, intent)
                finish()

            }else{
                Toast.makeText(this@AddNote,"Please enter a note",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }


        val titleInput: EditText = findViewById(R.id.notes_title)
        val descriptionInput: EditText = findViewById(R.id.notes_description)
        val saveBtn: ImageView = findViewById(R.id.icon_save)


    }
}