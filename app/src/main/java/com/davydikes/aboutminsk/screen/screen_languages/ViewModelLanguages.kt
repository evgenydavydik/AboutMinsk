package com.davydikes.aboutminsk.screen.screen_languages

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.davydikes.aboutminsk.repositories.RepositoryApi
import com.davydikes.aboutminsk.repositories.RepositoryLanguage
import com.davydikes.aboutminsk.support.CoroutineViewModel
import kotlinx.coroutines.launch

class ViewModelLanguages(
    private val apiRepository: RepositoryApi,
    private val languageRepository: RepositoryLanguage
) : CoroutineViewModel() {

    val selected: LiveData<Boolean> = languageRepository.checkLanguageSelectedIn().asLiveData()

    fun selectedLanguage(language: String) {
        launch {
            languageRepository.selected(language)
        }
    }

    fun importLanguages() = launch {
        val result = apiRepository.importLanguages()
    }
}