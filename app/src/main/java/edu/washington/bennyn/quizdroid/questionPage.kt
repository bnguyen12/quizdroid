package edu.washington.bennyn.quizdroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class questionPage : android.support.v4.app.Fragment() {
    private var questionNum = 1
    private var totalQuestions = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        container!!.removeAllViews() //gets rid of all the old stuff
        return inflater.inflate(R.layout.activity_question_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val questionView = getView()!!.findViewById<TextView>(R.id.question)
        val radioGroup = getView()!!.findViewById<RadioGroup>(R.id.radioGroup)
        val submitBtn = getView()!!.findViewById<Button>(R.id.submitButton)

            totalQuestions = arguments!!.getInt("totalQuestions")
            questionNum = arguments!!.getInt("questionNum")
            var myAnswer = 1 //placeholder for now
            //val subject = ... //set later with intent
            val answers = arrayOf("1", "2", "3", "4") //set later with intent
            val radioButtons = radioGroup.touchables

            //Set answers on radio buttons
            for (i in 0..3) {
                val radioButton = radioButtons[i] as RadioButton
                radioButton.text = answers[i]
            }

            radioGroup.setOnCheckedChangeListener { group, checkedId ->
                myAnswer = getView()!!.findViewById<RadioButton>(checkedId).text.toString().toInt()
                submitBtn.visibility = (View.VISIBLE)
            }

            submitBtn.setOnClickListener {
                val fragment = answerPage()

                val bundle = Bundle()
                bundle.putInt("totalQuestions", totalQuestions)
                bundle.putInt("questionNum", questionNum)
                bundle.putInt("myAnswer", myAnswer)

                fragment.arguments = bundle

                val transaction = fragmentManager!!.beginTransaction()
                transaction.replace(R.id.fragmentLayout, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
    }
}
