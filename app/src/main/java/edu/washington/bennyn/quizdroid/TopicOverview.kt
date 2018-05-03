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

        val subject = arguments!!.getInt("subject")
        val totalQuestions = QuizApp.repository.getTopic(subject).questions.size
        val numQuestionsText = "This topic contains $totalQuestions questions"

        subjectBox.text = QuizApp.repository.getTopic(subject).title
        descriptionBox.text = QuizApp.repository.getTopic(subject).longDesc
        numQuestionsBox.text = numQuestionsText
        button.setOnClickListener {
            val fragment = QuestionPage()

            val bundle = Bundle()
            bundle.putInt("subject", subject)
            bundle.putInt("questionNum", 0)
            bundle.putInt("totalQuestions", totalQuestions)
            bundle.putInt("score", 0)
            fragment.arguments = bundle

            val transaction = fragmentManager!!.beginTransaction()
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
            transaction.replace(R.id.fragmentLayout, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}
