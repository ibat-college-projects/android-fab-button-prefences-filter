package com.aaademo.fabprefs

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {
    var logTag = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            /*
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

             */
            var sampleToItem = getString(R.string.HELLO_WORLD) + LocalDateTime.now()

            Log.i(logTag, getString(R.string.FAB_MESSAGE))
            Log.i(logTag, sampleToItem)

            var prefs = getSharedPreferences(getString(R.string.PACKAGE_NAME), Context.MODE_PRIVATE)

            // List of string items
            var todoItems =
                prefs.getStringSet(getString(R.string.TODO_ITEMS), setOf())?.toMutableSet()

            // Add to the todo Item
            todoItems?.add(sampleToItem)

            prefs.edit().putStringSet(getString(R.string.TODO_ITEMS), todoItems).apply()

            Log.i(logTag, getString(R.string.SAVED_MESSAGE))
        }
    }

    override fun onResume() {
        super.onResume()

        Log.i(logTag, "onResume is called")
        var prefs = getSharedPreferences(getString(R.string.PACKAGE_NAME), Context.MODE_PRIVATE)

        // List of string items
        var todoItems = prefs.getStringSet(getString(R.string.TODO_ITEMS), setOf())?.toMutableSet()

        Log.i(logTag, "the set contains ${todoItems?.size} elements")

        todoItems?.forEach { currentTodoItem -> Log.i(logTag, currentTodoItem) }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
