package edu.washington.bennyn.quizdroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)
        val subjects = arrayOf("Math", "Physics", "Lego Marvel Superheroes")
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, subjects)
        listView.adapter = adapter
    }
}
