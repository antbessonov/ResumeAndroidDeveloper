package org.bessonov.android_developer.domain.util

sealed interface LoadingError : ErrorMessage

object NetworkProblem : LoadingError

object SomethingWentWrong : LoadingError