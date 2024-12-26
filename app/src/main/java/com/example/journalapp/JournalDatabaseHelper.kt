package com.example.journalapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class JournalEntry(val id: Long, val title: String, val content: String, val date: String)

class JournalDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_TITLE TEXT, " +
                "$COLUMN_CONTENT TEXT, " +
                "$COLUMN_DATE TEXT)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addJournalEntry(title: String, content: String, date: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, title)
            put(COLUMN_CONTENT, content)
            put(COLUMN_DATE, date)
        }
        return db.insert(TABLE_NAME, null, values)
    }

    fun getAllJournalEntries(): List<JournalEntry> {
        val entries = mutableListOf<JournalEntry>()
        val db = readableDatabase
        val cursor = db.query(TABLE_NAME, null, null, null, null, null, "$COLUMN_DATE DESC")

        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(COLUMN_ID))
                val title = getString(getColumnIndexOrThrow(COLUMN_TITLE))
                val content = getString(getColumnIndexOrThrow(COLUMN_CONTENT))
                val date = getString(getColumnIndexOrThrow(COLUMN_DATE))
                entries.add(JournalEntry(id, title, content, date))
            }
            close()
        }
        return entries
    }

    companion object {
        private const val DATABASE_NAME = "journal.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "journal"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_CONTENT = "content"
        const val COLUMN_DATE = "date"
    }
}
