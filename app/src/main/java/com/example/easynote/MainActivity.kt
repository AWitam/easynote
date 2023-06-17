package com.example.easynote

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.easynote.databinding.ActivityMainBinding
import com.example.easynote.ui.notes.NotesDetailsActivity
import com.example.easynote.ui.notes.NotesDetailsActivity.Companion.IS_NOTE_DELETE
import com.example.easynote.ui.notes.NotesDetailsActivity.Companion.IS_NOTE_UPDATE
import com.example.easynote.ui.notes.NotesViewModel
import com.example.easynote.ui.todo_list.AddTodoListActivity
import com.example.easynote.ui.todo_list.AddTodoListActivity.Companion.IS_TODO_DELETE
import com.example.easynote.ui.todo_list.AddTodoListActivity.Companion.IS_TODO_UPDATE
import com.example.easynote.ui.todo_list.TodoViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val notesViewModel: NotesViewModel by viewModels { NotesViewModel.Factory }
    private val todoViewModel: TodoViewModel by viewModels { TodoViewModel.Factory }
    private var currentDestinationId = 0


    private val addNoteLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                notesViewModel.insertNote(result)
            }
        }

    private val addTodoLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                todoViewModel.insertTodo(result)
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navView: BottomNavigationView = binding.navView
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment


        val navController = navHostFragment.navController
        currentDestinationId  = navController.currentDestination?.id ?: 0

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_notes, R.id.navigation_todo_lists
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val intent = intent
        if (intent.hasExtra(IS_NOTE_UPDATE)) {
            notesViewModel.updateNote(intent)
        }

        if (intent.hasExtra(IS_NOTE_DELETE)) {
            notesViewModel.deleteNote(intent)
        }

        if (intent.hasExtra(IS_TODO_UPDATE)) {
            todoViewModel.updateTodo(intent)
        }

        if( intent.hasExtra(IS_TODO_DELETE)){
            todoViewModel.deleteTodo(intent)
        }


        val fab: FloatingActionButton = findViewById(R.id.fab_add)

        fab.setOnClickListener {
            when (navController.currentDestination?.id) {
                R.id.navigation_notes -> {
                    val addNoteIntent = Intent(this, NotesDetailsActivity::class.java)
                    addNoteLauncher.launch(addNoteIntent)
                }

                R.id.navigation_todo_lists -> {
                    val addTodoIntent = Intent(this, AddTodoListActivity::class.java)
                    addTodoLauncher.launch(addTodoIntent)
                }

                else -> null
            }
        }
    }


}


