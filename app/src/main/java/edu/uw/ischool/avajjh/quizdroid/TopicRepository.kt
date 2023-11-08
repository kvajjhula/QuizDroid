package edu.uw.ischool.avajjh.quizdroid

interface TopicRepository {
    fun getTopics(): List<Topic>
    fun getTopicsStrings(): List<String>
    fun getTopic(title: String): Topic?
    fun getQuestionsByTopic(title: String): List<Quiz>
}