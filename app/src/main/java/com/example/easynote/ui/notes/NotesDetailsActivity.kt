package com.example.easynote.ui.notes

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.easynote.MainActivity
import com.example.easynote.R


class NotesDetailsActivity : AppCompatActivity() {

    lateinit var title: EditText
    lateinit var note_desc: EditText
    lateinit var saveBtn: ImageView
    lateinit var deleteBtn: ImageView

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        title = findViewById(R.id.notes_title)
        note_desc = findViewById(R.id.notes_description)
        saveBtn = findViewById(R.id.icon_save)
        deleteBtn = findViewById(R.id.icon_delete)

        val intent = intent
        val isUpdate = intent?.getBooleanExtra(IS_NOTE_UPDATE, false)

        if (isUpdate == true) {
            title.setText(intent.getStringExtra(TITLE_KEY))
            note_desc.setText(intent.getStringExtra(DESCRIPTION_KEY))
        }

        saveBtn.setOnClickListener {

            if (validateInput(title, note_desc)) {
                Toast.makeText(
                    this,
                    R.string.note_empty_not_saved,
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }


            val resultIntent = Intent(this, MainActivity::class.java)
            val formater = SimpleDateFormat("EEE, d MMM yyyy HH:mm:a")

            resultIntent.putExtra(TITLE_KEY, title.text.toString())
            resultIntent.putExtra(DESCRIPTION_KEY, note_desc.text.toString())
            resultIntent.putExtra(DATE_KEY, formater.format(System.currentTimeMillis()))

            if (isUpdate == true) {
                resultIntent.putExtra(IS_NOTE_UPDATE, true)
                resultIntent.putExtra(NOTE_ID, intent.getIntExtra(NOTE_ID, -1))
                startActivity(resultIntent)
                finish()
                return@setOnClickListener
            }

            deleteBtn.setOnClickListener {
                val resultIntent = Intent(this, MainActivity::class.java)
                resultIntent.putExtra(IS_NOTE_DELETE, true)
                resultIntent.putExtra(NOTE_ID, intent.getIntExtra(NOTE_ID, -1))
                resultIntent.putExtra(TITLE_KEY, title.text.toString())
                resultIntent.putExtra(DESCRIPTION_KEY, note_desc.text.toString())
                startActivity(resultIntent)
                finish()
                return@setOnClickListener
            }

            setResult(Activity.RESULT_OK, resultIntent)
            finish()
            return@setOnClickListener
        }


    }

    fun validateInput(title: EditText, description: EditText): Boolean {
        return TextUtils.isEmpty(title.text.toString()) or TextUtils.isEmpty(description.text.toString())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    companion object {
        const val IS_NOTE_UPDATE = "com.example.android.notes.is_update"
        const val NOTE_ID = "com.example.android.notes.note_id"
        const val TITLE_KEY = "com.example.android.notes.new_note_title"
        const val DESCRIPTION_KEY = "com.example.android.notes.new_note_description"
        const val DATE_KEY = "com.example.android.notes.new_note_date"
        const val IS_NOTE_DELETE = "com.example.android.notes.is_delete"

    }

}
