package com.ironman.friday.utils

import android.content.Context

class CommandProcessor(private val context: Context) {

    fun process(command: String): String {
        return when {
            command.contains("hello", ignoreCase = true) -> "Good day, Sir. How may I assist you?"
            command.contains("weather", ignoreCase = true) -> "Checking weather data for you, Sir."
            command.contains("time", ignoreCase = true) -> "The current time is ${System.currentTimeMillis()}"
            command.contains("thank", ignoreCase = true) -> "You're welcome, Sir. Happy to help."
            command.contains("shutdown", ignoreCase = true) -> "Powering down systems, Sir."
            command.contains("help", ignoreCase = true) -> "I can help you with voice commands, weather updates, and more. What would you like to do?"
            else -> "I'm not sure I understood that, Sir. Could you please repeat?"
        }
    }
}
