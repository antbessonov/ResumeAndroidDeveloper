package org.bessonov.android_developer.data.database

import androidx.room.Dao
import androidx.room.Query
import org.bessonov.android_developer.data.database.model.RecommendationDbModel

@Dao
interface RecommendationDao {

    @Query("SELECT * FROM recommendations")
    suspend fun getList(): List<RecommendationDbModel>
}