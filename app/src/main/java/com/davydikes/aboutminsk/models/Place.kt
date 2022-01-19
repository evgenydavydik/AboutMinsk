package com.davydikes.aboutminsk.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(
    tableName = "places",
    foreignKeys = [ForeignKey(
        entity = LanguageApp::class,
        parentColumns = ["id"],
        childColumns = ["lang"],
        onDelete = CASCADE,
        onUpdate = CASCADE
    )]
)

data class Place(
    @PrimaryKey val id: Int,
    val name: String,
    val text: String,
    val sound: String,
    val lang: Int,
    val logo: String,
    val photo: String,
    val city_id: Int,
    val visible: Boolean
) : Parcelable