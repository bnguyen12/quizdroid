package edu.washington.bennyn.quizdroid

import android.util.Log

class QuizApp : android.app.Application(), TopicRepository {

    override var topics: Array<TopicObj>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {
            for (i in 0..2) {
                val topic = TopicObj("This is a title")
                topic.longDesc = "long description"
                topic.shortDesc = "short description"
                for (i in 0..3) {
                    val quiz = QuizObj("This is a question")
                    quiz.answers = arrayOf("1", "2", "3", "4")
                    quiz.answer = 1
                    topic.questions[i] = quiz
                }
                topics[i] = topic
            }
        }

    override fun onCreate() {
        super.onCreate()
        Log.d("appMessage", "QuizApp initialized!")
    }

    //So other classes can get the data from the TopicRepository
    fun getTopic(index: Int): TopicObj {
        return(topics[index])
    }
}