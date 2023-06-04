package com.example.easynote

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.easynote.databinding.ActivityMainBinding
import com.example.easynote.ui.notes.AddNoteActivity
import com.example.easynote.ui.todo_lists.AddTodoList
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: NotesViewModel by viewModels { NotesViewModel.Factory }

    private val addNoteLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                viewModel.insert(result)
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
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_notes, R.id.navigation_todo_lists
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        val fab: FloatingActionButton = findViewById(R.id.fab_add)

        fab.setOnClickListener {
            val activity = when (navController.currentDestination?.id) {
                R.id.navigation_notes -> {
                    val intent = Intent(this, AddNoteActivity::class.java)
                    addNoteLauncher.launch(intent)

                }

                R.id.navigation_todo_lists -> AddTodoList::class.java
                else -> null
            }
        }

    }


}