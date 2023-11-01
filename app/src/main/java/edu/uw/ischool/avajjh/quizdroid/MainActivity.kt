package edu.uw.ischool.avajjh.quizdroid
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.list_view)
        var topicList = ArrayList<String>()
        topicList.add("Math")
        topicList.add("Physics")
        topicList.add("Marvel Super Heroes")

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, topicList)
        listView.setAdapter(adapter)

        listView.setOnItemClickListener { parent, view, position, id ->
            val selectedTopic = topicList[position]
            val intent = Intent(this, TopicOverviewActivity::class.java)
            intent.putExtra("selectedTopic", selectedTopic)
            startActivity(intent)
        }

    }
}