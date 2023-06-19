package org.bessonov.android_developer.di.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.bessonov.android_developer.data.database.RecommendationDao
import org.bessonov.android_developer.data.mapper.RecommendationMapper
import org.bessonov.android_developer.data.repository.RecommendationRepositoryImpl
import org.bessonov.android_developer.domain.repository.RecommendationRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideRecommendationRepository(
        recommendationDao: RecommendationDao,
        recommendationMapper: RecommendationMapper
    ): RecommendationRepository {
        return RecommendationRepositoryImpl(
            recommendationDao = recommendationDao,
            recommendationMapper = recommendationMapper
        )
    }

    @Provides
    @Singleton
    fun provideRecommendationMapper(): RecommendationMapper {
        return RecommendationMapper()
    }
}