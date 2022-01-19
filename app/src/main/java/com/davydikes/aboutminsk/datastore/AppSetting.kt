package com.davydikes.aboutminsk.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.davydikes.aboutminsk.BuildConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    "${BuildConfig.APPLICATION_ID}_datastore"
)
class AppSettings(context: Context) {

    private val dataStore = context.dataStore

    fun languageFlow(): Flow<Int> = dataStore.data.map { preferences ->
        preferences[intPreferencesKey(LANGUAGE_KEY)] ?: -1
    }

    suspend fun language(): Int = languageFlow().first()

    suspend fun setLanguage(language: Int) {
        dataStore.edit { preferences ->
            preferences[intPreferencesKey(LANGUAGE_KEY)] = language
        }
    }

    companion object {
        private const val LANGUAGE_KEY = "LANGUAGE_KEY"
    }
}