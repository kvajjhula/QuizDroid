package edu.uw.ischool.avajjh.quizdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class TopicOverviewActivity : AppCompatActivity() {
    private lateinit var questions: List<Quiz>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_overview)
        val selectedTopic = intent.getStringExtra("selectedTopic")
        val topicDescriptionTextView: TextView = findViewById(R.id.topicDescription)
        val totalQuestionsTextView: TextView = findViewById(R.id.totalQuestions)
        questions = QuizApp.topicRepository.getQuestionsByTopic(selectedTopic!!)
        val totalQuestions = questions.size

        topicDescriptionTextView.text = "This quiz is about $selectedTopic."
        totalQuestionsTextView.text = "Total Questions: $totalQuestions"


        val btnBegin = findViewById<Button>(R.id.btnBegin)
        btnBegin.setOnClickListener() {
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("selectedTopic", selectedTopic)
            startActivity(intent)
        }
    }
}