package edu.washington.bennyn.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class answerPage : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        container!!.removeAllViews()
        return inflater.inflate(R.layout.activity_answer_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myAnsBox = getView()!!.findViewById<TextView>(R.id.myAns)
        val actualAnsBox = getView()!!.findViewById<TextView>(R.id.actualAns)
        val score = getView()!!.findViewById<TextView>(R.id.score)
        val btn = getView()!!.findViewById<Button>(R.id.nextOrFinalBtn)

        val totalQuestions = arguments!!.getInt("totalQuestions")
        val questionNum = arguments!!.getInt("questionNum")
        val myAnswer = arguments!!.getInt("myAnswer")

        myAnsBox.text = myAnswer.toString()
        actualAnsBox.text = 1.toString() //set to an actual answer later

        val scoreText = "You have $questionNum out of $totalQuestions correct!" //turn questionNum into the correct # of answers lately
        score.text = scoreText

        if (totalQuestions == questionNum) {
            btn.text = "Finish"
        }

        btn.setOnClickListener {
            if (btn.text == "Finish") {
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
            } else {
                val fragment = questionPage()

                val bundle = Bundle()
                bundle.putInt("totalQuestions", totalQuestions)
                bundle.putInt("questionNum", questionNum + 1)

                fragment.arguments = bundle

                val transaction = fragmentManager!!.beginTransaction()
                transaction.replace(R.id.fragmentLayout, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
    }
}
