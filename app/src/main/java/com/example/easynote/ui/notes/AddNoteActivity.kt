package com.example.easynote.ui.notes

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import com.example.easynote.R



class AddNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        val title = findViewById<EditText>(R.id.notes_title)
        val note_desc = findViewById<EditText>(R.id.notes_description)
        val saveBtn = findViewById<ImageView>(R.id.icon_save)

        saveBtn.setOnClickListener {
            if (TextUtils.isEmpty(note_desc.text) || TextUtils.isEmpty(title.text)) {
                setResult(Activity.RESULT_CANCELED, Intent())
            } else {
               // todo

            }
            finish()

        }

    }


}