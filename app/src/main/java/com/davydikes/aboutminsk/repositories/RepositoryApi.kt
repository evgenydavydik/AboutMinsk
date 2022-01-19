package com.davydikes.aboutminsk.repositories

import com.davydikes.aboutminsk.cloud.CloudKrokappApi
import com.davydikes.aboutminsk.models.LanguageApp
import com.davydikes.aboutminsk.models.Place

class RepositoryApi(
    private val apiInterface: CloudKrokappApi,
    private val placesRepository: RepositoryPlace,
    private val languageRepository: RepositoryLanguage
) {
    suspend fun importPlaces(): Boolean {
        val response = apiInterface.importPlaces()
        val cloudPlaces = response.body() ?: emptyList()
        val places = emptyList<Place>().toMutableList()
        cloudPlaces.map { cloudPlace ->
            if (cloudPlace.city_id == 1 && cloudPlace.visible) {
                places.add(
                    Place(
                        id = cloudPlace.id,
                        name = cloudPlace.name,
                        text = cloudPlace.text,
                        sound = cloudPlace.sound,
                        lang = cloudPlace.lang,
                        logo = cloudPlace.logo,
                        photo = cloudPlace.photo,
                        city_id = cloudPlace.city_id,
                        visible = cloudPlace.visible
                    )
                )
            }

        }
        if (placesRepository.getCountPlaces() != cloudPlaces.size)
            placesRepository.savePlaces(places)
        return response.isSuccessful
    }

    suspend fun importLanguages(): Boolean {
        val response = apiInterface.importLanguages()
        val cloudLanguages = response.body() ?: emptyList()
        val languages = emptyList<LanguageApp>().toMutableList()
        cloudLanguages.map { cloudLanguages ->
            languages.add(
                LanguageApp(
                    id = cloudLanguages.id,
                    key = cloudLanguages.key,
                    name = cloudLanguages.name
                )
            )
        }
        if (languageRepository.getCountLanguages() !=cloudLanguages.size)
            languageRepository.saveLanguagesApp(languages)
        return response.isSuccessful
    }
}