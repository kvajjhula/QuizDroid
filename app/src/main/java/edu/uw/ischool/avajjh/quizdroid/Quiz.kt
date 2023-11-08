package edu.uw.ischool.avajjh.quizdroid

import java.io.Serializable

data class Quiz(
    val questionText: String,
    val options: List<String>,
    val correctAnswer: Int
) : Serializable