package com.example.easynote.ui.notes

import android.app.Activity
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.easynote.R


class AddNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        val title = findViewById<EditText>(R.id.notes_title)
        val note_desc = findViewById<EditText>(R.id.notes_description)
        val saveBtn = findViewById<ImageView>(R.id.icon_save)

        saveBtn.setOnClickListener {
            val isEmpty =
                TextUtils.isEmpty(title.text.toString()) or TextUtils.isEmpty(note_desc.text.toString())
            val intent = Intent()

            if (isEmpty) {
                Toast.makeText(
                    this,
                    R.string.note_empty_not_saved,
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            } else {
                val formater = SimpleDateFormat("EEE, d MMM yyyy HH:mm:a")
                val note = title.text.toString()
                intent.putExtra(TITLE_KEY, note)
                intent.putExtra(DESCRIPTION_KEY, note_desc.text.toString())
                intent.putExtra(DATE_KEY, formater.format(System.currentTimeMillis()))
                setResult(Activity.RESULT_OK, intent)
            }
            finish()
        }


    }

    companion object {
        const val TITLE_KEY = "com.example.android.notes.new_note_title"
        const val DESCRIPTION_KEY = "com.example.android.notes.new_note_description"
        const val DATE_KEY = "com.example.android.notes.new_note_date"
    }


}