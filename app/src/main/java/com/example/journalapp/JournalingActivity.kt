package com.example.journalapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class JournalingActivity : AppCompatActivity() {

    private lateinit var titleEditText: EditText
    private lateinit var contentEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var viewDocumentsButton: Button // Add reference to buttonViewDocuments
    private lateinit var dbHelper: JournalDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        titleEditText = findViewById(R.id.editTextTitle)
        contentEditText = findViewById(R.id.editTextContent)
        saveButton = findViewById(R.id.buttonSave)
        viewDocumentsButton = findViewById(R.id.buttonViewDocuments) // Initialize the button
        dbHelper = JournalDatabaseHelper(this)

        saveButton.setOnClickListener {
            saveJournalEntry()
        }

        // Set up click listener for the View Documents button
        viewDocumentsButton.setOnClickListener {
            val intent = Intent(this, ViewDocumentsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveJournalEntry() {
        val title = titleEditText.text.toString()
        val content = contentEditText.text.toString()
        val date = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date())

        if (title.isNotEmpty() && content.isNotEmpty()) {
            dbHelper.addJournalEntry(title, content, date)
            Toast.makeText(this, "Journal entry saved!", Toast.LENGTH_SHORT).show()
            titleEditText.text.clear()
            contentEditText.text.clear()
        } else {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
        }
    }
}
