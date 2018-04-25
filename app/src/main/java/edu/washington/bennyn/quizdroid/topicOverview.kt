package edu.washington.bennyn.quizdroid

import android.app.Fragment
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_question_page.*

class topicOverview : android.support.v4.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        container!!.removeAllViews()
        return inflater!!.inflate(R.layout.activity_topic_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val subjectName = getView()!!.findViewById<TextView>(R.id.subjectName)
        val numQuestions = getView()!!.findViewById<TextView>(R.id.numQuestions)
        val description = getView()!!.findViewById<TextView>(R.id.description)
        val button = getView()!!.findViewById<Button>(R.id.beginButton)

        val subject = arguments!!.getString("subject")
        val numOfQuestions = 4 //get from intent later
        val details = "Description placeholder that will be initialized later!"
        val numQuestionsText = "This topic contains $numOfQuestions questions"

        subjectName.text = subject
        description.text = details
        numQuestions.text = numQuestionsText
        button.setOnClickListener {
            val fragment = questionPage()

            val bundle = Bundle()
            bundle.putString("subject", subject)
            bundle.putInt("questionNum", 1)
            bundle.putInt("totalQuestions", numOfQuestions)

            fragment.arguments = bundle

            val transaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.fragmentLayout, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}
