package com.ironman.friday.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ironman.friday.R
import com.ironman.friday.data.model.Command
import com.ironman.friday.service.AssistantService
import com.ironman.friday.utils.CommandProcessor
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var statusText: TextView
    private lateinit var responseText: TextView
    private lateinit var commandText: TextView
    private lateinit var speakButton: ImageButton
    private lateinit var historyButton: Button
    private lateinit var settingsButton: Button

    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var assistantService: AssistantService
    private lateinit var commandProcessor: CommandProcessor

    private val PERMISSION_REQUEST_CODE = 100
    private val PERMISSIONS = arrayOf(
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.INTERNET
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        initializeServices()
        checkPermissions()
    }

    private fun initializeViews() {
        statusText = findViewById(R.id.tv_status)
        responseText = findViewById(R.id.tv_response)
        commandText = findViewById(R.id.tv_last_command)
        speakButton = findViewById(R.id.btn_speak)
        historyButton = findViewById(R.id.btn_history)
        settingsButton = findViewById(R.id.btn_settings)

        speakButton.setOnClickListener { startListening() }
        historyButton.setOnClickListener { openHistory() }
        settingsButton.setOnClickListener { openSettings() }

        updateStatus("Online")
    }

    private fun initializeServices() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
        assistantService = AssistantService(this)
        commandProcessor = CommandProcessor(this)
    }

    private fun checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val permissionsToRequest = mutableListOf<String>()
            for (permission in PERMISSIONS) {
                if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    permissionsToRequest.add(permission)
                }
            }
            if (permissionsToRequest.isNotEmpty()) {
                ActivityCompat.requestPermissions(this, permissionsToRequest.toTypedArray(), PERMISSION_REQUEST_CODE)
            }
        }
    }

    private fun startListening() {
        updateStatus(getString(R.string.listening))
        speakButton.setColorFilter(ContextCompat.getColor(this, R.color.arc_reactor_blue))

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
        }

        speechRecognizer.startListening(intent)
    }

    private fun processCommand(command: String) {
        updateStatus(getString(R.string.processing))
        commandText.text = "Command: $command"

        val response = commandProcessor.process(command)
        displayResponse(response)
    }

    private fun displayResponse(response: String) {
        updateStatus(getString(R.string.speaking))
        responseText.text = response
        speakButton.setColorFilter(ContextCompat.getColor(this, R.color.gold_primary))
    }

    private fun updateStatus(status: String) {
        statusText.text = status
    }

    private fun openHistory() {
        startActivity(Intent(this, CommandHistoryActivity::class.java))
    }

    private fun openSettings() {
        startActivity(Intent(this, SettingsActivity::class.java))
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                // All permissions granted
            }
        }
    }

    override fun onDestroy() {
        speechRecognizer.destroy()
        super.onDestroy()
    }
}
