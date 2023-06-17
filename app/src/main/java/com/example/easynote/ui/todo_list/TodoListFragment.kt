package com.example.easynote.ui.todo_list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.easynote.R

interface CheckboxListener {
    fun onCheckboxStateChanged(intent: Intent)
}

class TodoListFragment : Fragment(), CheckboxListener {

    companion object {
        fun newInstance() = TodoListFragment()
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TodoListAdapter
    private lateinit var emptyView: TextView

    private val todoViewModel: TodoViewModel by activityViewModels { TodoViewModel.Factory }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_todo_list, container, false);
        emptyView = view.findViewById(R.id.empty_todo_view)
        recyclerView = view.findViewById(R.id.todo_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = TodoListAdapter(this)
        recyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        todoViewModel.allTodos.observe(viewLifecycleOwner) { todos ->
            if (todos.isNotEmpty()) {
                recyclerView.visibility = View.VISIBLE
                emptyView.visibility = View.GONE
                adapter.submitList(todos)

            } else {
                recyclerView.visibility = View.GONE
                emptyView.visibility = View.VISIBLE
            }
        }
    }

    override fun onCheckboxStateChanged(intent: Intent) {
        todoViewModel.updateTodo(intent)
    }

}

