package edu.washington.bennyn.quizdroid

import android.content.Context
import android.util.Log

class QuizApp : android.app.Application() {

    //Makes this into a singleton
    companion object Repository {
        val repository = OnlineRepository()
        val subjectNames = arrayOf("Science", "Marvel Superheroes", "Mathematics")
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("appMessage", "QuizApp initialized!")
    }
}