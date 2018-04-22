package edu.washington.bennyn.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class answerPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer_page)

        val myAns = findViewById<TextView>(R.id.myAns)
        val actualAns = findViewById<TextView>(R.id.actualAns)
        val score = findViewById<TextView>(R.id.score)
        val btn = findViewById<Button>(R.id.nextOrFinalBtn)

        val totalQuestions = intent.getIntExtra("totalQuestions", 1)
        val questionNum = intent.getIntExtra("questionNum", 1)
        val answer = intent.getIntExtra("answer", 1)

        myAns.text = answer.toString()
        actualAns.text = 1.toString() //set to an actual answer later

        val scoreText = "You have $questionNum out of $totalQuestions correct!" //turn questionNum into the correct # of answers lately
        score.text = scoreText

        if (totalQuestions == questionNum) {
            btn.text = "Finish"
        }

        btn.setOnClickListener {
            if (btn.text == "Finish") {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, questionPage::class.java)
                intent.putExtra("totalQuestions", totalQuestions)
                intent.putExtra("questionNum", questionNum + 1)
                startActivity(intent)
            }
        }
    }
}
