package edu.washington.bennyn.quizdroid

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)
        listView.adapter = customAdapter(this)
        listView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, Quiz::class.java)
            intent.putExtra("subject", position)
            startActivity(intent)
        }
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
            descriptionRow.text = QuizApp.offlineRepository.getTopic(position).shortDesc

            return row
        }
    }
}
