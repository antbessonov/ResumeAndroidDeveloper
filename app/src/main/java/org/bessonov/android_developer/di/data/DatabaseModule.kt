package org.bessonov.android_developer.di.data

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.bessonov.android_developer.data.database.AppDatabase
import org.bessonov.android_developer.data.database.RecommendationDao
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, DB_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideRecommendationDao(appDatabase: AppDatabase): RecommendationDao {
        return appDatabase.recommendationDao()
    }

    private const val DB_NAME = "android_developer.db"
}