package com.example.easynote.ui.todo_list

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.easynote.MainActivity
import com.example.easynote.R
import com.example.easynote.models.Todo
import com.example.easynote.ui.notes.NotesDetailsActivity

class TodoListAdapter(private val checkboxListener: CheckboxListener) :
    ListAdapter<Todo, TodoListAdapter.ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_todo_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TodoListAdapter.ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Todo) = with(itemView) {
            val todoDescription = findViewById<TextView>(R.id.todo_item_description)
            todoDescription.text = item.todo_description

            val checkbox = findViewById<CheckBox>(R.id.checkbox)
            checkbox.isChecked = item.completed

            setOnClickListener {
                val updateIntent = Intent(context, AddTodoListActivity::class.java)
                setBaseExtras(updateIntent, item)
                updateIntent.putExtra(AddTodoListActivity.TODO_COMPLETED_KEY, item.completed)
                context.startActivity(updateIntent)
            }

            checkbox.setOnClickListener {
                val toggleCompleteIntent = Intent(context, MainActivity::class.java)
                setBaseExtras(toggleCompleteIntent, item)
                toggleCompleteIntent.putExtra(
                    AddTodoListActivity.TODO_COMPLETED_KEY,
                    checkbox.isChecked
                )
                checkboxListener.onCheckboxStateChanged(toggleCompleteIntent)
            }
        }
    }

}

fun setBaseExtras(intent: Intent, item: Todo) {
    intent.putExtra(AddTodoListActivity.IS_TODO_UPDATE, true)
    intent.putExtra(AddTodoListActivity.TODO_ID, item.id)
    intent.putExtra(AddTodoListActivity.TODO_DESCRIPTION_KEY, item.todo_description)
}

class DiffCallback : DiffUtil.ItemCallback<Todo>() {
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem == newItem
    }
}

