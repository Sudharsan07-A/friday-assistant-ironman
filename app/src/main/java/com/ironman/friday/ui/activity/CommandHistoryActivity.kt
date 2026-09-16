package com.ironman.friday.ui.activity

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.ironman.friday.R
import com.ironman.friday.data.database.CommandDatabase

class CommandHistoryActivity : AppCompatActivity() {

    private lateinit var historyListView: ListView
    private lateinit var clearButton: Button
    private lateinit var database: CommandDatabase
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_command_history)

        initializeViews()
        loadHistory()
    }

    private fun initializeViews() {
        historyListView = findViewById(R.id.lv_history)
        clearButton = findViewById(R.id.btn_clear_history)
        database = CommandDatabase.getInstance(this)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        historyListView.adapter = adapter

        clearButton.setOnClickListener { clearHistory() }
    }

    private fun loadHistory() {
        // Load commands from database
        val commands = database.commandDao().getAllCommands()
        val commandStrings = commands.map { "${it.command} - ${it.response}" }
        adapter.clear()
        adapter.addAll(commandStrings)
        adapter.notifyDataSetChanged()
    }

    private fun clearHistory() {
        database.commandDao().deleteAll()
        adapter.clear()
        adapter.notifyDataSetChanged()
    }
}
