package com.ironman.friday.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ironman.friday.data.model.Command

@Database(entities = [Command::class], version = 1, exportSchema = false)
abstract class CommandDatabase : RoomDatabase() {
    abstract fun commandDao(): CommandDao

    companion object {
        @Volatile
        private var INSTANCE: CommandDatabase? = null

        fun getInstance(context: Context): CommandDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    CommandDatabase::class.java,
                    "friday_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
