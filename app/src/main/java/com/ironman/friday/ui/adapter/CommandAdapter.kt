package com.ironman.friday.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ironman.friday.data.model.Command
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CommandAdapter(private val commands: List<Command>) :
    RecyclerView.Adapter<CommandAdapter.CommandViewHolder>() {

    inner class CommandViewHolder(private val root: ViewGroup) :
        RecyclerView.ViewHolder(root) {
        private val commandText: TextView = root.findViewById(android.R.id.text1)
        private val responseText: TextView = root.findViewById(android.R.id.text2)
        private val timeText: TextView = root.findViewById(android.R.id.icon)

        fun bind(command: Command) {
            commandText.text = "Command: ${command.command}"
            responseText.text = "Response: ${command.response}"
            timeText.text = formatTime(command.timestamp)
        }

        private fun formatTime(timestamp: Long): String {
            val date = Date(timestamp)
            val format = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            return format.format(date)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommandViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CommandViewHolder(parent)
    }

    override fun onBindViewHolder(holder: CommandViewHolder, position: Int) {
        holder.bind(commands[position])
    }

    override fun getItemCount(): Int = commands.size
}
