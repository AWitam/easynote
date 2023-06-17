package com.example.easynote.ui.todo_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.example.easynote.R

class AddTodoListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo)

    }


    override fun onBackPressed() {
        Log.d("MainActivity", "onBackPressed")
        val fragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main)
        if( fragment is TodoListFragment) {
            supportFragmentManager.popBackStack(fragment.javaClass.name, 0)
        }

    }


}