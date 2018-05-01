package edu.washington.bennyn.quizdroid

import android.util.Log

class QuizApp : android.app.Application() {

    //Makes this into a singleton
    companion object Repository {
        val offlineRepository = OfflineRepository()
        val subjectNames = arrayOf("Math", "Physics", "Marvel Superheroes")
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("appMessage", "QuizApp initialized!")
    }
}