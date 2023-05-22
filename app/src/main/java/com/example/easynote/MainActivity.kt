package com.example.easynote

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.easynote.databinding.ActivityMainBinding
import com.example.easynote.ui.notes.AddNote
import com.example.easynote.ui.todo_lists.AddTodoList
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
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
                R.id.navigation_notes -> AddNote::class.java
                R.id.navigation_todo_lists -> AddTodoList::class.java
                else -> null
            }

            if (activity != null) {
                val intent = Intent(this, activity)
                startActivity(intent)
            }
        }
    }
}