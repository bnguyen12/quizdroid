package edu.washington.bennyn.quizdroid

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.view.*
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.FileInputStream
import java.lang.reflect.Array
import java.util.jar.Manifest
import android.net.NetworkInfo
import android.net.ConnectivityManager



class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        if (!OnlineRepository().topics.isEmpty()) { //if no questions file is present don't do anything
            listView = findViewById(R.id.listView)
            listView.adapter = customAdapter(this)
            listView.setOnItemClickListener { parent, view, position, id ->
                val intent = Intent(this, Quiz::class.java)
                intent.putExtra("subject", position)
                startActivity(intent)
            }
        }

        if (!isConnected(this)) {
            if (isAirplaneModeOn(this)) {
                val builder = AlertDialog.Builder(this)
                val inflater = this.layoutInflater
                val dialogView = inflater.inflate(R.layout.custom_dialog, null)
                builder.setTitle("Do you want to turn off airplane mode?")
                builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                    startActivity(Intent(android.provider.Settings.ACTION_SETTINGS))
                })
                builder.setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->

                })
                builder.create().show()
            } else {
                val builder = AlertDialog.Builder(this)
                val inflater = this.layoutInflater
                val dialogView = inflater.inflate(R.layout.custom_dialog, null)
                builder.setView(dialogView)
                builder.setTitle("Device is not currently connected")
                builder.setNeutralButton("OK", DialogInterface.OnClickListener { dialog, which ->

                })
                builder.create().show()
            }
        }
    }

    private fun isAirplaneModeOn(context: Context): Boolean {
        return Settings.System.getInt(context.contentResolver,
                Settings.Global.AIRPLANE_MODE_ON, 0) != 0

    }

    // Returns whether or not the phone is connected to internet
    fun isConnected(context: Context): Boolean {
        val cm = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        startActivity(Intent(this, Preferences::class.java))
        return true
    }

    private class customAdapter(context: Context): BaseAdapter() {
        private val myContext: Context

        init {
            myContext = context
        }

        override fun getItem(position: Int): Any {
            return QuizApp.subjectNames[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return QuizApp.subjectNames.size
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val inflater = LayoutInflater.from(myContext)
            val row = inflater.inflate(R.layout.custom_row, parent, false)

            val topicRow = row.findViewById<TextView>(R.id.topicRow)
            topicRow.text = QuizApp.subjectNames[position]

            val descriptionRow = row.findViewById<TextView>(R.id.descriptionRow)
            descriptionRow.text = QuizApp.repository.getTopic(position).shortDesc

            return row
        }
    }
}
