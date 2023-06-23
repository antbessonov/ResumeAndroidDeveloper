package org.bessonov.android_developer.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.bessonov.android_developer.data.database.model.RecommendationDbModel
import org.bessonov.android_developer.data.database.model.SkillDbModel
import org.bessonov.android_developer.data.database.model.SkillGroupDbModel

@Database(
    entities = [SkillGroupDbModel::class, SkillDbModel::class, RecommendationDbModel::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun skillGroupDao(): SkillGroupDao

    abstract fun skillDao(): SkillDao

    abstract fun recommendationDao(): RecommendationDao
}