package edu.washington.bennyn.quizdroid

interface TopicRepository {
    var topics: Array<TopicObj>

    fun getTopic(index: Int): TopicObj
}

//Implements the TopicRepository with hard-coded variables that are stored in memory
class offlineRepository: TopicRepository {

    override var topics: Array<TopicObj> = makeObjects()

    //create the topics variable
    private fun makeObjects(): Array<TopicObj> {
        val topicList = mutableListOf<TopicObj>()
        for (i in 0..2) {
            val questionList = mutableListOf<QuizObj>()
            for (i in 0..3) {
                val question = QuizObj("This is a question", arrayOf("1", "2", "3", "4"), 1)
                questionList.add(question)
            }
            val topic = TopicObj("This is a title", "shortDesc", "longDesc",
                                 questionList.toTypedArray())
            topicList.add(topic)
        }
        return (topicList.toTypedArray())
    }

    //get the topic from a topic array by its index
    override fun getTopic(index: Int): TopicObj {
        return (topics[index])
    }
}

//This object is technically a "Question" object as well
data class QuizObj(val question: String, val answers: Array<String>, val answer: Int)

//Object for a topic that carries multiple questions
data class TopicObj(val title: String, val shortDesc: String, val longDesc: String,
                    val questions: Array<QuizObj>)