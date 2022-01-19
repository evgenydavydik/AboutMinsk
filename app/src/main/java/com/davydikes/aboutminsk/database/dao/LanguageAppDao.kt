package com.davydikes.aboutminsk.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.davydikes.aboutminsk.models.LanguageApp
import kotlinx.coroutines.flow.Flow

@Dao
abstract class LanguageAppDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        abstract fun saveLanguageApp(characters: List<LanguageApp>): List<Long>

        @Query("DELETE FROM languages")
        abstract fun deleteAllLanguageApp()

        @Query("SELECT * FROM languages ")
        abstract fun getLanguageAppLiveFlow(): Flow<List<LanguageApp>>

        @Query("SELECT id FROM languages WHERE `key` == :key")
        abstract fun getLanguageId(key: String): Int

        @Query("SELECT COUNT(name) FROM languages ")
        abstract fun getCountLanguagesApp(): Int
}