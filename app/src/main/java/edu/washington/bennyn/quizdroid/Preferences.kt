package edu.washington.bennyn.quizdroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView

class Preferences : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val urlView = findViewById<TextView>(R.id.urlInput)
        val urlSubmitBtn = findViewById<Button>(R.id.urlSubmitButton)
        val numberPicker = findViewById<NumberPicker>(R.id.numberPicker)
        numberPicker.maxValue = 10
        numberPicker.minValue = 0
        numberPicker.wrapSelectorWheel = false


        urlSubmitBtn.setOnClickListener {
            //Implement later
        }
        numberPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            //Implement later
        }
    }
}
