package edu.washington.bennyn.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class Quiz : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        val fragment = TopicOverview()
        val bundle = Bundle()

        val subject = intent.getIntExtra("subject", 1)
        bundle.putInt("subject", subject)
        fragment.arguments = bundle
        transaction.replace(R.id.fragmentLayout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    // Fill menu bar with items
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    // Go to the settings page when an icon is clicked
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        startActivity(Intent(this, Preferences::class.java))
        return true
    }
}
