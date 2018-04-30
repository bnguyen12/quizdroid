package edu.washington.bennyn.quizdroid

import android.util.Log

class QuizApp : android.app.Application() {

    override fun onCreate() {
        super.onCreate()
        Log.e("appMessage", "QuizApp initialized!")
    }
}