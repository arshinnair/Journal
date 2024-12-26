package com.example.journalapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DocumentViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_document_view)

        val titleTextView = findViewById<TextView>(R.id.textViewTitle)
        val contentTextView = findViewById<TextView>(R.id.textViewContent)
        val dateTextView = findViewById<TextView>(R.id.textViewDate)

        val title = intent.getStringExtra("document_title")
        val content = intent.getStringExtra("document_content")
        val date = intent.getStringExtra("document_date")

        titleTextView.text = title
        contentTextView.text = content
        dateTextView.text = date
    }
}
