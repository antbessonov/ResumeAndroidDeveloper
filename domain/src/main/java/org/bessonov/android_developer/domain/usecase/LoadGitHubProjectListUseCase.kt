package org.bessonov.android_developer.domain.usecase

import org.bessonov.android_developer.domain.repository.GitHubProjectRepository
import org.bessonov.android_developer.domain.util.LoadingResult

class LoadGitHubProjectListUseCase(private val repository: GitHubProjectRepository) {

   suspend operator fun invoke(): LoadingResult {
        return repository.loadList()
    }
}