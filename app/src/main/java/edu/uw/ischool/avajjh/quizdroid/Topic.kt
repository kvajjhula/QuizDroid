package edu.uw.ischool.avajjh.quizdroid

data class Topic(
    val title: String,
    val shortDescription: String,
    val longDescription: String,
    val questionObjects: List<Quiz>
)