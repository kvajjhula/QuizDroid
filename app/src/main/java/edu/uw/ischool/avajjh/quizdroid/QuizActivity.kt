package edu.uw.ischool.avajjh.quizdroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

class QuizActivity : AppCompatActivity() {
    private var currentQuestionIndex = 0;
    private var numCorrectAnswers = 0;

    private lateinit var questions: List<Quiz>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        val selectedTopic = intent.getStringExtra("selectedTopic")

        questions = QuizApp.topicRepository.getQuestionsByTopic(selectedTopic!!)
        val totalQuestions = questions.size

        updateQuestion(questions[0])

        val submitButton = findViewById<Button>(R.id.button)
        submitButton.setOnClickListener() {
            val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
            val selectedRadioButtonId: Int = radioGroup.checkedRadioButtonId
            // Check if any radio button is selected
            if (selectedRadioButtonId == -1) {
                // No radio button selected, do nothing
                return@setOnClickListener
            }

            if (currentQuestionIndex < questions.count()) {

                val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)
                val selectedIndex = radioGroup.indexOfChild(selectedRadioButton)
                val correctAnswerIndex = questions[currentQuestionIndex].correctAnswer
                val correctAnswer = questions[currentQuestionIndex].options[correctAnswerIndex]
                val userAnswer = questions[currentQuestionIndex].options[selectedIndex]

                if (selectedIndex == correctAnswerIndex) {
                    numCorrectAnswers++;
                }
                if (currentQuestionIndex == questions.count() - 1) {
                    finish()
                }
                // package the correct answer for the current question
                val intent = Intent(this, AnswerActivity::class.java)
                intent.putExtra("correctAnswer", correctAnswer)
                intent.putExtra("isLastQuestion", currentQuestionIndex == questions.count() - 1)

                currentQuestionIndex++;
                intent.putExtra("userAnswer", userAnswer)
                intent.putExtra("numCorrectAnswers", numCorrectAnswers)
                intent.putExtra("totalQuestions", totalQuestions)

                startActivity(intent)

            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (currentQuestionIndex < questions.count()) {
            updateQuestion(questions[currentQuestionIndex])
        }
    }

    private fun updateQuestion(question: Quiz) {
        val questionTextView : TextView = findViewById(R.id.questionTextView)
        val radioGroupOptions : RadioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        radioGroupOptions.clearCheck()
        questionTextView.text = question.questionText;
        findViewById<RadioButton>(R.id.radioButton).text = question.options[0]
        findViewById<RadioButton>(R.id.radioButton2).text = question.options[1]
        findViewById<RadioButton>(R.id.radioButton3).text = question.options[2]
        findViewById<RadioButton>(R.id.radioButton4).text = question.options[3]
    }
}