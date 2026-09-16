package com.ironman.friday.ui.activity

import android.os.Bundle
import android.widget.EditText
import android.widget.Switch
import android.widget.Spinner
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.ironman.friday.R
import com.ironman.friday.utils.PreferencesManager

class SettingsActivity : AppCompatActivity() {

    private lateinit var apiKeyInput: EditText
    private lateinit var languageSpinner: Spinner
    private lateinit var voiceToggle: Switch
    private lateinit var saveButton: Button
    private lateinit var preferencesManager: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        initializeViews()
        loadSettings()
    }

    private fun initializeViews() {
        apiKeyInput = findViewById(R.id.et_api_key)
        languageSpinner = findViewById(R.id.spinner_language)
        voiceToggle = findViewById(R.id.switch_voice)
        saveButton = findViewById(R.id.btn_save_settings)
        preferencesManager = PreferencesManager(this)

        saveButton.setOnClickListener { saveSettings() }
    }

    private fun loadSettings() {
        apiKeyInput.setText(preferencesManager.getApiKey())
        voiceToggle.isChecked = preferencesManager.isVoiceEnabled()
    }

    private fun saveSettings() {
        preferencesManager.saveApiKey(apiKeyInput.text.toString())
        preferencesManager.setVoiceEnabled(voiceToggle.isChecked)
        finish()
    }
}
