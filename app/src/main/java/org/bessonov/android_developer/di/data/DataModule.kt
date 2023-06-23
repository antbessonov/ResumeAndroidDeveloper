package org.bessonov.android_developer.di.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.bessonov.android_developer.data.database.RecommendationDao
import org.bessonov.android_developer.data.database.SkillDao
import org.bessonov.android_developer.data.database.SkillGroupDao
import org.bessonov.android_developer.data.mapper.RecommendationMapper
import org.bessonov.android_developer.data.mapper.SkillGroupMapper
import org.bessonov.android_developer.data.mapper.SkillMapper
import org.bessonov.android_developer.data.repository.RecommendationRepositoryImpl
import org.bessonov.android_developer.data.repository.SkillGroupRepositoryImpl
import org.bessonov.android_developer.domain.repository.RecommendationRepository
import org.bessonov.android_developer.domain.repository.SkillGroupRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideSkillGroupRepository(
        skillGroupDao: SkillGroupDao,
        skillDao: SkillDao,
        skillGroupMapper: SkillGroupMapper
    ): SkillGroupRepository {
        return SkillGroupRepositoryImpl(
            skillGroupDao = skillGroupDao,
            skillDao = skillDao,
            skillGroupMapper = skillGroupMapper
        )
    }

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
    fun provideSkillGroupMapper(skillMapper: SkillMapper): SkillGroupMapper {
        return SkillGroupMapper(skillMapper = skillMapper)
    }

    @Provides
    @Singleton
    fun provideSkillMapper(): SkillMapper {
        return SkillMapper()
    }

    @Provides
    @Singleton
    fun provideRecommendationMapper(): RecommendationMapper {
        return RecommendationMapper()
    }
}