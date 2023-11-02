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
    data class Question(
        val questionText: String,
        val options: List<String>,
        val correctAnswer: Int
    ): Serializable

    val physicsQuestions = listOf(
        Question("What is the speed of light?", listOf("3x10^8 m/s", "2x10^8 m/s", "4x10^8 m/s", "5x10^8 m/s"), 0),
        Question("What is the force exerted on a 1kg mass object accelerating at 9.8 m/s^2?", listOf("1 N", "10 N",  "9.8 N", "0 N"), 2),
        Question("What is newton's 3rd law?", listOf("For every action, there is an equal and opposite reaction", "F=ma", "Energy cannot be created or destroyed", "Objects at rest stay at rest"), 0)
    )

    val mathQuestions = listOf(
        Question("What is 2 + 2?", listOf("3", "4", "5", "6"), 1),
        Question("What is the square root of 16?", listOf("25", "44", "4", "6"), 2),
        Question("I have 3 apples and I buy 23 more. How many total do I have?", listOf("29", "89", "1", "26"), 3)
    )

    val marvelQuestions = listOf(
        Question("Which Marvel superhero carries a hammer?", listOf("Hawkeye", "Black Widow", "Thor", "Captain Canada"), 2),
        Question("What is the name of the AI assistant that Iron Man / Tony Stark uses?", listOf("Jarvis", "Travis", "Garvis", "Mavis"), 0),
        Question("What's Black Widow's real name?", listOf("Bill", "Natasha Romanoff", "Katherine Juarez", "Maria Romanoff"), 1)
    )

    private lateinit var questions: List<Question>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        val selectedTopic = intent.getStringExtra("selectedTopic")

        questions = when (selectedTopic) {
            "Physics" -> physicsQuestions
            "Math" -> mathQuestions
            "Marvel Super Heroes" -> marvelQuestions
            else -> { listOf() }
        }
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

    private fun updateQuestion(question: Question) {
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