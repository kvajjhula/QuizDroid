package edu.uw.ischool.avajjh.quizdroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText

class Preferences : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        val questionURLText = findViewById<EditText>(R.id.editTextURL)
        Log.d("questionURL", questionURLText.toString())
    }
}