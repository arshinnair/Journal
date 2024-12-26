package com.example.journalapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewDocumentsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var documentAdapter: DocumentAdapter
    private lateinit var dbHelper: JournalDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_documents)

        recyclerView = findViewById(R.id.recyclerViewDocuments)
        recyclerView.layoutManager = LinearLayoutManager(this)
        dbHelper = JournalDatabaseHelper(this)

        val documents = dbHelper.getAllJournalEntries()
        documentAdapter = DocumentAdapter(documents) { entry ->
            openDocument(entry)
        }
        recyclerView.adapter = documentAdapter
    }

    private fun openDocument(entry: JournalEntry) {
        val intent = Intent(this, DocumentViewActivity::class.java).apply {
            putExtra("document_title", entry.title)
            putExtra("document_content", entry.content)
            putExtra("document_date", entry.date)
        }
        startActivity(intent)
    }
}
