package com.davydikes.aboutminsk.screen.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.davydikes.aboutminsk.repositories.RepositoryApi
import com.davydikes.aboutminsk.repositories.RepositoryLanguage
import com.davydikes.aboutminsk.repositories.RepositoryPlace
import com.davydikes.aboutminsk.support.CoroutineViewModel
import kotlinx.coroutines.launch

class ViewModelMain(
    private val placesRepository: RepositoryPlace,
    private val apiRepository: RepositoryApi,
    private val languageRepository: RepositoryLanguage
) : CoroutineViewModel() {
    val progressLiveData = MutableLiveData<Boolean>()
    var placesLiveData = placesRepository.placesFlow.asLiveData()

    fun importPlaces() = launch {
        val result = apiRepository. importPlaces()
        progressLiveData.postValue(result)
    }

    fun logout() {
        launch {
            languageRepository.logout()
        }
    }
}