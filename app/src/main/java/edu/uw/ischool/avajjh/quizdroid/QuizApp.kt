package edu.uw.ischool.avajjh.quizdroid
import android.app.Application
import android.util.Log


class QuizApp : Application() {


    companion object {
        lateinit var topicRepository: TopicRepository
        private set
    }
    override fun onCreate() {
        super.onCreate()
        topicRepository = InMemoryTopicRepository()
    }
}