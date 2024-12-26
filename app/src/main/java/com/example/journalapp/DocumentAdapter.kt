package com.example.journalapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DocumentAdapter(
    private val documents: List<JournalEntry>,
    private val onItemClick: (JournalEntry) -> Unit
) : RecyclerView.Adapter<DocumentAdapter.DocumentViewHolder>() {

    inner class DocumentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle)
        val dateTextView: TextView = itemView.findViewById(R.id.textViewDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_document, parent, false)
        return DocumentViewHolder(view)
    }

    override fun onBindViewHolder(holder: DocumentViewHolder, position: Int) {
        val document = documents[position]
        holder.titleTextView.text = document.title
        holder.dateTextView.text = document.date
        holder.itemView.setOnClickListener { onItemClick(document) }
    }

    override fun getItemCount() = documents.size
}
