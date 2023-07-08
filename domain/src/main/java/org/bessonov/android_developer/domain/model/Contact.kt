package org.bessonov.android_developer.domain.model

data class Contact(
    val telegramId: String,
    val mail: String,
    val tel: String,
    val linkedinId: String,
    val githubId: String,
    val myGeo: Geo
)
