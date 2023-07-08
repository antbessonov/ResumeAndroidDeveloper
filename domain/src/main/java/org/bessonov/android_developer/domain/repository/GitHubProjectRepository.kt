package org.bessonov.android_developer.domain.repository

import kotlinx.coroutines.flow.Flow
import org.bessonov.android_developer.domain.model.GitHubProject
import org.bessonov.android_developer.domain.util.LoadingResult

interface GitHubProjectRepository {

    suspend fun loadList(): LoadingResult

    fun getList(): Flow<List<GitHubProject>>
}