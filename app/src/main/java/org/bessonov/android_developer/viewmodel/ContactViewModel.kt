package org.bessonov.android_developer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.bessonov.android_developer.domain.usecase.GetContactUseCase
import org.bessonov.android_developer.state.ContactState
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    getContactUseCase: GetContactUseCase
) : ViewModel() {

    val uiState: StateFlow<ContactState> = getContactUseCase()
        .map { contact ->
            ContactState.Success(
                telegramId = contact.telegramId,
                mail = contact.mail,
                tel = contact.tel,
                linkedinId = contact.linkedinId,
                githubId = contact.githubId,
                myGeo = LatLng(contact.myGeo.latitude, contact.myGeo.longitude),
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
            initialValue = ContactState.Loading
        )
}