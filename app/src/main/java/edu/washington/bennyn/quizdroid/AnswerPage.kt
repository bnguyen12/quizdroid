package edu.washington.bennyn.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class AnswerPage : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        container!!.removeAllViews()
        return inflater.inflate(R.layout.activity_answer_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myAnsBox = getView()!!.findViewById<TextView>(R.id.myAns)
        val actualAnsBox = getView()!!.findViewById<TextView>(R.id.actualAns)
        val scoreBox = getView()!!.findViewById<TextView>(R.id.score)
        val btn = getView()!!.findViewById<Button>(R.id.nextOrFinalBtn)

        var score = arguments!!.getInt("score")
        val subject = arguments!!.getInt("subject")
        val totalQuestions = arguments!!.getInt("totalQuestions")
        val questionNum = arguments!!.getInt("questionNum")
        val myAnswer = arguments!!.getInt("myAnswer")

        myAnsBox.text = myAnswer.toString() //this is a number, so later set it to an actual answer from the question objects
        actualAnsBox.text = QuizApp.offlineRepository.getTopic(subject).questions[questionNum].answer.toString()

        if (myAnswer == QuizApp.offlineRepository.getTopic(subject).questions[questionNum].answer) {
            score += 1
        }

        val scoreText = "You have ${score} out of $totalQuestions correct!" //turn questionNum into the correct # of answers lately
        scoreBox.text = scoreText

        if (totalQuestions == questionNum + 1) {
            btn.text = "Finish"
        }

        btn.setOnClickListener {
            if (btn.text == "Finish") {
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
            } else {
                val fragment = QuestionPage()
                val bundle = Bundle()
                bundle.putInt("score", score)
                bundle.putInt("totalQuestions", totalQuestions)
                bundle.putInt("questionNum", questionNum + 1)
                fragment.arguments = bundle

                val transaction = fragmentManager!!.beginTransaction()
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
                transaction.replace(R.id.fragmentLayout, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
    }
}
