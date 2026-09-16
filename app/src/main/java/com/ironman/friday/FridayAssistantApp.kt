package com.ironman.friday

import android.app.Application
import android.content.Context
import com.ironman.friday.data.database.CommandDatabase

class FridayAssistantApp : Application() {

    companion object {
        private lateinit var instance: FridayAssistantApp

        fun getInstance(): FridayAssistantApp {
            return instance
        }

        fun getAppContext(): Context {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initializeDatabase()
    }

    private fun initializeDatabase() {
        // Initialize Room database
        CommandDatabase.getInstance(this)
    }
}
