package org.bessonov.android_developer.fragment.contact

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
        val isInternetConnection: Boolean,
        val isUpdateMapProgress: Boolean
    ) : ContactState()
}