package edu.uw.ischool.avajjh.quizdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class AnswerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)
        val correctAnswer = intent.getStringExtra("correctAnswer")
        val userAnswer = intent.getStringExtra("userAnswer")
        val numCorrectAnswers = intent.getIntExtra("numCorrectAnswers", 0)
        val isLastQuestion = intent.getBooleanExtra("isLastQuestion", false)
        val totalQuestions = intent.getIntExtra("totalQuestions", 0)

        Log.d("debug", correctAnswer.toString())
        val correctAnswerTextView : TextView = findViewById(R.id.correctAnswer)
        val userAnswerTextView : TextView = findViewById(R.id.userAnswered)
        val numCorrectAnswersTextView : TextView = findViewById(R.id.questionsAnswered)

        val correctAnswerMessage = "The correct answer was $correctAnswer"
        val userAnswerMessage = "You answered $userAnswer"
        val numCorrectAnswersMessage = "You have gotten $numCorrectAnswers out of $totalQuestions questions right so far"
        correctAnswerTextView.text = correctAnswerMessage
        userAnswerTextView.text = userAnswerMessage
        numCorrectAnswersTextView.text = numCorrectAnswersMessage

        val nextButton = findViewById<Button>(R.id.nextButton)
        if (isLastQuestion) {
            nextButton.text = "Finish"
        }
        nextButton.setOnClickListener() {
            if (isLastQuestion) {
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            } else {
                finish()
            }
        }


    }
}