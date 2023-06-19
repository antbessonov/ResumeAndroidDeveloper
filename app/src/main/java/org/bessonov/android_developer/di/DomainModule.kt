package org.bessonov.android_developer.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import org.bessonov.android_developer.domain.repository.RecommendationRepository
import org.bessonov.android_developer.domain.usecase.GetRecommendationListUseCase

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    fun provideGetRecommendationListUseCase(
        repository: RecommendationRepository
    ): GetRecommendationListUseCase {
        return GetRecommendationListUseCase(repository)
    }
}