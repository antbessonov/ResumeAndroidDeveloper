package org.bessonov.android_developer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.bessonov.android_developer.domain.usecase.GetContactUseCase
import org.bessonov.android_developer.domain.util.ErrorMessage
import org.bessonov.android_developer.domain.util.NetworkProblem
import org.bessonov.android_developer.domain.util.OperationFailed
import org.bessonov.android_developer.state.ContactState
import org.bessonov.android_developer.util.hasInternetConnection
import org.bessonov.android_developer.util.socketTimeoutMs
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    getContactUseCase: GetContactUseCase
) : ViewModel() {

    private val isInternetConnection: MutableStateFlow<Boolean> = MutableStateFlow(false)

    private val isUpdateMapProgress: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val uiState: StateFlow<ContactState> =
        combine(
            isInternetConnection,
            isUpdateMapProgress,
            getContactUseCase()
        ) { isInternetConnection, isUpdateMapProgress, contact ->
            ContactState.Success(
                telegramId = contact.telegramId,
                mail = contact.mail,
                tel = contact.tel,
                linkedinId = contact.linkedinId,
                githubId = contact.githubId,
                myGeo = LatLng(contact.myGeo.latitude, contact.myGeo.longitude),
                isInternetConnection = isInternetConnection,
                isUpdateMapProgress = isUpdateMapProgress
            )
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
                initialValue = ContactState.Loading
            )

    private val _errorMessage: MutableStateFlow<ErrorMessage?> = MutableStateFlow(null)
    val errorMessage = _errorMessage.asStateFlow()

    init {
        isInternetConnection()
    }

    fun isInternetConnection() {
        viewModelScope.launch(Dispatchers.IO) {
            isInternetConnection.update { hasInternetConnection() }
        }
    }

    fun updateMap() {
        isInternetConnection()
        isUpdateMapProgress.update { true }
        viewModelScope.launch {
            delay(socketTimeoutMs.toLong())
            if (!isInternetConnection.value) {
                _errorMessage.update { NetworkProblem }
                stopUpdateMap()
            }
        }
    }

    fun stopUpdateMap() {
        isUpdateMapProgress.update { false }
    }

    fun hideErrorMessage() {
        _errorMessage.update { null }
    }

    fun handleNavigationError() {
        _errorMessage.update { OperationFailed }
    }
}