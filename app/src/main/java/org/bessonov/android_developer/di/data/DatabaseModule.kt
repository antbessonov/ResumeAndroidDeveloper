package org.bessonov.android_developer.di.data

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.bessonov.android_developer.data.database.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, DB_NAME)
            .createFromAsset("initial_android_developer.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideHardSkillGroupDao(appDatabase: AppDatabase): HardSkillGroupDao {
        return appDatabase.hardSkillGroupDao()
    }

    @Provides
    @Singleton
    fun provideHardSkillDao(appDatabase: AppDatabase): HardSkillDao {
        return appDatabase.hardSkillDao()
    }

    @Provides
    @Singleton
    fun provideRecommendationDao(appDatabase: AppDatabase): RecommendationDao {
        return appDatabase.recommendationDao()
    }

    @Provides
    @Singleton
    fun provideGitHubProjectDao(appDatabase: AppDatabase): GitHubProjectDao {
        return appDatabase.gitHubProjectDao()
    }

    @Provides
    @Singleton
    fun provideGitHubProjectHardSkillDao(appDatabase: AppDatabase): GitHubProjectHardSkillDao {
        return appDatabase.gitHubProjectHardSkillDao()
    }

    @Provides
    @Singleton
    fun provideEducationDao(appDatabase: AppDatabase): EducationDao {
        return appDatabase.educationDao()
    }

    @Provides
    @Singleton
    fun provideContactDao(appDatabase: AppDatabase): ContactDao {
        return appDatabase.contactDao()
    }

    private const val DB_NAME = "android_developer.db"
}