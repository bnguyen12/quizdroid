package edu.washington.bennyn.quizdroid

interface TopicRepository {
    var topics: Array<TopicObj>
    fun getTopic(index: Int): TopicObj
}

//This object is technically a "Question" object as well
data class QuizObj(val question: String, val answers: Array<String>, val answer: Int)

//Object for a topic that carries multiple questions
data class TopicObj(val title: String, val shortDesc: String, val longDesc: String,
                    val questions: Array<QuizObj>)