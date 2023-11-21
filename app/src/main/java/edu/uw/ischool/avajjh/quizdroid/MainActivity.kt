package edu.uw.ischool.avajjh.quizdroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val listView = findViewById<ListView>(R.id.list_view)
        val topicList = QuizApp.topicRepository.getTopicsStrings()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, topicList)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val selectedTopic = topicList[position]
            val intent = Intent(this, TopicOverviewActivity::class.java)
            intent.putExtra("selectedTopic", selectedTopic)
            startActivity(intent)
        }
        Log.i("mainactivity", filesDir.toString())


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.subitem_preferences -> {
                val intent = Intent(this, Preferences::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }





}