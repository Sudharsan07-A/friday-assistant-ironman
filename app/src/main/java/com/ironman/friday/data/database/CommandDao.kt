package com.ironman.friday.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ironman.friday.data.model.Command

@Dao
interface CommandDao {
    @Insert
    suspend fun insert(command: Command)

    @Query("SELECT * FROM commands ORDER BY timestamp DESC")
    fun getAllCommands(): List<Command>

    @Query("SELECT * FROM commands WHERE id = :id")
    fun getCommandById(id: Int): Command?

    @Delete
    suspend fun delete(command: Command)

    @Query("DELETE FROM commands")
    suspend fun deleteAll()

    @Query("SELECT * FROM commands ORDER BY timestamp DESC LIMIT :limit")
    fun getRecentCommands(limit: Int): List<Command>
}
