package edu.uw.ischool.avajjh.quizdroid

import android.util.JsonReader
import android.util.Log
import java.io.FileReader

class  InMemoryTopicRepository : TopicRepository {
    private val topics = mutableListOf<Topic>()

    init {
        val filePath = "/data/user/0/edu.uw.ischool.avajjh.quizdroid/files/questions.json"
        loadTopics(filePath)
    }

    override fun getQuestionsByTopic(title: String): List<Quiz> {
        return topics.find { it.title == title }?.questionObjects ?: emptyList()
    }
    override fun getTopicsStrings(): List<String> {
        return topics.map { it.title }
    }

    override fun loadTopics(filePath: String) {
        JsonReader(FileReader(filePath)).use {reader ->
            reader.beginArray()
            while (reader.hasNext()) {
                topics.add(readTopics(reader))
            }
            reader.endArray()
        }
    }

    override fun readTopics(reader: JsonReader): Topic {
        var title = ""
        var desc = ""
        val questionObjects = mutableListOf<Quiz>()

        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.nextName()) {
                "title" -> title = reader.nextString()
                "desc" -> desc = reader.nextString()
                "questions" -> {
                    reader.beginArray()
                    while (reader.hasNext()) {
                        questionObjects.add(readQuiz(reader))
                    }
                    reader.endArray()
                }
            }
        }
        reader.endObject()
        return Topic(title, desc, desc, questionObjects)
    }

    override fun readQuiz(reader: JsonReader): Quiz {
        var questionText = ""
        var correctAnswer = -1 // change to one based indexing
        var options = mutableListOf<String>()

        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.nextName()) {
                "text" -> questionText = reader.nextString()
                "answer" -> correctAnswer = reader.nextString().toInt() - 1 // change to one based indexing
                "answers" -> {
                    reader.beginArray()
                    while (reader.hasNext()) {
                        options.add(reader.nextString())
                    }
                    reader.endArray()
                }
            }
        }
        reader.endObject()
        return Quiz(questionText, options, correctAnswer)
    }
}
