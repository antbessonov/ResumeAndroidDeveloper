package org.bessonov.android_developer.domain.util

sealed class LoadingResult  {

    object Loading : LoadingResult()

    object Success : LoadingResult()

    data class Error(val message: LoadingError) : LoadingResult()

}
