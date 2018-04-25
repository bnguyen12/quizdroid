package edu.washington.bennyn.quizdroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class Quiz : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        val fragment = TopicOverview()
        val bundle = Bundle()

        val subject = intent.getStringExtra("subject")
        bundle.putString("subject", subject)
        fragment.arguments = bundle

        transaction.replace(R.id.fragmentLayout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
