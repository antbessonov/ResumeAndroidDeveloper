package org.bessonov.android_developer.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.bessonov.android_developer.domain.model.GitHubProject
import org.bessonov.android_developer.domain.repository.GitHubProjectRepository

class GetGitHubProjectListUseCase(private val repository: GitHubProjectRepository) {

    operator fun invoke(): Flow<List<GitHubProject>> {
        return repository.getList()
    }
}