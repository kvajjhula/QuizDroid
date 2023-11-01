package edu.uw.ischool.avajjh.quizdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class TopicOverviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_overview)

        val selectedTopic = intent.getStringExtra("selectedTopic")
        val topicDescriptionTextView: TextView = findViewById(R.id.topicDescription)
        if (selectedTopic == "Physics") {
            topicDescriptionTextView.text = "This quiz is about $selectedTopic. We will test your basic knowledge of this topic"
        } else if (selectedTopic == "Math") {
            topicDescriptionTextView.text = "This quiz is about $selectedTopic. Hopefully you paid attention in math class."
        } else {
            topicDescriptionTextView.text = "This quiz is about $selectedTopic."
        }

        val btnBegin = findViewById<Button>(R.id.btnBegin)
        btnBegin.setOnClickListener() {
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("selectedTopic", selectedTopic)
            startActivity(intent)
        }






    }
}