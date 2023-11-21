package edu.uw.ischool.avajjh.quizdroid

import android.util.JsonReader

interface TopicRepository {
    fun getTopicsStrings(): List<String>
    fun getQuestionsByTopic(title: String): List<Quiz>
    fun loadTopics(filePath: String)
    fun readTopics(reader: JsonReader): Topic
    fun readQuiz(reader: JsonReader): Quiz
}