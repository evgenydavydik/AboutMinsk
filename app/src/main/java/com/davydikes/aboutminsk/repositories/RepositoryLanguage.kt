package com.davydikes.aboutminsk.repositories

import com.davydikes.aboutminsk.database.dao.LanguageAppDao
import com.davydikes.aboutminsk.datastore.AppSettings
import com.davydikes.aboutminsk.models.LanguageApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class RepositoryLanguage(
    private val languageAppDao: LanguageAppDao,
    private val appSettings: AppSettings
) {
    val languages = languageAppDao.getLanguageAppLiveFlow()

    fun getCountLanguages(): Int {
        return languageAppDao.getCountLanguagesApp()
    }

    suspend fun saveLanguagesApp(languages: List<LanguageApp>) {
        withContext(Dispatchers.IO) {
            languageAppDao.saveLanguageApp(languages)
        }
    }

    suspend fun logout() {
        withContext(Dispatchers.IO) {
            appSettings.setLanguage(-1)
        }
    }

    suspend fun selected(language: String) {
        withContext(Dispatchers.IO) {
            appSettings.setLanguage(languageAppDao.getLanguageId(language))
        }
    }

    fun checkLanguageSelectedIn(): Flow<Boolean> =
        appSettings.languageFlow().map { it != -1 }.flowOn(Dispatchers.IO)
}