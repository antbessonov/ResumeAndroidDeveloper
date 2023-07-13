package org.bessonov.android_developer.di.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.bessonov.android_developer.data.database.*
import org.bessonov.android_developer.data.mapper.*
import org.bessonov.android_developer.data.network.APIService
import org.bessonov.android_developer.data.repository.*
import org.bessonov.android_developer.domain.repository.ContactRepository
import org.bessonov.android_developer.domain.repository.GitHubProjectRepository
import org.bessonov.android_developer.domain.repository.HardSkillGroupRepository
import org.bessonov.android_developer.domain.repository.RecommendationRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideHardSkillGroupRepository(
        hardSkillGroupDao: HardSkillGroupDao,
        hardSkillDao: HardSkillDao,
        hardSkillGroupMapper: HardSkillGroupMapper
    ): HardSkillGroupRepository {
        return HardSkillGroupRepositoryImpl(
            hardSkillGroupDao = hardSkillGroupDao,
            hardSkillDao = hardSkillDao,
            hardSkillGroupMapper = hardSkillGroupMapper
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
    fun provideGitHubProjectRepository(
        apiService: APIService,
        loadingResultMapper: LoadingResultMapper,
        gitHubProjectDao: GitHubProjectDao,
        gitHubProjectMapper: GitHubProjectMapper,
        gitHubProjectHardSkillDao: GitHubProjectHardSkillDao,
        gitHubProjectHardSkillMapper: GitHubProjectHardSkillMapper,
        hardSkillDao: HardSkillDao,
        hardSkillGroupDao: HardSkillGroupDao
    ): GitHubProjectRepository {
        return GitHubProjectRepositoryImpl(
            apiService = apiService,
            loadingResultMapper = loadingResultMapper,
            gitHubProjectDao = gitHubProjectDao,
            gitHubProjectMapper = gitHubProjectMapper,
            gitHubProjectHardSkillDao = gitHubProjectHardSkillDao,
            gitHubProjectHardSkillMapper = gitHubProjectHardSkillMapper,
            hardSkillDao = hardSkillDao,
            hardSkillGroupDao = hardSkillGroupDao
        )
    }

    @Provides
    @Singleton
    fun provideEducationRepositoryImpl(
        educationDao: EducationDao,
        educationMapper: EducationMapper
    ): EducationRepositoryImpl {
        return EducationRepositoryImpl(
            educationDao = educationDao,
            educationMapper = educationMapper
        )
    }

    @Provides
    @Singleton
    fun provideContactRepository(
        contactDao: ContactDao,
        contactMapper: ContactMapper
    ): ContactRepository {
        return ContactRepositoryImpl(contactDao = contactDao, contactMapper = contactMapper)
    }

    @Provides
    @Singleton
    fun provideHardSkillGroupMapper(
        skillMapper: HardSkillMapper
    ): HardSkillGroupMapper {
        return HardSkillGroupMapper(hardSkillMapper = skillMapper)
    }

    @Provides
    @Singleton
    fun provideHardSkillMapper(): HardSkillMapper {
        return HardSkillMapper()
    }

    @Provides
    @Singleton
    fun provideRecommendationMapper(): RecommendationMapper {
        return RecommendationMapper()
    }

    @Provides
    @Singleton
    fun provideLoadingResultMapper(): LoadingResultMapper {
        return LoadingResultMapper()
    }

    @Provides
    @Singleton
    fun provideGitHubProjectHardSkillMapper(
        hardSkillMapper: HardSkillMapper,
        hardSkillGroupMapper: HardSkillGroupMapper
    ): GitHubProjectHardSkillMapper {
        return GitHubProjectHardSkillMapper(
            hardSkillMapper = hardSkillMapper,
            hardSkillGroupMapper = hardSkillGroupMapper
        )
    }

    @Provides
    @Singleton
    fun provideGitHubProjectMapper(
        gitHubProjectHardSkillMapper: GitHubProjectHardSkillMapper
    ): GitHubProjectMapper {
        return GitHubProjectMapper(gitHubProjectHardSkillMapper = gitHubProjectHardSkillMapper)
    }

    @Provides
    @Singleton
    fun provideEducationMapper(): EducationMapper {
        return EducationMapper()
    }

    @Provides
    @Singleton
    fun provideContactMapper(): ContactMapper {
        return ContactMapper()
    }
}