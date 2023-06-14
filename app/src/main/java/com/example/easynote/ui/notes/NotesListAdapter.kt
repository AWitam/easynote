package com.example.easynote.ui.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.easynote.R
import com.example.easynote.models.Note


class NotesListAdapter : ListAdapter<Note, NotesListAdapter.NoteViewHolder>(WordsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = getItem(position)
        holder.bind(currentNote)
    }

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title : TextView = itemView.findViewById(R.id.note_item_title)
        private val content : TextView = itemView.findViewById(R.id.note_item_content)
        private val date : TextView = itemView.findViewById(R.id.note_item_date)

        fun bind(currentNote: Note?) {
            title.text = currentNote?.title
            content.text = currentNote?.note
            date.text = currentNote?.date
        }

        companion object {
            fun create(parent: ViewGroup): NoteViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_note_item, parent, false)
                return NoteViewHolder(view)
            }
        }
    }

    class WordsComparator : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.note == newItem.note
        }
    }
}