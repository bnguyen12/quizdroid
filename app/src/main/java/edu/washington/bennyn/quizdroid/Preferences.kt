package edu.washington.bennyn.quizdroid

import android.Manifest
import android.app.AlarmManager
import android.app.DownloadManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast

class Preferences : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val urlView = findViewById<TextView>(R.id.urlInput)
        val urlSubmitBtn = findViewById<Button>(R.id.urlSubmitButton)
        val numberPicker = findViewById<NumberPicker>(R.id.numberPicker)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, CustomReceiver::class.java)
        numberPicker.minValue = 1
        numberPicker.maxValue = 10
        numberPicker.wrapSelectorWheel = false

        urlSubmitBtn.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                    PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
            } else {
                intent.putExtra("url", urlView.text.toString())
                val pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)
                val minutes = numberPicker.value * 60 * 1000
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), minutes.toLong(), pendingIntent)
                Toast.makeText(this, "Attempting download at ${urlView.text}",
                        Toast.LENGTH_SHORT).show()
            }
        }

        numberPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            //Implement later
        }
    }
}
