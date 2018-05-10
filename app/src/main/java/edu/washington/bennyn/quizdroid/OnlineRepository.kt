package edu.washington.bennyn.quizdroid

import android.Manifest
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

class OnlineRepository(): TopicRepository {
    override var topics: Array<TopicObj> = retrieveJSON()

    override fun getTopic(index: Int): TopicObj {
        return (topics[index])
    }

    private fun retrieveJSON(): Array<TopicObj> {
        val topicsList = ArrayList<TopicObj>()
        val path = File(Environment.getExternalStorageDirectory().toString() + "/Download/questions.json")
        if (path.exists()) {
            Log.e("json status", "found!")
            val jsonText = File(path.toString())
            val jsonArray = JSONArray(jsonText.readText())
            for (i in 0..jsonArray.length() - 1) {
                val jsonObject = JSONObject(jsonArray[i].toString())
                val topicName = jsonObject["title"].toString()
                val desc = jsonObject["desc"].toString()

                val questionList = mutableListOf<QuizObj>()

                val questions = JSONArray(jsonObject["questions"].toString())
                for (i in 0..questions.length() - 1) {
                    val question = JSONObject(questions[i].toString())
                    val questionText = question["text"].toString()
                    val answer = question["answer"].toString().toInt()
                    val answersList = question["answers"] as JSONArray
                    val answers = mutableListOf<String>()
                    for (j in 0..answersList.length() - 1) {
                        answers.add(answersList[j].toString())
                    }

                    val questionObj = QuizObj(questionText, answers.toTypedArray(), answer)
                    questionList.add(questionObj)
                }

                val topicObj = TopicObj(topicName, desc, desc, questionList.toTypedArray())
                topicsList.add(topicObj)
            }
        } else {
            Log.e("file status", "not found!")
        }
        return topicsList.toTypedArray()
    }
}