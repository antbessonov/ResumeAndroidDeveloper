package org.bessonov.android_developer.state

import com.google.android.gms.maps.model.LatLng

sealed class ContactState {

    object Loading : ContactState()

    data class Success(
        val telegramId: String,
        val mail: String,
        val tel: String,
        val linkedinId: String,
        val githubId: String,
        val myGeo: LatLng,
    ) : ContactState()
}