package com.davydikes.aboutminsk.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.davydikes.aboutminsk.database.dao.LanguageAppDao
import com.davydikes.aboutminsk.database.dao.PlaceDao
import com.davydikes.aboutminsk.models.LanguageApp
import com.davydikes.aboutminsk.models.Place

@Database(
    entities = [
        Place::class,
        LanguageApp::class
    ],
    version = 1,
    exportSchema = true
)



abstract class MinskDatabase : RoomDatabase() {
    abstract fun placesDao(): PlaceDao
    abstract fun languageAppDao(): LanguageAppDao
}

object DatabaseConstructor {
    fun create(context: Context): MinskDatabase =
        Room.databaseBuilder(
            context,
            MinskDatabase::class.java,
            "com.davydikes.aboutminsk.db"
        ).build()
}