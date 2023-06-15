package com.example.easynote.ui.notes


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.easynote.R
import com.example.easynote.models.Note

class NotesListAdapter : ListAdapter<Note, NotesListAdapter.ItemViewholder>(DiffCallback())  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_note_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NotesListAdapter.ItemViewholder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Note) = with(itemView) {
            val title = findViewById<TextView>(R.id.note_item_title)
            title.text = item.title
            val description = findViewById<TextView>(R.id.note_item_content)
            description.text = item.note
            val date = findViewById<TextView>(R.id.note_item_date)
            date.text = item.date

            setOnClickListener {
                val intent = Intent(context, NotesDetailsActivity::class.java)
                intent.putExtra(NotesDetailsActivity.NOTE_ID, item.id)
                intent.putExtra(NotesDetailsActivity.TITLE_KEY, item.title)
                intent.putExtra(NotesDetailsActivity.DESCRIPTION_KEY, item.note)
                intent.putExtra(NotesDetailsActivity.DATE_KEY, item.date)
                intent.putExtra(NotesDetailsActivity.IS_UPDATE, true)
                context.startActivity(intent)
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}
