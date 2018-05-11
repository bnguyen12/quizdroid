package edu.washington.bennyn.quizdroid

import android.Manifest
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
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
        numberPicker.minValue = 1
        numberPicker.maxValue = 10
        numberPicker.wrapSelectorWheel = false

        urlSubmitBtn.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                    PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
            } else {
                downloadFile(urlView.text.toString())
                Toast.makeText(this, "Attempting download at ${urlView.text}",
                        Toast.LENGTH_SHORT).show()
            }
        }

        numberPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            //Implement later
        }
    }

    // Downloads JSON file into sdcard/Download
    fun downloadFile(url: String) {
        val downloadURI = Uri.parse(url)
        val request = DownloadManager.Request(downloadURI)
        val downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        request.setTitle("Download")
        request.setDescription("Downloading file . . .")
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "questions.json")
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
        request.allowScanningByMediaScanner()
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        downloadManager.enqueue(request)
    }
}
