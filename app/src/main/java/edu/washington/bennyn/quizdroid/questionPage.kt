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
    private var questionNum = 1
    private var totalQuestions = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_page)

        val questionView = findViewById<TextView>(R.id.question)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val submitBtn = findViewById<Button>(R.id.submitButton)

        totalQuestions = intent.getIntExtra("totalQuestions", 1)
        questionNum = intent.getIntExtra("questionNum", 1)
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
            //Log.e("clickedButton", findViewById<RadioButton>(checkedId).text.toString())
            myAnswer = findViewById<RadioButton>(checkedId).text.toString().toInt()
            submitBtn.visibility = (View.VISIBLE)
        }

        submitBtn.setOnClickListener {
            val intent = Intent(this, answerPage::class.java)
            intent.putExtra("totalQuestions", totalQuestions)
            intent.putExtra("questionNum", questionNum)
            intent.putExtra("myAnswer", myAnswer)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (questionNum == 1) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, questionPage::class.java)
            intent.putExtra("questionNum", questionNum - 1)
            intent.putExtra("totalQuestions", totalQuestions)
            startActivity(intent)
        }
    }
}
