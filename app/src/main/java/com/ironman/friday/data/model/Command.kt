package com.ironman.friday.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "commands")
data class Command(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val command: String,
    val response: String,
    val timestamp: Long = System.currentTimeMillis(),
    val status: String = "completed"
)
