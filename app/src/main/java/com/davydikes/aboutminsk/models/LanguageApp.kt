package com.davydikes.aboutminsk.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "languages")
data class LanguageApp(
    @PrimaryKey
    val id: Int,
    val key: String,
    val name: String
) : Parcelable