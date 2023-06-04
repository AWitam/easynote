package com.example.easynote.ui.notes

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.easynote.R
import com.example.easynote.models.Note


class NotesAdapter(private val data: List<Note>) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var noteTitle: TextView
        var noteDescription: TextView
        var noteDate: TextView

        init {
            noteTitle = itemView.findViewById(R.id.note_title)
            noteDescription = itemView.findViewById(R.id.notes_description)
            noteDate = itemView.findViewById(R.id.note_date)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_note_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteTitle.text = data[position].title
        holder.noteDescription.text = data[position].note
        holder.noteDate.text = data[position].date
    }


}