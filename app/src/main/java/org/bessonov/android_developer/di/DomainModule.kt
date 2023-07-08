package org.bessonov.android_developer.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import org.bessonov.android_developer.data.repository.EducationRepositoryImpl
import org.bessonov.android_developer.domain.repository.ContactRepository
import org.bessonov.android_developer.domain.repository.GitHubProjectRepository
import org.bessonov.android_developer.domain.repository.HardSkillGroupRepository
import org.bessonov.android_developer.domain.repository.RecommendationRepository
import org.bessonov.android_developer.domain.usecase.*

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    fun provideGetRecommendationListUseCase(
        repository: RecommendationRepository
    ): GetRecommendationListUseCase {
        return GetRecommendationListUseCase(repository = repository)
    }

    @Provides
    fun provideGetHardSkillGroupListUseCase(
        repository: HardSkillGroupRepository
    ): GetHardSkillGroupListUseCase {
        return GetHardSkillGroupListUseCase(repository = repository)
    }

    @Provides
    fun provideGetGitHubProjectListUseCase(
        repository: GitHubProjectRepository
    ): GetGitHubProjectListUseCase {
        return GetGitHubProjectListUseCase(repository = repository)
    }

    @Provides
    fun provideLoadGitHubProjectListUseCase(
        repository: GitHubProjectRepository
    ): LoadGitHubProjectListUseCase {
        return LoadGitHubProjectListUseCase(repository = repository)
    }

    @Provides
    fun provideGetEducationListUseCase(): GetEducationListUseCase {
        return GetEducationListUseCase()
    }

    @Provides
    fun provideGetContactUseCase(repository: ContactRepository): GetContactUseCase {
        return GetContactUseCase(repository = repository)
    }
}