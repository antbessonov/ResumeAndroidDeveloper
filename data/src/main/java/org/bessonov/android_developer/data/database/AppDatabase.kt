package org.bessonov.android_developer.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.bessonov.android_developer.data.database.model.RecommendationDbModel

@Database(
    entities = [RecommendationDbModel::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun recommendationDao(): RecommendationDao
}