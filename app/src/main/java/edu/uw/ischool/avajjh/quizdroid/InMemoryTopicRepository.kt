package edu.uw.ischool.avajjh.quizdroid

class InMemoryTopicRepository : TopicRepository {
    private val topics = mutableListOf<Topic>()

    init {
        topics.add(Topic("Math", "This quiz will test your basic Math knowledge.", "Long description of Math", mutableListOf(
            Quiz("What is 2 + 2?", listOf("3", "4", "5", "6"), 1),
            Quiz("What is the square root of 16?", listOf("25", "44", "4", "6"), 2),
            Quiz("I have 3 apples and I buy 23 more. How many total do I have?", listOf("29", "89", "1", "26"), 3)
        )))
        topics.add(Topic("Physics", "This quiz will test your basic Physics knowledge.", "Long description of Math", mutableListOf(
            Quiz("What is the speed of light?", listOf("3x10^8 m/s", "2x10^8 m/s", "4x10^8 m/s", "5x10^8 m/s"), 0),
            Quiz("What is the force exerted on a 1kg mass object accelerating at 9.8 m/s^2?", listOf("1 N", "10 N",  "9.8 N", "0 N"), 2),
            Quiz("What is newton's 3rd law?", listOf("For every action, there is an equal and opposite reaction", "F=ma", "Energy cannot be created or destroyed", "Objects at rest stay at rest"), 0)
        )))
        topics.add(Topic("Marvel Super Heroes", "This quiz will test your basic Marvel superhero knowledge.", "Long description of Math", mutableListOf(
            Quiz("Which Marvel superhero carries a hammer?", listOf("Hawkeye", "Black Widow", "Thor", "Captain Canada"), 2),
            Quiz("What is the name of the AI assistant that Iron Man / Tony Stark uses?", listOf("Jarvis", "Travis", "Garvis", "Mavis"), 0),
            Quiz("What's Black Widow's real name?", listOf("Bill", "Natasha Romanoff", "Katherine Juarez", "Maria Romanoff"), 1)
        )))
    }

    override fun getTopics(): List<Topic> {
        return topics
    }
    override fun getTopic(title: String): Topic? {
        return topics.find { it.title == title }
    }
    override fun getQuestionsByTopic(title: String): List<Quiz> {
        return topics.find { it.title == title }?.questionObjects ?: emptyList()
    }
    override fun getTopicsStrings(): List<String> {
//        var topicStrings = mutableListOf<String>()
//        topics.forEach { topic ->
//            topicStrings.add(topic.title)
//        }
//        return topicStrings
        return topics.map { it.title }
    }

}
