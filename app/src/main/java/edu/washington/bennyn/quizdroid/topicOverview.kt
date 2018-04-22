package edu.washington.bennyn.quizdroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class topicOverview : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_overview)

        val subjectName = findViewById<TextView>(R.id.subjectName)
        val numQuestions = findViewById<TextView>(R.id.numQuestions)
        val description = findViewById<TextView>(R.id.description)

        val subject = intent.getStringExtra("subject")
        val numOfQuestions = 3 //get from intent later
        val details = "Description placeholder that will be initialized later!"
        val numQuestionsText = "This topic contains $numOfQuestions questions"

        subjectName.text = subject
        description.text = details
        numQuestions.text = numQuestionsText

    }
}
