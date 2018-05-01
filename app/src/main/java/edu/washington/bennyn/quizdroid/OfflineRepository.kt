package edu.washington.bennyn.quizdroid

//Implements the TopicRepository with hard-coded variables that are stored in memory
class OfflineRepository: TopicRepository {
    override var topics: Array<TopicObj> = makeObjects()

    //Returns an array of topic objects for Math, Physics, and Marvel Superheroes
    private fun makeObjects(): Array<TopicObj> {
        val topicList = mutableListOf<TopicObj>()
        val subjects = arrayOf("Math", "Physics", "Lego Marvel Superheroes")
        for (i in 0..2) {
            val questionList = mutableListOf<QuizObj>()
            for (i in 0..3) {
                val question = QuizObj("This is a question", arrayOf("1", "2", "3", "4"), 1)
                questionList.add(question)
            }
            val topic = TopicObj(subjects[i], "This is a short description",
                    "This is a long description", questionList.toTypedArray())
            topicList.add(topic)
        }
        return (topicList.toTypedArray())
    }

    //get the topic from a topic array by its index
    override fun getTopic(index: Int): TopicObj {
        return (topics[index])
    }
}