package org.bessonov.android_developer.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.bessonov.android_developer.data.database.model.*

@Database(
    entities = [
        HardSkillGroupDbModel::class, HardSkillDbModel::class,
        RecommendationDbModel::class,
        GitHubProjectDbModel::class, GitHubProjectHardSkillDbModel::class,
        EducationGroupDbModel::class, EducationDbModel::class,
        ContactDbModel::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun hardSkillGroupDao(): HardSkillGroupDao

    abstract fun hardSkillDao(): HardSkillDao

    abstract fun recommendationDao(): RecommendationDao

    abstract fun gitHubProjectDao(): GitHubProjectDao

    abstract fun gitHubProjectHardSkillDao(): GitHubProjectHardSkillDao

    abstract fun educationDao(): EducationDao

    abstract fun contactDao(): ContactDao
}