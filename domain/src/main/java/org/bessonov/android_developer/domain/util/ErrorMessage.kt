package org.bessonov.android_developer.domain.util

sealed interface ErrorMessage

object OperationFailed : ErrorMessage