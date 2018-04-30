package edu.washington.bennyn.quizdroid

interface TopicRepository {
    var topics: Array<TopicObj> //hard-coded and stored in memory
}

//This object is technically a "Question" object as well
data class QuizObj(val question: String) {
    lateinit var answers: Array<String>
    var answer = 1 //default answer
}

data class TopicObj(val title: String) {
    lateinit var shortDesc: String
    lateinit var longDesc: String
    lateinit var questions: Array<QuizObj>
}