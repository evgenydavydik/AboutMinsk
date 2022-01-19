package com.davydikes.aboutminsk.cloud


data class LanguagesCloutResult(
    val id: Int,
    val key: String,
    val name: String
)

data class PlacesCloutResult(
    val id: Int,
    val name: String,
    val text: String,
    val sound: String,
    val lang: Int,
    val logo: String,
    val photo: String,
    val city_id: Int,
    val visible: Boolean
)
