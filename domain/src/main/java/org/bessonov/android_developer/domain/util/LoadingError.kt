package org.bessonov.android_developer.domain.util

sealed interface LoadingError

object NetworkProblem : LoadingError

object SomethingWentWrong : LoadingError