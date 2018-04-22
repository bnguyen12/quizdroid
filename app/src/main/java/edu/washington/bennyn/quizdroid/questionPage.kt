package edu.washington.bennyn.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class questionPage : AppCompatActivity() {
    private lateinit var currAns: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_page)

        val questionView = findViewById<TextView>(R.id.question)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val submitBtn = findViewById<Button>(R.id.submitButton)
        val totalQuestions = intent.getIntExtra("totalQuestions", 1)
        val questionNum = intent.getIntExtra("questionNum", 1)
        val answer = 1 //placeholder for now

        //val subject = ... //set later with intent
        val answers = arrayOf("1", "2", "3", "4") //set later with intent
        val radioButtons = radioGroup.touchables

        //Set answers on radio buttons
        for (i in 0..3) {
            val radioButton = radioButtons[i] as RadioButton
            radioButton.text = answers[i]
        }

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            //Log.e("clickedButton", findViewById<RadioButton>(checkedId).text.toString())
            submitBtn.visibility = (View.VISIBLE)
        }

        submitBtn.setOnClickListener {
            val intent = Intent(this, answerPage::class.java)
            intent.putExtra("totalQuestions", totalQuestions)
            intent.putExtra("questionNum", questionNum)
            intent.putExtra("answer", 1)
            startActivity(intent)
        }
    }
}
