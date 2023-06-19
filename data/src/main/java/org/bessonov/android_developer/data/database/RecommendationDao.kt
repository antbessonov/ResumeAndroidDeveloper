package org.bessonov.android_developer.data.database

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.bessonov.android_developer.data.database.model.RecommendationDbModel

@Dao
interface RecommendationDao {

    @Query("SELECT * FROM recommendations")
    fun getList(): Flow<List<RecommendationDbModel>>
}