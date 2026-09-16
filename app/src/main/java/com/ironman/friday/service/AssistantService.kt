package com.ironman.friday.service

import android.content.Context
import android.speech.tts.TextToSpeech
import com.ironman.friday.data.database.CommandDatabase
import com.ironman.friday.data.model.Command
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Locale

class AssistantService(private val context: Context) : TextToSpeech.OnInitListener {

    private lateinit var textToSpeech: TextToSpeech
    private val database = CommandDatabase.getInstance(context)

    init {
        textToSpeech = TextToSpeech(context, this)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            textToSpeech.language = Locale.getDefault()
        }
    }

    fun speak(text: String) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null)
    }

    fun saveCommand(command: String, response: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val cmd = Command(
                command = command,
                response = response,
                timestamp = System.currentTimeMillis()
            )
            database.commandDao().insert(cmd)
        }
    }

    fun shutdown() {
        textToSpeech.stop()
        textToSpeech.shutdown()
    }
}
