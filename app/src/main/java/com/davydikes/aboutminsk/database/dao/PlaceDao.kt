package com.davydikes.aboutminsk.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.davydikes.aboutminsk.models.Place
import kotlinx.coroutines.flow.Flow

@Dao
abstract class PlaceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun savePlaces(characters: List<Place>): List<Long>

    @Query("DELETE FROM places")
    abstract fun deleteAllPlaces()

    @Query("SELECT * FROM places where lang==:lang")
    abstract fun getCurrentPlacesLiveFlow(lang: Int): Flow<List<Place>>

    @Query("SELECT * FROM places ")
    abstract fun getPlacesLiveFlow(): Flow<List<Place>>

    @Query("SELECT COUNT(name) FROM places ")
    abstract fun getCountPlaces(): Int
}