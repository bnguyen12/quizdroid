package edu.washington.bennyn.quizdroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class TopicOverview : android.support.v4.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        container!!.removeAllViews()
        return inflater!!.inflate(R.layout.activity_topic_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val subjectBox = getView()!!.findViewById<TextView>(R.id.subjectName)
        val numQuestionsBox = getView()!!.findViewById<TextView>(R.id.numQuestions)
        val descriptionBox = getView()!!.findViewById<TextView>(R.id.description)
        val button = getView()!!.findViewById<Button>(R.id.beginButton)

        val subject = arguments!!.getString("subject")
        val numOfQuestions = 4 //set from JSON data later
        val details = "Description placeholder that will be initialized later!"
        val numQuestionsText = "This topic contains $numOfQuestions questions"

        subjectBox.text = subject
        descriptionBox.text = details
        numQuestionsBox.text = numQuestionsText
        button.setOnClickListener {
            val fragment = QuestionPage()

            val bundle = Bundle()
            bundle.putString("subject", subject)
            bundle.putInt("questionNum", 1)
            bundle.putInt("totalQuestions", numOfQuestions)

            fragment.arguments = bundle

            val transaction = fragmentManager!!.beginTransaction()
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
            transaction.replace(R.id.fragmentLayout, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}
