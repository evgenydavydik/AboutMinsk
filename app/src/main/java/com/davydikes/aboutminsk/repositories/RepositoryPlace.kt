package com.davydikes.aboutminsk.repositories

import com.davydikes.aboutminsk.database.dao.PlaceDao
import com.davydikes.aboutminsk.datastore.AppSettings
import com.davydikes.aboutminsk.models.Place
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.withContext

class RepositoryPlace(
    private val appSettings: AppSettings,
    private val placeDao: PlaceDao
) {
    val placesFlow: Flow<List<Place>> = appSettings.languageFlow().flatMapLatest {
        placeDao.getCurrentPlacesLiveFlow(it)
    }

    suspend fun savePlaces(places: List<Place>) {
        withContext(Dispatchers.IO){
            placeDao.savePlaces(places)
        }
    }

    fun getCountPlaces(): Int{
        return placeDao.getCountPlaces()
    }
}