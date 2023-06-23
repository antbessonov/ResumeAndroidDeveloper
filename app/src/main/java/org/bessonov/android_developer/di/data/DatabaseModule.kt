package org.bessonov.android_developer.di.data

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.bessonov.android_developer.data.database.AppDatabase
import org.bessonov.android_developer.data.database.RecommendationDao
import org.bessonov.android_developer.data.database.SkillDao
import org.bessonov.android_developer.data.database.SkillGroupDao
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
    fun provideSkillGroupDao(appDatabase: AppDatabase): SkillGroupDao {
        return appDatabase.skillGroupDao()
    }

    @Provides
    @Singleton
    fun provideSkillDao(appDatabase: AppDatabase): SkillDao {
        return appDatabase.skillDao()
    }

    @Provides
    @Singleton
    fun provideRecommendationDao(appDatabase: AppDatabase): RecommendationDao {
        return appDatabase.recommendationDao()
    }

    private const val DB_NAME = "android_developer.db"
}